package com.kumquat.syncProductApi.domain.model.externalProduct

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

object ExternalProductFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        name: String = "Produkt Testowy",
        priceNet: BigDecimal = BigDecimal("100.00"),
        vatValue: BigDecimal = BigDecimal("23.00"),
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ExternalProduct(
        id = id,
        name = name,
        priceNet = priceNet,
        vatValue = vatValue,
        versionDate = versionDate
    )
}