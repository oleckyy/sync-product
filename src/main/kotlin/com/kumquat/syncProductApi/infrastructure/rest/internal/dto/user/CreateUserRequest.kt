package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user

data class CreateUserRequest(
    val login: String,
    val password: String,
    val privilege: Int,
)
