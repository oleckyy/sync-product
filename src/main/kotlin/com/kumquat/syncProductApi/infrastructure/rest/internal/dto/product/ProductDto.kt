package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class ProductDto(
    val id: UUID,
    val externalId: UUID,
    val storeId: UUID,
    val name: String,
    val priceNet: BigDecimal,
    val vatValue: BigDecimal,
    val versionDate: LocalDateTime
)
