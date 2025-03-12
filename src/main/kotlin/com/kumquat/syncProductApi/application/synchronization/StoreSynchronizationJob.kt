package com.kumquat.syncProductApi.application.synchronization

import com.kumquat.syncProductApi.application.synchronization.store.StoreSynchronizationManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class StoreSynchronizationJob(
    private val storeSynchronizationManager: StoreSynchronizationManager
) {
    @Scheduled(initialDelay = 5_000, fixedDelay = 18_000_000)
    fun synchronize() {
        storeSynchronizationManager.synchronize()
    }
}