package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.DeactivateUserCommand
import com.kumquat.syncProductApi.domain.port.UserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeactivatingUser(
    private val userPort: UserPort,
) {
    fun deactivate(deactivateUserCommand: DeactivateUserCommand) {
        userPort.deactivate(deactivateUserCommand)
    }
}
