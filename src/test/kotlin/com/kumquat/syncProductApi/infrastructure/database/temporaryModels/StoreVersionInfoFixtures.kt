package com.kumquat.syncProductApi.infrastructure.database.temporaryModels

import java.time.LocalDateTime
import java.util.*

object StoreVersionInfoFixtures {
    fun withCompleteData(
        storeId: UUID = UUID.randomUUID(),
        latestSyncDateTime: LocalDateTime = LocalDateTime.now()
    ) = StoreVersionInfo(
        storeId = storeId,
        latestSyncDateTime = latestSyncDateTime
    )
}