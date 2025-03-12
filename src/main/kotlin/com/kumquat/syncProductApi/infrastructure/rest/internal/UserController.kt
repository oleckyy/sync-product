package com.kumquat.syncProductApi.infrastructure.rest.internal

import com.kumquat.syncProductApi.application.usecase.user.ActivatingUser
import com.kumquat.syncProductApi.application.usecase.user.CreatingUser
import com.kumquat.syncProductApi.application.usecase.user.DeactivatingUser
import com.kumquat.syncProductApi.application.usecase.user.UpdatingUser
import com.kumquat.syncProductApi.domain.const.MessageType
import com.kumquat.syncProductApi.domain.const.Privilege.Companion.ROLE_ADMIN
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.Response
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.CreateUserRequest
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.user.UpdateUserRequest
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toActivateUserCommand
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toCreateUserCommand
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toDeactivateUserCommand
import com.kumquat.syncProductApi.infrastructure.rest.internal.mapper.toUpdateUserCommand
import com.kumquat.syncProductApi.infrastructure.rest.internal.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val creatingUser: CreatingUser,
    private val updatingUser: UpdatingUser,
    private val activatingUser: ActivatingUser,
    private val deactivatingUser: DeactivatingUser,
) {
    @GetMapping
    @Secured(ROLE_ADMIN)
    fun findAll(): ResponseEntity<*> = ResponseEntity.ok(userService.findAll())

    @GetMapping("/{id}")
    @Secured(ROLE_ADMIN)
    fun findById(
        @PathVariable id: UUID,
    ): ResponseEntity<*> = ResponseEntity.ok(userService.fetchByIdOrThrow(id))

    @PostMapping
    @Secured(ROLE_ADMIN)
    fun create(
        @RequestBody createUserRequest: CreateUserRequest,
    ): ResponseEntity<*> =
        creatingUser.create(createUserRequest.toCreateUserCommand())
            .let { ResponseEntity.ok(Response.withMessage(MessageType.USER_CREATED)) }

    @PatchMapping
    @Secured(ROLE_ADMIN)
    fun update(
        @RequestBody updateUserRequest: UpdateUserRequest,
    ): ResponseEntity<*> =
        updatingUser.update(updateUserRequest.toUpdateUserCommand())
            .let { ResponseEntity.ok(Response.withMessage(MessageType.USER_UPDATED)) }

    @PatchMapping("/{id}/activate")
    @Secured(ROLE_ADMIN)
    fun activateUser(
        @PathVariable id: UUID,
    ): ResponseEntity<*> =
        activatingUser.activate(id.toActivateUserCommand())
            .let { ResponseEntity.ok(Response.withMessage(MessageType.USER_ACTIVATED)) }

    @PatchMapping("/{id}/deactivate")
    @Secured(ROLE_ADMIN)
    fun deactivateUser(
        @PathVariable id: UUID,
    ): ResponseEntity<*> =
        deactivatingUser.deactivate(id.toDeactivateUserCommand())
            .let { ResponseEntity.ok(Response.withMessage(MessageType.USER_DEACTIVATED)) }
}