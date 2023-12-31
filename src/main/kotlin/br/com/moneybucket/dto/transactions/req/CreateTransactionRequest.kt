package br.com.moneybucket.dto.transactions.req

import br.com.moneybucket.enums.transactions.Type
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CreateTransactionRequest(
    @NotNull
    val title: String,
    @NotNull
    val type: Type,
    @NotNull
    val category: UUID,
    @NotNull
    val financeInstitution: UUID,
    @NotNull
    val date: LocalDate,
    @NotNull
    val value: BigDecimal
)