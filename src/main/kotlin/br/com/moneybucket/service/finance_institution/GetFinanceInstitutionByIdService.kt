package br.com.moneybucket.service.finance_institution

import br.com.moneybucket.entity.FinanceInstitution
import br.com.moneybucket.repository.FinanceInstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetFinanceInstitutionByIdService(
    @Autowired private val financeInstitutionRepository: FinanceInstitutionRepository
) {
    fun invoke(financeInstitutionId: UUID, username: String): FinanceInstitution? {
        return financeInstitutionRepository.findByIdAndUsername(financeInstitutionId, username);
    }
}