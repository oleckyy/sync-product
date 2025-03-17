package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommand

interface IncomingStoreDatabasePort {
    fun createStore(upsertStoreCommand: UpsertStoreCommand)

    fun updateStore(upsertStoreCommand: UpsertStoreCommand)
}