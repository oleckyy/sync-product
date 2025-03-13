package com.kumquat.syncProductApi.infrastructure.rest.internal.service

import com.kumquat.syncProductApi.infrastructure.database.ProductEntityRepository
import com.kumquat.syncProductApi.infrastructure.database.StoreEntityRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ProductService(
    private val productEntityRepository: ProductEntityRepository,
    private val storeEntityRepository: StoreEntityRepository
) {

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