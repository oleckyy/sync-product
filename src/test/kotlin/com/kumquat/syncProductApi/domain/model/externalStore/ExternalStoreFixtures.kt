package com.kumquat.syncProductApi.domain.model.externalStore

import java.time.LocalDateTime
import java.util.*

object ExternalStoreFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        active: Boolean = true,
        name: String = "Testowy Sklep",
        location: String = "Warszawa, Polska",
        apiUrl: String = "https://api.testowysklep.com",
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ExternalStore(
        id = id,
        active = active,
        name = name,
        location = location,
        apiUrl = apiUrl,
        versionDate = versionDate
    )

    fun withIdAndVersionDate(id: UUID, versionDate: LocalDateTime) =
        withCompleteData().copy(id = id, versionDate = versionDate)
}