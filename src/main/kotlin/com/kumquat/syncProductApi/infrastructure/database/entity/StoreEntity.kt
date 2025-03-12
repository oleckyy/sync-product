package com.kumquat.syncProductApi.infrastructure.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "STORE")
data class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val externalId: UUID,
    @Column(nullable = false)
    val active: Boolean,
    @Column(nullable = false, unique = true)
    val name: String,
    @Column(nullable = false)
    val location: String,
    @Column(nullable = false)
    val apiUrl: String,
    @Column(nullable = false)
    val versionDate: LocalDateTime = LocalDateTime.now(),
)
