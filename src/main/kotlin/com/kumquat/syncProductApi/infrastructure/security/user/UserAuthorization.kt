package com.kumquat.syncProductApi.infrastructure.security.user

import java.util.*

data class UserAuthorization(
    val id: UUID,
    val active: Boolean,
    val login: String,
    val password: String,
    val privilege: Int
)
