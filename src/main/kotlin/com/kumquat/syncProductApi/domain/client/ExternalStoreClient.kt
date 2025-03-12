package com.kumquat.syncProductApi.domain.client

import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore

interface ExternalStoreClient {
    fun getExternalStores(url: String): Result<List<ExternalStore>>
}