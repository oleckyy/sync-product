package com.kumquat.syncProductApi.application.usecase.user

import com.kumquat.syncProductApi.domain.model.user.CreateUserCommandFixtures
import com.kumquat.syncProductApi.domain.port.UserPort
import io.mockk.*
import org.junit.jupiter.api.Test

class CreatingUserTest {
    private val userPort: UserPort = mockk()
    private val creatingUser = CreatingUser(userPort)

    @Test
    fun `should create user`() {
        //given
        val createUserCommand = CreateUserCommandFixtures.withCompleteData()
        every { userPort.create(any()) } just Runs

        //when
        creatingUser.create(createUserCommand)

        //then
        verify(exactly = 1) { userPort.create(createUserCommand) }
    }
}