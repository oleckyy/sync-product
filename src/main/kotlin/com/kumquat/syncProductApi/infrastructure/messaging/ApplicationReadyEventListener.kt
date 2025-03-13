package com.kumquat.syncProductApi.infrastructure.messaging

import com.kumquat.syncProductApi.domain.accessor.ProductSynchronizationAccessor
import com.kumquat.syncProductApi.infrastructure.rest.internal.service.ProductService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ApplicationReadyEventListener(
    private val productSynchronizationAccessor: ProductSynchronizationAccessor,
    private val productService: ProductService
) {

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationStart() {
        productService.getLatestSyncDateForStores(DEFAULT_SYNC_DATE_TIME).entries
            .map { productSynchronizationAccessor.putProductSyncDateByStoreId(it.key, it.value) }
    }

    companion object {
        private val DEFAULT_SYNC_DATE_TIME = LocalDateTime.of(1970, 1, 1, 21, 37)
    }
}