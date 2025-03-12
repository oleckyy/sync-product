package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.token

data class AuthToken(
    val login: String,
    val password: String,
)
