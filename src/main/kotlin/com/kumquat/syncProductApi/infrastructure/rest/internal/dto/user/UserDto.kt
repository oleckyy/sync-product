package com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user

import java.time.LocalDateTime
import java.util.*

data class UserDto(
    val id: UUID,
    val active: Boolean,
    val login: String,
    val privilege: Int,
    val versionDate: LocalDateTime,
)
