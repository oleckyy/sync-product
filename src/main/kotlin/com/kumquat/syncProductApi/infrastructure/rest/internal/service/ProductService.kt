package com.kumquat.syncProductApi.infrastructure.rest.internal.service

import com.kumquat.syncProductApi.infrastructure.database.ProductEntityRepository
import com.kumquat.syncProductApi.infrastructure.database.StoreEntityRepository
import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toProductDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.specification.ProductPredicateBuilder
import com.kumquat.syncProductApi.infrastructure.rest.internal.specification.predicate.ProductSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class ProductService(
    private val productEntityRepository: ProductEntityRepository,
    private val storeEntityRepository: StoreEntityRepository,
    private val predicateBuilders: List<ProductPredicateBuilder>,
) {
    @Transactional(readOnly = true)
    fun findAll(
        pageable: Pageable,
        productFilters: ProductFiltersDto
    ): Page<ProductDto> {
        return productEntityRepository.findAll(
            ProductSpecification(predicateBuilders, productFilters),
            pageable
        ).map { it.toProductDto() }
    }

    fun getLatestSyncDateForStores(defaultSyncDateTime: LocalDateTime): Map<UUID, LocalDateTime> {
        val storeIds = storeEntityRepository.findAllByActiveTrue().map { it.id }
        val storeVersionDateFromDocuments = productEntityRepository.findLatestStoreVersionInfo()
        val storeVersionMap = storeVersionDateFromDocuments.associate { it.storeId to it.latestSyncDateTime }
        val completeStoreSyncDateInfo = mutableMapOf<UUID, LocalDateTime>()
        storeIds.forEach { storeId ->
            completeStoreSyncDateInfo[storeId!!] = storeVersionMap[storeId] ?: defaultSyncDateTime
        }
        return completeStoreSyncDateInfo
    }
}