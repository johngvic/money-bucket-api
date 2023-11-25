package br.com.moneybucket.service.user

import br.com.moneybucket.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    @Autowired private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(login: String): UserDetails? {
        return userRepository.findByLogin(login).get()
    }
}