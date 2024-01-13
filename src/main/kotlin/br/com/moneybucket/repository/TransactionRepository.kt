package br.com.moneybucket.repository

import br.com.moneybucket.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface TransactionRepository : JpaRepository<Transaction, UUID> {
    fun findByDateBetween(start: LocalDate, end: LocalDate): List<Transaction>
    fun findByUsername(user: String): List<Transaction>
    fun findByIdAndUsername(id: UUID, username: String): Transaction?
    fun deleteByIdAndUsername(id: UUID, username: String): Int
}