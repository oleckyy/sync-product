package com.kumquat.syncProductApi.infrastructure.database.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "PRODUCT")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @Column(nullable = false)
    val externalId: UUID,
    @Column(nullable = false)
    val storeId: UUID,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val priceNet: BigDecimal,
    @Column(nullable = false)
    val vatValue: BigDecimal,
    @Column(nullable = false)
    val versionDate: LocalDateTime = LocalDateTime.now(),
)
