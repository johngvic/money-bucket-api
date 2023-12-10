package br.com.moneybucket.dto.categories.req

import jakarta.validation.constraints.NotNull

data class CreateCategoryRequest(
    @NotNull
    val name: String
)