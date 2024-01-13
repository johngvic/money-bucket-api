package br.com.moneybucket.service.transaction

import br.com.moneybucket.entity.Transaction
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetTransactionByIdService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(transactionId: UUID, username: String): Transaction? {
        return transactionRepository.findByIdAndUsername(transactionId, username)
    }
}