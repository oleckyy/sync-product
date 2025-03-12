package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand
import com.kumquat.syncProductApi.infrastructure.database.entity.StoreEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StoreAdapterMapper {
    fun toCreatedStoreEntity(upsertStoreCommand: UpsertStoreCommand) =
        StoreEntity(
            externalId = upsertStoreCommand.externalId,
            active = upsertStoreCommand.active,
            name = upsertStoreCommand.name,
            location = upsertStoreCommand.location,
            apiUrl = upsertStoreCommand.apiUrl,
            versionDate = LocalDateTime.now(),
        )

    fun toUpdatedStoreEntity(upsertStoreCommand: UpsertStoreCommand, storeEntity: StoreEntity) =
        storeEntity.copy(
            active = upsertStoreCommand.active,
            name = upsertStoreCommand.name,
            location = upsertStoreCommand.location,
            apiUrl = upsertStoreCommand.apiUrl,
            versionDate = LocalDateTime.now(),
        )
}