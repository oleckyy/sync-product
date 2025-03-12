package com.kumquat.syncProductApi.domain.model.store

import java.util.*

data class UpsertStoreCommand(
    val externalId: UUID,
    val active: Boolean,
    val name: String,
    val location: String,
    val apiUrl: String,
)
