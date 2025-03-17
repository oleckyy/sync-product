package com.kumquat.syncProductApi.infratstructure.rest.internal.dto.product

import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductDto
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

object ProductDtoFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        externalId: UUID = UUID.randomUUID(),
        storeId: UUID = UUID.randomUUID(),
        name: String = "Sample Product",
        priceNet: BigDecimal = BigDecimal("99.99"),
        vatValue: BigDecimal = BigDecimal("19.00"),
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ProductDto(
        id = id,
        externalId = externalId,
        storeId = storeId,
        name = name,
        priceNet = priceNet,
        vatValue = vatValue,
        versionDate = versionDate
    )
}