package com.kumquat.syncProductApi.domain.model.user

import com.kumquat.syncProductApi.domain.const.Privilege

object CreateUserCommandFixtures {
    fun withCompleteData(
        login: String = "defaultUser",
        password: String = "SecurePassword123!",
        privilege: Privilege = Privilege.ADMIN
    ) = CreateUserCommand(
        login = login,
        password = password,
        privilege = privilege
    )
}