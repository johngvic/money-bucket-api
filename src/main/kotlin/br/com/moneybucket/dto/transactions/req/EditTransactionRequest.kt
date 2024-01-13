package br.com.moneybucket.dto.transactions.req

import br.com.moneybucket.enums.transactions.Type
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class EditTransactionRequest(
    val title: String?,
    val type: Type?,
    val category: UUID?,
    val financeInstitution: UUID?,
    val date: LocalDate?,
    val value: BigDecimal?
)
