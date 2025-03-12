package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommand
import com.kumquat.syncProductApi.infrastructure.database.entity.UserEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserAdapterMapper {
    fun toCreatedUserEntity(
        createUserCommand: CreateUserCommand,
        encodedPassword: String,
    ) = UserEntity(
        active = true,
        login = createUserCommand.login,
        password = encodedPassword,
        privilege = createUserCommand.privilege.type,
    )

    fun toUpdatedUserEntity(
        updateUserCommand: UpdateUserCommand,
        userEntity: UserEntity,
        newPassword: String?,
    ) = userEntity.copy(
        login = updateUserCommand.login,
        password = newPassword?.takeIf { it.isNotBlank() } ?: userEntity.password,
        privilege = updateUserCommand.privilege.type,
        versionDate = LocalDateTime.now(),
    )

    fun toActivatedUserEntity(userEntity: UserEntity) =
        userEntity.copy(
            active = true,
            versionDate = LocalDateTime.now(),
        )

    fun toDeactivatedUserEntity(userEntity: UserEntity) =
        userEntity.copy(
            active = false,
            versionDate = LocalDateTime.now(),
        )
}