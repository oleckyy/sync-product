package com.kumquat.syncProductApi.application.synchronization.store.mapper

import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore
import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand
import org.springframework.stereotype.Component

@Component
class StoreSynchronizationMapper {
    fun toUpsertStoreCommand(externalStore: ExternalStore) =
        UpsertStoreCommand(
            externalId = externalStore.id,
            active = externalStore.active,
            name = externalStore.name,
            location = externalStore.location,
            apiUrl = externalStore.apiUrl,
        )
}