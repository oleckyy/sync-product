package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommandFixtures
import com.kumquat.syncProductApi.domain.port.UserPort
import io.mockk.*
import org.junit.jupiter.api.Test

class UpdatingUserTest {
    private val userPort: UserPort = mockk()
    private val updatingUser = UpdatingUser(userPort)

    @Test
    fun `should update user`() {
        //given
        val updateUserCommand = UpdateUserCommandFixtures.withCompleteData()
        every { userPort.update(any()) } just Runs

        //when
        updatingUser.update(updateUserCommand)

        //then
        verify(exactly = 1) { userPort.update(updateUserCommand) }
    }
}