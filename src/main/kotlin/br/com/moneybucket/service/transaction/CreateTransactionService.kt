package br.com.moneybucket.service.transaction

import br.com.moneybucket.entity.Transaction
import br.com.moneybucket.enums.transactions.Type
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Service
class CreateTransactionService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(
        username: String,
        title: String,
        type: Type,
        category: UUID,
        financeInstitution: UUID,
        date: LocalDate,
        value: BigDecimal
    ): UUID {
        val response = transactionRepository.save(
            Transaction(
                null, username, title, type, category, financeInstitution, date, value, LocalDateTime.now()))

        return response.id ?: throw Exception("Failed creating transaction")
    }
}