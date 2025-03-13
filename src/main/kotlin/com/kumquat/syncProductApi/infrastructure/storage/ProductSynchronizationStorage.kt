package com.kumquat.syncProductApi.infrastructure.storage

import com.kumquat.syncProductApi.domain.accessor.ProductSynchronizationAccessor
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class ProductSynchronizationStorage(
    private val productLastSynchronizedVersionDates: MutableMap<UUID, LocalDateTime>
) : ProductSynchronizationAccessor {

    override fun getProductSyncDateByStoreId(storeId: UUID): LocalDateTime? =
        productLastSynchronizedVersionDates[storeId]

    override fun putProductSyncDateByStoreId(storeId: UUID, syncDateTime: LocalDateTime) {
        productLastSynchronizedVersionDates[storeId] = syncDateTime
    }
}