package br.com.moneybucket.repository

import br.com.moneybucket.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransactionRepository : JpaRepository<Transaction, UUID> {
}