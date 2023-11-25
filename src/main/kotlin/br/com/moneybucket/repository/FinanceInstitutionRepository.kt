package br.com.moneybucket.repository

import br.com.moneybucket.entity.FinanceInstitution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FinanceInstitutionRepository : JpaRepository<FinanceInstitution, UUID> {
    fun findByUsername(user: String): List<FinanceInstitution>
    fun findByIdAndUsername(id: UUID, username: String): FinanceInstitution?
}