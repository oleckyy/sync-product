package com.kumquat.syncProductApi.domain.model.externalProduct

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ExternalProduct(
    val id: UUID,
    val name: String,
    val priceNet: BigDecimal,
    val vatValue: BigDecimal,
    val versionDate: LocalDateTime,
)
