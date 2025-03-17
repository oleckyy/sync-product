package com.kumquat.syncProductApi.domain.model.user

import com.kumquat.syncProductApi.domain.const.Privilege
import java.util.*

object UpdateUserCommandFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID(),
        login: String = "user123",
        password: String? = "securePassword123",
        privilege: Privilege = Privilege.USER
    ) = UpdateUserCommand(
        id = id,
        login = login,
        password = password,
        privilege = privilege
    )
}