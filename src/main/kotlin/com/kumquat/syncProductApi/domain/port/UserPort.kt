package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.user.ActivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.model.user.DeactivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommand

interface UserPort {
    fun create(createUserCommand: CreateUserCommand)

    fun update(updateUserCommand: UpdateUserCommand)

    fun activate(activateUserCommand: ActivateUserCommand)

    fun deactivate(deactivateUserCommand: DeactivateUserCommand)
}