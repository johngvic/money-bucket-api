package br.com.moneybucket.dto.reports.res

data class ReportDescription(
    val title: String,
    val description: String,
    val endpoint: String,
    val enabled: Boolean
)
