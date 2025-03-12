package com.kumquat.syncProductApi.application.synchronization.store

import com.kumquat.syncProductApi.domain.client.ExternalStoreClient
import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore
import com.kumquat.syncProductApi.domain.port.StorePort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class StoreSynchronizationManager(
    //TODO add registry entities with predetermined value for store hub api url
   // private val registryPort: RegistryPort,
    private val storePort: StorePort,
    private val externalStoreClient: ExternalStoreClient,
    private val
) {
    fun synchronize() {
        val mockUrl = "http://localhost:9999/storeHub/stores"
        externalStoreClient.getExternalStores(mockUrl)
            .map { externalStores -> createOrUpdateStores(externalStores) }
    }
    private fun createOrUpdateStores(externalStores: List<ExternalStore>) {
        val externalStoreIds = externalStores.map { it.id }
        val foundStores = storePort.findAllStoresInGroupedByExternalId(externalStoreIds)
        externalStores.forEach { externalStore ->
            val upsertStoreCommand =
                stationSynchronizationMapper.toUpsertStoreCommand(externalStore, stationGroupId)
            foundStores[externalStore.id]
                ?.takeIf { externalStore.versionDate.isAfter(it.externalVersionDate) }
                ?.let { stationPort.updateStore(upsertStoreCommand) }
                ?: let {
                    if (!stationPort.existsByExternalId(externalStore.id)) {
                        stationPort.createStore(upsertStoreCommand)
                    }
                }
        }
    }

    private fun fetchStoreGroupsByExternalIds(externalStores: List<ExternalStore>): Map<Int, UUID> {
        val externalStoreGroupIds = externalStores.mapNotNull { it.groupId }
        return stationPort.findAllStoreGroupsInGroupedByExternalId(externalStoreGroupIds)
            .map { it.key to it.value.id }
            .toMap()
    }
}