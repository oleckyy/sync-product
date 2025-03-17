package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.model.user.CreateUserCommandFixtures
import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommandFixtures
import com.kumquat.syncProductApi.infrastructure.database.entity.UserEntityFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class UserAdapterMapperTest {
    private val userAdapterMapper = UserAdapterMapper()

    @Test
    fun `should map to CreatedUserEntity`() {
        //given
        val createUserCommand = CreateUserCommandFixtures.withCompleteData()
        val encodedPassword = "dsagf1241dsgvfsadfasd"

        //when
        val result = userAdapterMapper.toCreatedUserEntity(createUserCommand,encodedPassword)

        //then
        assertThat(result.id).isNotNull()
        assertThat(result.active).isEqualTo(true)
        assertThat(result.login).isEqualTo(createUserCommand.login)
        assertThat(result.password).isEqualTo(encodedPassword)
        assertThat(result.privilege).isEqualTo(createUserCommand.privilege.type)
    }

    @Test
    fun `should map to UpdatedUserEntity`() {
        //given
        val updateUserCommand = UpdateUserCommandFixtures.withCompleteData()
        val userEntity = UserEntityFixtures.withCompleteData()
        val encodedPassword = "dsagf1241dsgvfsadfasd"

        //when
        val result = userAdapterMapper.toUpdatedUserEntity(updateUserCommand,userEntity,encodedPassword)

        //then
        assertThat(result.id).isEqualTo(userEntity.id)
        assertThat(result.active).isEqualTo(userEntity.active)
        assertThat(result.login).isEqualTo(updateUserCommand.login)
        assertThat(result.password).isEqualTo(encodedPassword)
        assertThat(result.privilege).isEqualTo(updateUserCommand.privilege.type)
        assertThat(result.versionDate).isNotNull()
    }

    @Test
    fun `should map to ActivatedUserEntity`() {
        //given
        val userEntity = UserEntityFixtures.withCompleteData()

        //when
        val result = userAdapterMapper.toActivatedUserEntity(userEntity)

        //then
        assertThat(result.active).isEqualTo(true)
        assertThat(result.versionDate).isAfter(userEntity.versionDate)
    }

    @Test
    fun `should map to DeactivatedUserEntity`() {
        //given
        val userEntity = UserEntityFixtures.withCompleteData()

        //when
        val result = userAdapterMapper.toDeactivatedUserEntity(userEntity)

        //then
        assertThat(result.active).isEqualTo(false)
        assertThat(result.versionDate).isAfter(userEntity.versionDate)
    }
}