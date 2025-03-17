package com.kumquat.syncProductApi.infratstructure.database.entity

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

object ProductEntityFixtures {
    fun withCompleteData(
        id: UUID? = UUID.randomUUID(),
        externalId: UUID = UUID.randomUUID(),
        storeId: UUID = UUID.randomUUID(),
        name: String = "Sample Product",
        priceNet: BigDecimal = BigDecimal("100.00"),
        vatValue: BigDecimal = BigDecimal("23.00"),
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = ProductEntity(
        id = id,
        externalId = externalId,
        storeId = storeId,
        name = name,
        priceNet = priceNet,
        vatValue = vatValue,
        versionDate = versionDate
    )
}