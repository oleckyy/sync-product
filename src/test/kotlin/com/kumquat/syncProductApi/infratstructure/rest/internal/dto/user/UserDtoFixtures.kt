package com.kumquat.syncProductApi.infratstructure.rest.internal.dto.user

import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.UserDto
import java.time.LocalDateTime
import java.util.*

object UserDtoFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        active: Boolean = true,
        login: String = "user123",
        privilege: Int = 1,
        versionDate: LocalDateTime = LocalDateTime.now()
    ) = UserDto(
        id = id,
        active = active,
        login = login,
        privilege = privilege,
        versionDate = versionDate
    )
}