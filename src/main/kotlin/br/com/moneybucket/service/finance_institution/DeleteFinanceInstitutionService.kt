package br.com.moneybucket.service.finance_institution

import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.FinanceInstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class DeleteFinanceInstitutionService(
    @Autowired
    private val financeInstitutionRepository: FinanceInstitutionRepository
) {
    fun invoke(financeInstitutionId: UUID, username: String) {
        val response = financeInstitutionRepository
            .deleteByIdAndUsername(financeInstitutionId, username)

        if (response == 0) throw ResourceNotFoundException("Finance institution not found")
    }
}