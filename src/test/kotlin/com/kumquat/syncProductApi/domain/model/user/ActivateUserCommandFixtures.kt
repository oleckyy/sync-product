package com.kumquat.syncProductApi.domain.model.user

import java.util.*

object ActivateUserCommandFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID()
    ) = ActivateUserCommand(
        id = id
    )
}