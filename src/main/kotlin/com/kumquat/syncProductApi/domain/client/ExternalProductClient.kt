package com.kumquat.syncProductApi.domain.client

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProduct
import com.kumquat.syncProductApi.domain.model.store.Store
import java.time.LocalDateTime

interface ExternalProductClient {
    fun getExternalProducts(
        store: Store,
        storeLastSyncDate: LocalDateTime
    ): Result<List<ExternalProduct>>
}