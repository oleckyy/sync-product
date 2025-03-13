package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.store.Store
import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand
import java.util.*

interface StorePort {
    fun createStore(upsertStoreCommand: UpsertStoreCommand)

    fun updateStore(upsertStoreCommand: UpsertStoreCommand)

    fun findAllActiveStores(): List<Store>

    fun findAllStoresInGroupedByExternalId(externalIds: List<UUID>): Map<UUID, Store>

    fun existsByExternalId(externalId: UUID): Boolean
}