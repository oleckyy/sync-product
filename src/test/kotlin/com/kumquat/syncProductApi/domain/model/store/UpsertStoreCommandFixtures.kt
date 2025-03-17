package com.kumquat.syncProductApi.domain.model.store

import java.util.*

object UpsertStoreCommandFixtures {
    fun withCompleteData(
        externalId: UUID = UUID.randomUUID(),
        active: Boolean = true,
        name: String = "Testowy Sklep",
        location: String = "Warszawa",
        apiUrl: String = "https://api.example.com"
    ) = UpsertStoreCommand(
        externalId = externalId,
        active = active,
        name = name,
        location = location,
        apiUrl = apiUrl
    )
}