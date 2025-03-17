package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user

object CreateUserRequestFixtures {
    fun withCompleteData(
        login: String = "sampleUser",
        password: String = "password123",
        privilege: Int = 1
    ) = CreateUserRequest(
        login = login,
        password = password,
        privilege = privilege
    )
}