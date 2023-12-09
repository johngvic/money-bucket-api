package br.com.moneybucket.dto.finance_institutions.req

import jakarta.validation.constraints.NotNull

data class EditFinanceInstitutionRequest(
    @NotNull
    val name: String
)
