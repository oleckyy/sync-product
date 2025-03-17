package com.kumquat.syncProductApi.infratstructure.database.temporaryModels

import com.kumquat.syncProductApi.infrastructure.database.temporaryModels.StoreVersionInfo
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