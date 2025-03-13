package com.kumquat.syncProductApi.application.synchronization

import com.kumquat.syncProductApi.application.synchronization.product.ProductSynchronizationManager
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ProductSynchronizationJob(
    private val productSynchronizationManager: ProductSynchronizationManager
) {

    @Scheduled(initialDelay = 20_000, fixedDelay = 100_000)
    fun synchronizeProducts() {
        runBlocking {
            productSynchronizationManager.synchronize()
        }
    }
}