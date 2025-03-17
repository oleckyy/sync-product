package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user

import java.util.*

object UpdateUserRequestFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        login: String = "updatedUser",
        password: String = "newPassword123",
        privilege: Int = 2
    ) = UpdateUserRequest(
        id = id,
        login = login,
        password = password,
        privilege = privilege
    )
}