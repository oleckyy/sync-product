package com.kumquat.syncProductApi.domain.model.store

import java.time.LocalDateTime
import java.util.*

data class Store(
    val id: UUID,
    val externalId: UUID,
    val active: Boolean,
    val name: String,
    val location: String,
    val apiUrl: String,
    val versionDate: LocalDateTime
)
