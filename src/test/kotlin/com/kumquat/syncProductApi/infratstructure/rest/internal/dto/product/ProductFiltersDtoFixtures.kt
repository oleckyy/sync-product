package com.kumquat.syncProductApi.infratstructure.rest.internal.dto.product

import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import java.time.LocalDateTime
import java.util.*

object ProductFiltersDtoFixtures {
    fun withCompleteData(
        name: String? = "Sample Product",
        storeIds: List<UUID>? = listOf(UUID.randomUUID(), UUID.randomUUID()),
        versionDateFrom: LocalDateTime? = LocalDateTime.now().minusDays(10),
        versionDateTo: LocalDateTime? = LocalDateTime.now()
    ) = ProductFiltersDto(
        name = name,
        storeIds = storeIds,
        versionDateFrom = versionDateFrom,
        versionDateTo = versionDateTo
    )
}