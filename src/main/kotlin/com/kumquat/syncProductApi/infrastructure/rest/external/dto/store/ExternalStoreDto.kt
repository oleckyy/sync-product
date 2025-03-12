package com.kumquat.syncProductApi.infrastructure.rest.external.dto.store

 import java.time.LocalDateTime
 import java.util.*

data class ExternalStoreDto(
    val id: UUID,
    val active: Boolean,
    val name: String,
    val location: String,
    val apiUrl: String,
    val versionDate: LocalDateTime
)
