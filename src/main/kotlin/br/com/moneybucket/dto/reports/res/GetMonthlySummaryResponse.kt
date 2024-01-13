package br.com.moneybucket.dto.reports.res

data class GetMonthlySummaryResponse(
    val reportName: String? = "MonthlyFinancialSummary",
    val year: Int,
    val data: BasicMonthSummary?,
    val byCategory: Map<String, MutableMap<String, BasicMonthSummary>>?,
    val byFinanceInstitution: Map<String, MutableMap<String, BasicMonthSummary>>?
)
