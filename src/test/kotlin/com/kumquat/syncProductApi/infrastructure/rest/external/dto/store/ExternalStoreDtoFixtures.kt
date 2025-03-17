package com.kumquat.syncProductApi.infrastructure.rest.external.dto.store

import java.time.LocalDateTime
import java.util.*

object ExternalStoreDtoFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        active: Boolean = true,
        name: String = "Store XYZ",
        location: String = "Location ABC",
        apiUrl: String = "http://api.storexyz.com",
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ExternalStoreDto(
        id = id,
        active = active,
        name = name,
        location = location,
        apiUrl = apiUrl,
        versionDate = versionDate
    )
}