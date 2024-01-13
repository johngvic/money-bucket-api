package br.com.moneybucket.controller

import br.com.moneybucket.dto.reports.res.GetAvailableReportsResponse
import br.com.moneybucket.dto.reports.res.GetMonthlySummaryResponse
import br.com.moneybucket.service.reports.GetAvailableReportsService
import br.com.moneybucket.service.reports.GetMonthlySummaryService
import br.com.moneybucket.service.user.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("reports")
class ReportController(
    @Autowired
    private val tokenService: TokenService,
    private val getAvailableReportsService: GetAvailableReportsService,
    private val getMonthlySummaryService: GetMonthlySummaryService,
) {
    @GetMapping
    fun getAvailableReports(): ResponseEntity<GetAvailableReportsResponse> {
        val availableReports = getAvailableReportsService.invoke()
        return ResponseEntity.ok(GetAvailableReportsResponse(availableReports))
    }

    @GetMapping("/{year}/monthly-summary")
    fun getMonthlySummaryReport(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable year: Int,
        @RequestParam("month") month: Int?,
        @RequestParam("include_category") includeCategory: Boolean?,
        @RequestParam("include_fin_institution") includeFinanceInstitution: Boolean?,
    ): ResponseEntity<GetMonthlySummaryResponse> {
        val response = getMonthlySummaryService.invoke(year, month, includeCategory, includeFinanceInstitution)

        return ResponseEntity.ok(
            GetMonthlySummaryResponse(
                null,
                year,
                response.data,
                response.byCategory,
                response.byFinanceInstitution
            )
        )
    }
}