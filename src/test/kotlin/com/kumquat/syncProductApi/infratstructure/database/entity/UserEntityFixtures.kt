package com.kumquat.syncProductApi.infratstructure.database.entity

import com.kumquat.syncProductApi.infrastructure.database.entity.UserEntity
import java.time.LocalDateTime
import java.util.*

object UserEntityFixtures {
    fun withCompleteData(
        id: UUID? = UUID.randomUUID(),
        active: Boolean = true,
        login: String = "user123",
        password: String = "password123",
        privilege: Int = 1,
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = UserEntity(
        id = id,
        active = active,
        login = login,
        password = password,
        privilege = privilege,
        versionDate = versionDate
    )
}