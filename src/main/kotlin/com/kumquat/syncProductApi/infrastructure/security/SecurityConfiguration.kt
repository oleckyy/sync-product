package com.kumquat.syncProductApi.infrastructure.security

import com.kumquat.syncProductApi.domain.const.Privilege
import com.kumquat.syncProductApi.infrastructure.rest.internal.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userService: UserService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val passwordEncoder: PasswordEncoder,
) {
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(this::loadUserByUsername)
        authProvider.setPasswordEncoder(passwordEncoder)
        return authProvider
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint { _, response, authException ->
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Incorrect login or password!",
                        )
                    }
                    .accessDeniedHandler { _, response, accessDeniedException ->
                        response.sendError(
                            HttpServletResponse.SC_FORBIDDEN,
                            "${accessDeniedException.message}",
                        )
                    }
            }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST, "/auth/token").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    private fun loadUserByUsername(login: String): UserDetails {
        val user = userService.fetchByLoginOrThrow(login)
        val privilegeType = Privilege.fromType(user.privilege).value
        val authority = SimpleGrantedAuthority(privilegeType)
        val authorities =
            if (user.privilege == Privilege.ADMIN.type) {
                Privilege.entries.map { SimpleGrantedAuthority(it.value) }
            } else {
                setOf(authority)
            }
        return User(
            user.login,
            user.password,
            user.active,
            true,
            true,
            true,
            authorities,
        )
    }
}
