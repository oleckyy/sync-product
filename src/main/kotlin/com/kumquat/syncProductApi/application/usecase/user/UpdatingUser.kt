package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommand
import com.kumquat.syncProductApi.domain.port.UserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdatingUser(
    private val userPort: UserPort,
) {
    fun update(updateUserCommand: UpdateUserCommand) {
        userPort.update(updateUserCommand)
    }
}
