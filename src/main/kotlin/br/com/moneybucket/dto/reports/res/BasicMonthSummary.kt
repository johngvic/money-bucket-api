package br.com.moneybucket.dto.reports.res

import java.math.BigDecimal

data class BasicMonthSummary(
    val balance: BigDecimal,
    val income: BigDecimal,
    val expense: BigDecimal
)
