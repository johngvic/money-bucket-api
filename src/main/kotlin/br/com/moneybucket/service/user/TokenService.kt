package br.com.moneybucket.service.user;

import br.com.moneybucket.entity.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Service;
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {
    var secret = "SECRET"

    fun generateToken(user: User): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            val token = JWT.create()
                .withIssuer("money-bucket")
                .withSubject(user.login)
                .withExpiresAt(generateExpiration())
                .sign(algorithm)

            return token
        } catch (exception: JWTCreationException) {
            throw RuntimeException("error while generating JWT token", exception)
        }
    }

    fun validateToken(token: String): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                .withIssuer("money-bucket")
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            ""
        }
    }

    fun generateExpiration(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}
