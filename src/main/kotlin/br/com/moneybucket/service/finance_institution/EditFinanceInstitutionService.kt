package br.com.moneybucket.service.finance_institution

import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.repository.FinanceInstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class EditFinanceInstitutionService(
    @Autowired
    private val financeInstitutionRepository: FinanceInstitutionRepository
) {
    fun invoke(financeInstitutionId: UUID, username: String, name: String) {
        val entity =
            financeInstitutionRepository.findByIdAndUsername(financeInstitutionId, username) ?:
            throw ResourceNotFoundException("Finance institution not found")

        entity.name = name
        financeInstitutionRepository.save(entity);
    }
}