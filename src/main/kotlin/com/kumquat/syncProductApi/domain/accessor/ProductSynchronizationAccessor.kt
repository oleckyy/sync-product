package com.kumquat.syncProductApi.domain.accessor

import java.time.LocalDateTime
import java.util.*

interface ProductSynchronizationAccessor {
    fun getProductSyncDateByStoreId(storeId: UUID): LocalDateTime?

    fun putProductSyncDateByStoreId(
        storeId: UUID,
        syncDateTime: LocalDateTime,
    )
}