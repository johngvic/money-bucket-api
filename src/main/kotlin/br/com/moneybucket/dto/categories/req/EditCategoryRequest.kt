package br.com.moneybucket.dto.categories.req

import jakarta.validation.constraints.NotNull

data class EditCategoryRequest(
    @NotNull
    val name: String
)
