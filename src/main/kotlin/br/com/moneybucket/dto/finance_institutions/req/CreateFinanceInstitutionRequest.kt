package br.com.moneybucket.dto.finance_institutions.req

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class CreateFinanceInstitutionRequest(
    @NotNull
    val name: String
)