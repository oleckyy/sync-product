package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.port.UserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreatingUser(
    private val userPort: UserPort,
) {
    fun create(createUserCommand: CreateUserCommand) {
        userPort.create(createUserCommand)
    }
}
