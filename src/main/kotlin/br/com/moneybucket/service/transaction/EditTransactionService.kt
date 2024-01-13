package br.com.moneybucket.service.transaction

import br.com.moneybucket.enums.transactions.Type
import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Service
class EditTransactionService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(
        transactionId: UUID,
        username: String,
        title: String?,
        type: Type?,
        category: UUID?,
        financeInstitution: UUID?,
        date: LocalDate?,
        value: BigDecimal?
    ) {
        val entity =
            transactionRepository.findByIdAndUsername(transactionId, username) ?:
            throw ResourceNotFoundException("Transaction not found")

        title?.let { entity.title = it }
        type?.let { entity.type = it }
        category?.let { entity.category = it }
        financeInstitution?.let { entity.financeInstitution = it }
        date?.let { entity.date = it }
        value?.let { entity.value = it }

        transactionRepository.save(entity);
    }
}