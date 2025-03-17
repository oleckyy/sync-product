package com.kumquat.syncProductApi.infrastructure.adapter

import com.kumquat.syncProductApi.domain.model.product.Product
import com.kumquat.syncProductApi.domain.port.OutgoingProductDatabasePort
import com.kumquat.syncProductApi.infrastructure.adapter.mapper.ProductAdapterMapper
import com.kumquat.syncProductApi.infrastructure.database.ProductEntityRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OutgoingProductDatabaseDatabaseAdapter(
    private val productAdapterMapper: ProductAdapterMapper,
    private val productEntityRepository: ProductEntityRepository,
) : OutgoingProductDatabasePort {
    override fun findAllByStoreIdGroupedByExternalId(externalIds: List<UUID>, storeId: UUID): Map<UUID, Product> =
        productEntityRepository.findAllByStoreIdAndExternalIdIn(storeId, externalIds)
            .map { productAdapterMapper.toProduct(it) }
            .associateBy { it.externalId }
}