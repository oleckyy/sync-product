package com.kumquat.syncProductApi.infrastructure.database.temporaryModels

import java.time.LocalDateTime
import java.util.UUID

data class StoreVersionInfo(
    val storeId: UUID,
    val latestSyncDateTime: LocalDateTime,
)
