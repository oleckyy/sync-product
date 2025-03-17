package com.kumquat.syncProductApi.domain.model.user

import java.util.*

object DeactivateUserCommandFixtures {
    fun withCompleteData(
        id: UUID = UUID.randomUUID()
    ) = DeactivateUserCommand(
        id = id
    )
}