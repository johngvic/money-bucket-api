package br.com.moneybucket.entity

import br.com.moneybucket.enums.users.Role
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
data class User (
    @Id
    var id: UUID,
    var name: String,
    var login: String,
    @Column(name = "password")
    var credential: String,
    @Enumerated(EnumType.STRING)
    var role: Role
) : UserDetails {

    constructor(name: String, login: String, password: String, role: Role) : this(UUID.randomUUID(), name, login, password, role)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val adminAuthority = SimpleGrantedAuthority("ROLE_ADMIN")
        val userAuthority = SimpleGrantedAuthority("ROLE_USER")

        return if (role == Role.ADMIN) {
            mutableListOf(adminAuthority, userAuthority)
        } else {
            mutableListOf(userAuthority)
        }
    }

    override fun getPassword(): String {
        return credential
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}