package com.kumquat.syncProductApi.infrastructure.adapter

import com.kumquat.syncProductApi.domain.model.user.ActivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.CreateUserCommand
import com.kumquat.syncProductApi.domain.model.user.DeactivateUserCommand
import com.kumquat.syncProductApi.domain.model.user.UpdateUserCommand
import com.kumquat.syncProductApi.domain.port.UserPort
import com.kumquat.syncProductApi.infrastructure.adapter.mapper.UserAdapterMapper
import com.kumquat.syncProductApi.infrastructure.database.UserEntityRepository
import com.kumquat.syncProductApi.util.logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAdapter(
    private val userEntityRepository: UserEntityRepository,
    private val userAdapterMapper: UserAdapterMapper,
    private val passwordEncoder: PasswordEncoder
) : UserPort {

    override fun create(createUserCommand: CreateUserCommand) {
        log.info("[USERS] Creating user: $createUserCommand")
        val encodedPassword = createUserCommand.password.let { passwordEncoder.encode(it) }
        val userEntityToCreate = userAdapterMapper.toCreatedUserEntity(createUserCommand, encodedPassword)
        val savedUserEntity = userEntityRepository.save(userEntityToCreate)
        log.info("[USERS] User created: $savedUserEntity")
    }

    override fun update(updateUserCommand: UpdateUserCommand) {
        log.info("[USERS] Updating user: $updateUserCommand")
        val newPassword =
            updateUserCommand.password
                ?.takeIf { it.isNotEmpty() && it.isNotBlank() }
                ?.let { passwordEncoder.encode(it) }
        userEntityRepository.findByIdOrNull(updateUserCommand.id)
            ?.let { userEntityToUpdate ->
                val updatedUserEntity =
                    userAdapterMapper.toUpdatedUserEntity(updateUserCommand, userEntityToUpdate, newPassword)
                val savedUserEntity = userEntityRepository.save(updatedUserEntity)
                log.info("[USERS] User updated: $savedUserEntity")
            } ?: throw IllegalStateException("User with ID: [${updateUserCommand.id}] not found!")
    }

    override fun activate(activateUserCommand: ActivateUserCommand) {
        log.info("[USERS] Activating user with ID: ${activateUserCommand.id}")
        userEntityRepository.findByIdOrNull(activateUserCommand.id)
            ?.let { userEntityToActivate ->
                val activatedUserEntity = userAdapterMapper.toActivatedUserEntity(userEntityToActivate)
                val savedUserEntity = userEntityRepository.save(activatedUserEntity)
                log.info("[USERS] Activated user with ID: $savedUserEntity")
            } ?: throw IllegalStateException("User with ID: [${activateUserCommand.id}] not found!")
    }

    override fun deactivate(deactivateUserCommand: DeactivateUserCommand) {
        log.info("[USERS] Deactivating user with ID: ${deactivateUserCommand.id}")
        userEntityRepository.findByIdOrNull(deactivateUserCommand.id)
            ?.let { userEntityToDeactivate ->
                val deactivatedUserEntity = userAdapterMapper.toDeactivatedUserEntity(userEntityToDeactivate)
                val savedUserEntity = userEntityRepository.save(deactivatedUserEntity)
                log.info("[USERS] Deactivated user with ID: $savedUserEntity")
            } ?: throw IllegalStateException("User with ID: [${deactivateUserCommand.id}] not found!")
    }

    companion object {
        private val log by logger()
    }
}