package br.com.moneybucket.service.finance_institution

import br.com.moneybucket.entity.FinanceInstitution
import br.com.moneybucket.repository.FinanceInstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateFinanceInstitutionService(
    @Autowired
    private val financeInstitutionRepository: FinanceInstitutionRepository
) {
    fun invoke(user: String, name: String): UUID {
        val generatedId = UUID.randomUUID()
        financeInstitutionRepository.save(FinanceInstitution(generatedId, user, name))
        return generatedId;
    }
}