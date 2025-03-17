package com.kumquat.syncProductApi.domain.model.store

import java.time.LocalDateTime
import java.util.*

object StoreFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        externalId: UUID = UUID.randomUUID(),
        active: Boolean = true,
        name: String = "Testowy Sklep",
        location: String = "Warszawa",
        apiUrl: String = "https://api.example.com",
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = Store(
        id = id,
        externalId = externalId,
        active = active,
        name = name,
        location = location,
        apiUrl = apiUrl,
        versionDate = versionDate
    )
}