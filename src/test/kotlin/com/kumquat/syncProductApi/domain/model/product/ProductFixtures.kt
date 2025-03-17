package com.kumquat.syncProductApi.domain.model.product

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

object ProductFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        externalId: UUID = UUID.randomUUID(),
        storeId: UUID = UUID.randomUUID(),
        name: String = "Testowy Produkt",
        priceNet: BigDecimal = BigDecimal("99.99"),
        vatValue: BigDecimal = BigDecimal("23.00"),
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = Product(
        id = id,
        externalId = externalId,
        storeId = storeId,
        name = name,
        priceNet = priceNet,
        vatValue = vatValue,
        versionDate = versionDate
    )
}