package br.com.moneybucket.dto.reports.res

data class MonthlySummary(
    var data: BasicMonthSummary?,
    var byCategory: Map<String, MutableMap<String, BasicMonthSummary>>?,
    var byFinanceInstitution: Map<String, MutableMap<String, BasicMonthSummary>>?
)
