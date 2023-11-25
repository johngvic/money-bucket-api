package br.com.moneybucket.dto.users.req

import jakarta.validation.constraints.NotNull

data class AuthenticationRequest(
    @NotNull
    val login: String,
    @NotNull
    val password: String
)
