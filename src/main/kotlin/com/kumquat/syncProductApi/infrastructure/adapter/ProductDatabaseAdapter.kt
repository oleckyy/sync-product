package com.kumquat.syncProductApi.infrastructure.adapter

import com.kumquat.syncProductApi.domain.accessor.ProductSynchronizationAccessor
import com.kumquat.syncProductApi.domain.model.product.Product
import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommand
import com.kumquat.syncProductApi.domain.port.ProductPort
import com.kumquat.syncProductApi.infrastructure.adapter.mapper.ProductAdapterMapper
import com.kumquat.syncProductApi.infrastructure.database.ProductEntityRepository
import com.kumquat.syncProductApi.util.logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductDatabaseAdapter(
    private val productAdapterMapper: ProductAdapterMapper,
    private val productEntityRepository: ProductEntityRepository,
    private val productSynchronizationAccessor: ProductSynchronizationAccessor
) : ProductPort {
    override fun create(upsertProductCommand: UpsertProductCommand) {
        log.info("[PRODUCT] Adding product. $upsertProductCommand")
        val productToCreate = productAdapterMapper.toCreatedProductEntity(upsertProductCommand)
        val createdProduct = productEntityRepository.save(productToCreate)
        log.info("[PRODUCT] Successfully added product. $createdProduct")
        productSynchronizationAccessor.putProductSyncDateByStoreId(createdProduct.storeId, createdProduct.versionDate)
    }

    override fun update(upsertProductCommand: UpsertProductCommand) {
        log.info("[PRODUCT] Updating product. $upsertProductCommand")
        productEntityRepository.findByIdOrNull(upsertProductCommand.id)?.let {
            val updatedProductEntity = productAdapterMapper.toUpdatedProductEntity(upsertProductCommand, it)
            val savedProductEntity = productEntityRepository.save(updatedProductEntity)
            productSynchronizationAccessor.putProductSyncDateByStoreId(
                savedProductEntity.storeId,
                savedProductEntity.versionDate
            )
            log.info("[PRODUCT] Successfully updated product. $savedProductEntity")
        }
    }

    override fun findAllByStoreIdGroupedByExternalId(externalIds: List<UUID>, storeId: UUID): Map<UUID, Product> =
        productEntityRepository.findAllByStoreIdAndExternalIdIn(storeId, externalIds)
            .map { productAdapterMapper.toProduct(it) }
            .associateBy { it.externalId }

    companion object {
        private val log by logger()
    }
}