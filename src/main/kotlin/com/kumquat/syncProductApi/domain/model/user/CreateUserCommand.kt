package com.kumquat.syncProductApi.domain.model.user

import com.kumquat.syncProductApi.domain.const.Privilege

data class CreateUserCommand(
    val login: String,
    val password: String,
    val privilege: Privilege,
) {
    override fun toString(): String {
        return "CreateUserCommand(login = $login, privilegeTypeValue = $privilege)"
    }
}

