package br.com.moneybucket.service.reports

import br.com.moneybucket.dto.reports.res.ReportDescription
import org.springframework.stereotype.Service

@Service
class GetAvailableReportsService {
    fun invoke(): List<ReportDescription> {
        val r1 = ReportDescription(
            "Monthly Summary",
            "Retrieves information about total of income and expenses for each month",
            "monthly-summary",
            true
        )
        val reports = listOf(r1);
        return reports
    }
}