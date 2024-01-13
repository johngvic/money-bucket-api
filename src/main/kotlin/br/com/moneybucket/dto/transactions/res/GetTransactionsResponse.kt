package br.com.moneybucket.dto.transactions.res

import br.com.moneybucket.entity.Transaction

data class GetTransactionsResponse(
    val data: List<Transaction>
)
