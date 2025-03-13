package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product

import java.time.LocalDateTime
import java.util.*

data class ProductFiltersDto(
    val name: String?,
    val storeIds: List<UUID>?,
    val versionDateFrom: LocalDateTime?,
    val versionDateTo: LocalDateTime?,
)
