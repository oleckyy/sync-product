package com.kumquat.syncProductApi.infrastructure.rest.internal.mapper

import com.kumquat.syncProductApi.domain.const.Privilege
import com.kumquat.syncProductApi.domain.model.user.ActivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.model.user.DeactivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommand
import com.kumquat.syncProductApi.infrastructure.database.entity.UserEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.CreateUserRequest
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.UpdateUserRequest
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.UserDto
import com.kumquat.syncProductApi.infrastructure.security.user.UserAuthorization
import java.util.*

fun UserEntity.toUserAuthorization() =
    UserAuthorization(
        id = id,
        active = active,
        login = login,
        password = password,
        privilege = privilege,
    )

fun UserEntity.toUserDto() =
    UserDto(
        id = id,
        active = active,
        login = login,
        privilege = privilege,
        versionDate = versionDate,
    )

fun CreateUserRequest.toCreateUserCommand() =
    CreateUserCommand(
        login = login,
        password = password,
        privilege = Privilege.fromType(privilege),
    )

fun UpdateUserRequest.toUpdateUserCommand() =
    UpdateUserCommand(
        id = id,
        login = login,
        password = password,
        privilege = Privilege.fromType(privilege),
    )

fun UUID.toActivateUserCommand() = ActivateUserCommand(id = this)

fun UUID.toDeactivateUserCommand() = DeactivateUserCommand(id = this)