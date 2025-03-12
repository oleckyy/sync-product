package com.kumquat.syncProductApi.infrastructure.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationHandler(
    private val authenticationManager: AuthenticationManager,
) {
    fun authenticate(usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken): Authentication {
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        return authentication
    }
}
