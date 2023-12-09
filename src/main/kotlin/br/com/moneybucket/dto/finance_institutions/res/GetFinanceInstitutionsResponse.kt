package br.com.moneybucket.dto.finance_institutions.res

import br.com.moneybucket.entity.FinanceInstitution

data class GetFinanceInstitutionsResponse(
    val data: List<FinanceInstitution>
)
