package com.kumquat.syncProductApi.application.synchronization.store

import com.kumquat.syncProductApi.application.synchronization.store.mapper.StoreSynchronizationMapper
import com.kumquat.syncProductApi.domain.client.ExternalStoreClient
import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore
import com.kumquat.syncProductApi.domain.port.StorePort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class StoreSynchronizationManager(
    //TODO add registry entities with predetermined value for store hub api url
   // private val registryPort: RegistryPort,
    private val storePort: StorePort,
    private val externalStoreClient: ExternalStoreClient,
    private val storeSynchronizationMapper: StoreSynchronizationMapper
) {
    fun synchronize() {
        val mockUrl = "http//localhost:9999/store-hub/stores"
        externalStoreClient.getExternalStores(mockUrl)
            .map { externalStores -> createOrUpdateStores(externalStores) }
    }
    private fun createOrUpdateStores(externalStores: List<ExternalStore>) {
        val externalStoreIds = externalStores.map { it.id }
        val foundStores = storePort.findAllStoresInGroupedByExternalId(externalStoreIds)
        externalStores.forEach { externalStore ->
            val upsertStoreCommand =
                storeSynchronizationMapper.toUpsertStoreCommand(externalStore)
            foundStores[externalStore.id]
                ?.takeIf { externalStore.versionDate.isAfter(it.versionDate) }
                ?.let { storePort.updateStore(upsertStoreCommand) }
                ?: let {
                    if (!storePort.existsByExternalId(externalStore.id)) {
                        storePort.createStore(upsertStoreCommand)
                    }
                }
        }
    }
}