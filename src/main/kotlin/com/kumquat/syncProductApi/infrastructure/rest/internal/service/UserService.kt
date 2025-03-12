package com.kumquat.syncProductApi.infrastructure.rest.internal.service

import com.kumquat.syncProductApi.infrastructure.database.UserEntityRepository
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.UserDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toUserAuthorization
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toUserDto
import com.kumquat.syncProductApi.infrastructure.security.user.UserAuthorization
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userEntityRepository: UserEntityRepository
) {
    fun findAll(): List<UserDto> {
        return userEntityRepository.findAll()
            .map { it.toUserDto() }
    }

    fun fetchByLoginOrThrow(login: String): UserAuthorization {
        return userEntityRepository.findByLogin(login)?.toUserAuthorization()
            ?: throw IllegalStateException("User with given login: [$login] does not exist!")
    }

    fun fetchByIdOrThrow(id: UUID): UserDto {
        return userEntityRepository.findByIdOrNull(id)
            ?.toUserDto()
            ?: throw IllegalStateException("Nie odnaleziono użytkownika. ID: [$id]")
    }
}