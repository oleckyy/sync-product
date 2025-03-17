package com.kumquat.syncProductApi.infrastructure.database.entity

import java.time.LocalDateTime
import java.util.*

object StoreEntityFixtures {
    fun withCompleteData(
        id: UUID? = UUID.randomUUID(),
        externalId: UUID = UUID.randomUUID(),
        active: Boolean = true,
        name: String = "Sample Store",
        location: String = "New York",
        apiUrl: String = "https://api.samplestore.com",
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = StoreEntity(
        id = id,
        externalId = externalId,
        active = active,
        name = name,
        location = location,
        apiUrl = apiUrl,
        versionDate = versionDate
    )
}