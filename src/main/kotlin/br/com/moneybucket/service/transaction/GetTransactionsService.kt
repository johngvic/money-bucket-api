package br.com.moneybucket.service.transaction

import br.com.moneybucket.entity.Transaction
import br.com.moneybucket.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetTransactionsService(
    @Autowired
    private val transactionRepository: TransactionRepository
) {
    fun invoke(username: String): List<Transaction> {
        val response = transactionRepository.findByUsername(username)
        return response
    }
}