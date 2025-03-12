package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.ActivateUserCommand
import com.kumquat.syncProductApi.domain.port.UserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ActivatingUser(
    private val userPort: UserPort,
) {
    fun activate(activateUserCommand: ActivateUserCommand) {
        userPort.activate(activateUserCommand)
    }
}
