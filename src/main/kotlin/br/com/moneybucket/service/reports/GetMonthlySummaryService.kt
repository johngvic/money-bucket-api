package br.com.moneybucket.service.reports

import br.com.moneybucket.dto.reports.res.BasicMonthSummary
import br.com.moneybucket.dto.reports.res.MonthlySummary
import br.com.moneybucket.entity.Transaction
import br.com.moneybucket.enums.transactions.Type
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.YearMonth

@Service
class GetMonthlySummaryService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(
        year: Int,
        month: Int?,
        includeCategory: Boolean?,
        includeFinanceInstitution: Boolean?
    ): MonthlySummary {
        val summary = MonthlySummary(null, null, null)

        if (month != null) {
            val result = calculateByPeriod(month, year, transactionRepository)
            summary.data = result
        }

        if (includeCategory == true) {
            val byCategoryResult = mutableMapOf<String, MutableMap<String, BasicMonthSummary>>()
            for (iteratedMonth in 1..12) {
                val transactions = getTransactionsByPeriod(iteratedMonth, year, transactionRepository)

                for (transaction in transactions) {
                    val stringfiedMonth = transaction.date.month.toString()
                    val category = transaction.category.toString()

                    val current = byCategoryResult[stringfiedMonth]
                    val currentSummary = current?.get(category)
                    var income = currentSummary?.income ?: BigDecimal.ZERO
                    var expense = currentSummary?.expense ?: BigDecimal.ZERO

                    if (transaction.type == Type.INCOME) {
                        income = income.add(transaction.value)
                    } else {
                        expense = expense.add(transaction.value)
                    }

                    byCategoryResult.compute(stringfiedMonth) { _, monthInstance ->
                        if (monthInstance == null) {
                            mutableMapOf(category to BasicMonthSummary(income.subtract(expense), income, expense))
                        } else {
                            monthInstance[category] = BasicMonthSummary(income.subtract(expense), income, expense)
                            monthInstance
                        }
                    }
                }
            }
            summary.byCategory = byCategoryResult
        }

        if (includeFinanceInstitution == true) {
            val byFinanceInstitutionResult = mutableMapOf<String, MutableMap<String, BasicMonthSummary>>()
            for (iteratedMonth in 1..12) {
                val transactions = getTransactionsByPeriod(iteratedMonth, year, transactionRepository)

                for (transaction in transactions) {
                    val stringfiedMonth = transaction.date.month.toString()
                    val financeInstitution = transaction.financeInstitution.toString()

                    val current = byFinanceInstitutionResult[stringfiedMonth]
                    val currentSummary = current?.get(financeInstitution)
                    var income = currentSummary?.income ?: BigDecimal.ZERO
                    var expense = currentSummary?.expense ?: BigDecimal.ZERO

                    if (transaction.type == Type.INCOME) {
                        income = income.add(transaction.value)
                    } else {
                        expense = expense.add(transaction.value)
                    }

                    byFinanceInstitutionResult.compute(stringfiedMonth) { _, monthInstance ->
                        if (monthInstance == null) {
                            mutableMapOf(financeInstitution to BasicMonthSummary(income.subtract(expense), income, expense))
                        } else {
                            monthInstance[financeInstitution] = BasicMonthSummary(income.subtract(expense), income, expense)
                            monthInstance
                        }
                    }
                }
            }
            summary.byFinanceInstitution = byFinanceInstitutionResult
        }

        return summary
    }

    companion object {
        fun calculateByPeriod(month: Int, year: Int, repository: TransactionRepository): BasicMonthSummary {
            val yearMonth = YearMonth.of(year, month)
            val start = yearMonth.atDay(1)
            val end = yearMonth.atEndOfMonth()
            val transactions = repository.findByDateBetween(start, end)
            var income = BigDecimal.ZERO
            var expense = BigDecimal.ZERO

            for (transaction in transactions) {
                if (transaction.type == Type.INCOME) {
                    income = income.add(transaction.value)
                } else {
                    expense = expense.add(transaction.value)
                }
            }

            return BasicMonthSummary(income.subtract(expense), income, expense)
        }

        fun getTransactionsByPeriod(month: Int, year: Int, repository: TransactionRepository): List<Transaction> {
            val yearMonth = YearMonth.of(year, month)
            val start = yearMonth.atDay(1)
            val end = yearMonth.atEndOfMonth()
            return repository.findByDateBetween(start, end)
        }
    }
}