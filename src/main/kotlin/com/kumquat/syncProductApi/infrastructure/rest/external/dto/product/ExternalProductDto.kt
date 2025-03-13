package com.kumquat.syncProductApi.infrastructure.rest.external.dto.product

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ExternalProductDto(
    val id: UUID,
    val name: String,
    val priceNet: BigDecimal,
    val vatValue: BigDecimal,
    val versionDate: LocalDateTime,
)
