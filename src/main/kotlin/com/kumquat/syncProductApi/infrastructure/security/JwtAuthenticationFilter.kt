package com.kumquat.syncProductApi.infrastructure.security

import com.kumquat.syncProductApi.domain.const.Privilege
import com.kumquat.syncProductApi.infrastructure.rest.internal.service.UserService
import com.kumquat.syncProductApi.util.logger
import io.micrometer.common.util.StringUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
class JwtAuthenticationFilter(
    val tokenProvider: JwtTokenProvider,
    val userService: UserService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        runCatching {
            val jwt = getJwtFromRequest(request)
            if (Objects.nonNull(jwt) && jwt!!.isNotBlank() && tokenProvider.validateToken(jwt)) {
                val username = tokenProvider.getUsernameFromToken(jwt)
                val userDetails = userService.fetchByLoginOrThrow(username)
                val userPrivileges =
                    SimpleGrantedAuthority(Privilege.fromType(userDetails.privilege).value)
                val authorities =
                    if (userDetails.privilege == Privilege.ADMIN.type) {
                        Privilege.entries.map { SimpleGrantedAuthority(it.value) }
                    } else {
                        setOf(userPrivileges)
                    }
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }.onFailure { exception -> log.warn("Could not set user authentication in security context $exception") }
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }

    companion object {
        private val log by logger()
    }
}
