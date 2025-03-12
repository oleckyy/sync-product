package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user

import java.util.*

data class UpdateUserRequest(
    val id: UUID,
    val login: String,
    val password: String,
    val privilege: Int,
)
