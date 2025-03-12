package com.kumquat.syncProductApi.domain.model.user

import com.kumquat.syncProductApi.domain.const.Privilege
import java.util.*

data class UpdateUserCommand(
    val id: UUID,
    val login: String,
    val password: String?,
    val privilege: Privilege,
) {
    override fun toString(): String {
        return "UpdateUserCommand(id = $id, login = $login, privilegeTypeValue = $privilege)"
    }
}
