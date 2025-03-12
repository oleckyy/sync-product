package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.store.Store
import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand
import java.util.*

interface StorePort {
    fun createStore(upsertStoreCommand: UpsertStoreCommand)

    fun updateStore(upsertStoreCommand: UpsertStoreCommand)

    fun findAllStoresInGroupedByExternalId(ids: List<UUID>): List<Store>
}