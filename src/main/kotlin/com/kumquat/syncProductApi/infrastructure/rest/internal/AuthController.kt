package com.kumquat.syncProductApi.infrastructure.rest.internal

import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.token.AuthToken
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.token.TokenResponse
import com.kumquat.syncProductApi.infrastructure.security.JwtAuthenticationHandler
import com.kumquat.syncProductApi.infrastructure.security.JwtTokenProvider
import com.kumquat.syncProductApi.util.logger
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtAuthenticationHandler: JwtAuthenticationHandler,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @PostMapping("/token")
    fun postAuthToken(
        @RequestBody authToken: AuthToken,
    ): ResponseEntity<*> {
        log.info("[AUTH] Authorizing user: ${authToken.login}")
        val authentication =
            jwtAuthenticationHandler.authenticate(
                UsernamePasswordAuthenticationToken(
                    authToken.login,
                    authToken.password,
                ),
            )
        val jwt = jwtTokenProvider.generateToken(authentication)
        return ResponseEntity.ok(jwt.toTokenResponse())
    }

    private fun String.toTokenResponse(): TokenResponse {
        return TokenResponse(
            token = this
        )
    }

    companion object {
        private val log by logger()
    }
}