package br.com.moneybucket.dto.users.req

import br.com.moneybucket.enums.users.Role
import jakarta.validation.constraints.NotNull

data class RegisterRequest(
    @NotNull
    val name: String,
    @NotNull
    val login: String,
    @NotNull
    val password: String,
    @NotNull
    val role: Role
)
