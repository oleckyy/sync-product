package com.kumquat.syncProductApi.infratstructure.rest.external.dto.product

import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductDto
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

object ExternalProductDtoFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        name: String = "Product Name",
        priceNet: BigDecimal = BigDecimal("100.00"),
        vatValue: BigDecimal = BigDecimal("23.00"),
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ExternalProductDto(
        id = id,
        name = name,
        priceNet = priceNet,
        vatValue = vatValue,
        versionDate = versionDate
    )
}