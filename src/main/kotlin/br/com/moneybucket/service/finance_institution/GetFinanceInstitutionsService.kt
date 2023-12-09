package br.com.moneybucket.service.finance_institution

import br.com.moneybucket.entity.FinanceInstitution
import br.com.moneybucket.repository.FinanceInstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetFinanceInstitutionsService(
    @Autowired
    private val financeInstitutionRepository: FinanceInstitutionRepository
) {
    fun invoke(username: String): List<FinanceInstitution> {
        return financeInstitutionRepository.findByUsername(username)
    }
}