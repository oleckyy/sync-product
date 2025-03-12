package com.kumquat.syncProductApi.infrastructure.security

import com.kumquat.syncProductApi.util.logger
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*

@Component
class JwtTokenProvider {
    fun generateToken(authentication: Authentication): String {
        val user = authentication.principal as User
        val expirationDate = Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS)
        return Jwts.builder()
            .subject(user.username)
            .issuedAt(Date())
            .expiration(expirationDate)
            .signWith(SECRET_KEY, Jwts.SIG.HS512)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    fun validateToken(authToken: String): Boolean {
        return runCatching {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(authToken)
        }.fold(
            onSuccess = {
                true
            },
            onFailure = { exception ->
                when (exception) {
                    is SignatureException -> {
                        log.warn("Invalid JWT signature")
                        false
                    }

                    is ExpiredJwtException -> {
                        log.warn("Expired JWT token")
                        false
                    }

                    is MalformedJwtException -> {
                        log.warn("Invalid JWT token")
                        false
                    }

                    is UnsupportedJwtException -> {
                        log.warn("Unsupported JWT token")
                        false
                    }

                    is IllegalArgumentException -> {
                        log.warn("JWT claims string is empty.")
                        false
                    }

                    else -> {
                        log.warn("JWT token not validated! Unknown Exception was thrown")
                        false
                    }
                }
            },
        )
    }

    companion object {
        const val JWT_EXPIRATION_IN_MS = 28_800_000
        const val JWT_SECRET_BASE_64 =
            "4/8R1heOMF7k52BIttOMNMbiE3Lz+zgJSsiRwy4QfC/N7bMFKw6UWgXvSzHz8HxzalBgwQHFuk3GM2t29YrIbQ=="
        val SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET_BASE_64))
        private val log by logger()
    }
}
