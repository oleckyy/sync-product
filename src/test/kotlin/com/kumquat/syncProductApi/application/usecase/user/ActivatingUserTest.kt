package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.ActivateUserCommandFixtures
import com.kumquat.syncProductApi.domain.port.UserPort
import io.mockk.*
import org.junit.jupiter.api.Test

class ActivatingUserTest {
    private val userPort: UserPort = mockk()
    private val activatingUser = ActivatingUser(userPort)

    @Test
    fun `should activate user`() {
        //given
        val activateUserCommand = ActivateUserCommandFixtures.withCompleteData()
        every { userPort.activate(any()) } just Runs

        //when
        activatingUser.activate(activateUserCommand)

        //then
        verify(exactly = 1) { userPort.activate(activateUserCommand) }
    }
}