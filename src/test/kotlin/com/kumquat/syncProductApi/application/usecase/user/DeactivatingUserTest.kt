package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.DeactivateUserCommandFixtures
import com.kumquat.syncProductApi.domain.port.UserPort
import io.mockk.*
import org.junit.jupiter.api.Test

class DeactivatingUserTest {
    private val userPort: UserPort = mockk()
    private val deactivatingUser = DeactivatingUser(userPort)

    @Test
    fun `should deactivate user`() {
        //given
        val deactivateUserCommand = DeactivateUserCommandFixtures.withCompleteData()
        every { userPort.deactivate(any()) } just Runs

        //when
        deactivatingUser.deactivate(deactivateUserCommand)

        //then
        verify(exactly = 1) { userPort.deactivate(deactivateUserCommand) }
    }
}