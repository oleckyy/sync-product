package com.kumquat.syncProductApi.domain.model.externalStore

import java.util.*

data class ExternalStore(
    val id: UUID,
    val active: Boolean,
    val name: String,
    val location: String,
    val apiUrl: String,
)
