package com.kumquat.syncProductApi.domain.model.product

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class UpsertProductCommand(
    val id: UUID?,
    val externalId: UUID,
    val storeId: UUID,
    val name: String,
    val priceNet: BigDecimal,
    val vatValue: BigDecimal,
    val versionDate: LocalDateTime,
)
