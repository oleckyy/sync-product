package com.kumquat.syncProductApi.infrastructure.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
@Entity
@Table(name = "USER")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val active: Boolean,
    @Column(nullable = false, unique = true)
    val login: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val privilege: Int,
    @Column(nullable = false)
    val versionDate: LocalDateTime = LocalDateTime.now(),
)
