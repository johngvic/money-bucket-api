package br.com.moneybucket.controller

import br.com.moneybucket.dto.users.req.AuthenticationRequest
import br.com.moneybucket.dto.users.req.RegisterRequest
import br.com.moneybucket.dto.users.res.AuthenticationResponse
import br.com.moneybucket.entity.User
import br.com.moneybucket.repository.UserRepository
import br.com.moneybucket.service.user.TokenService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthenticationController(
    @Autowired
    private val authenticationManager: AuthenticationManager,
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val tokenService: TokenService
) {
    @PostMapping("/login")
    fun login(
        @RequestBody
        @Valid
        request: AuthenticationRequest
    ): ResponseEntity<AuthenticationResponse> {
        val credentials = UsernamePasswordAuthenticationToken(request.login, request.password)
        val auth = this.authenticationManager.authenticate(credentials)
        val token = tokenService.generateToken(auth.principal as User)
        return ResponseEntity.ok(AuthenticationResponse(token))
    }

    @PostMapping("/register")
    fun register(
        @RequestBody
        @Valid
        request: RegisterRequest
    ): ResponseEntity<Unit> {
        if (userRepository.findByLogin(request.login).isPresent) {
            return ResponseEntity.badRequest().build()
        }
        val encryptedPassword = BCryptPasswordEncoder().encode(request.password)
        val user = User(request.name, request.login, encryptedPassword, request.role)
        userRepository.save(user)
        return ResponseEntity.ok().build()
    }
}