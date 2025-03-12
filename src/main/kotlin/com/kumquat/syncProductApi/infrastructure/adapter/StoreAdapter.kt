package com.kumquat.syncProductApi.infrastructure.adapter

import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand
import com.kumquat.syncProductApi.domain.port.StorePort
import com.kumquat.syncProductApi.infrastructure.adapter.mapper.StoreAdapterMapper
import com.kumquat.syncProductApi.infrastructure.database.StoreEntityRepository
import com.kumquat.syncProductApi.util.logger
import org.springframework.stereotype.Service

@Service
class StoreAdapter(
    private val storeEntityRepository: StoreEntityRepository,
    private val storeAdapterMapper: StoreAdapterMapper
) : StorePort {
    override fun createStore(upsertStoreCommand: UpsertStoreCommand) {
        log.info("[STORE] Adding store. $upsertStoreCommand")
        val storeToCreate = storeAdapterMapper.toCreatedStoreEntity(upsertStoreCommand)
        val createdStore = storeEntityRepository.save(storeToCreate)
        log.info("[STORE] Successfully added store. $createdStore")
    }

    override fun updateStore(upsertStoreCommand: UpsertStoreCommand) {
        log.info("[STORE] Updating store. $upsertStoreCommand")
        storeEntityRepository.findByExternalId(upsertStoreCommand.externalId)
            ?.let { foundStoreEntity ->
                val updatedStoreEntity = storeAdapterMapper.toUpdatedStoreEntity(upsertStoreCommand,foundStoreEntity)
                val savedStoreEntity = storeEntityRepository.save(updatedStoreEntity)
                log.info("[STORE] Successfully updated store. $savedStoreEntity")
            }
            ?: throw IllegalStateException("Store with given external ID: ${upsertStoreCommand.externalId} not found!")
    }

    companion object {
        private val log by logger()
    }
}