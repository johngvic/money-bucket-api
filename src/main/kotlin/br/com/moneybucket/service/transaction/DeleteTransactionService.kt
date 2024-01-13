package br.com.moneybucket.service.transaction

import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class DeleteTransactionService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(transactionId: UUID, username: String) {
        val response = transactionRepository
            .deleteByIdAndUsername(transactionId, username)

        if (response == 0) throw ResourceNotFoundException("Transaction not found")
    }
}