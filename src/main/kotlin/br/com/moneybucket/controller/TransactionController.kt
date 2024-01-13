package br.com.moneybucket.controller

import br.com.moneybucket.dto.finance_institutions.req.EditFinanceInstitutionRequest
import br.com.moneybucket.dto.transactions.req.CreateTransactionRequest
import br.com.moneybucket.dto.transactions.req.EditTransactionRequest
import br.com.moneybucket.dto.transactions.res.CreateTransactionResponse
import br.com.moneybucket.dto.transactions.res.GetTransactionsResponse
import br.com.moneybucket.entity.FinanceInstitution
import br.com.moneybucket.entity.Transaction
import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.helpers.StringParser
import br.com.moneybucket.service.transaction.*
import br.com.moneybucket.service.user.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@Slf4j
@RestController
@RequestMapping("transactions")
class TransactionController(
    @Autowired
    private val tokenService: TokenService,
    private val createTransactionService: CreateTransactionService,
    private val getTransactionsService: GetTransactionsService,
    private val getTransactionByIdService: GetTransactionByIdService,
    private val editTransactionService: EditTransactionService,
    private val deleteTransactionService: DeleteTransactionService
) {
    @PostMapping
    fun createTransaction(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CreateTransactionRequest
    ): ResponseEntity<CreateTransactionResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val id = createTransactionService.invoke(username, request.title, request.type, request.category, request.financeInstitution, request.date, request.value)
        return ResponseEntity(CreateTransactionResponse(id), HttpStatus.CREATED)
    }

    @GetMapping
    fun getTransactions(
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<GetTransactionsResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val id = getTransactionsService.invoke(username)
        return ResponseEntity(GetTransactionsResponse(id), HttpStatus.OK)
    }

    @GetMapping("/{transactionId}")
    fun getTransactionById(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable transactionId: UUID
    ): ResponseEntity<Transaction> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = getTransactionByIdService.invoke(transactionId, username)

        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        }

        throw ResourceNotFoundException("Transaction not found");
    }

    @PutMapping("/{transactionId}")
    fun editTransaction(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: EditTransactionRequest,
        @PathVariable transactionId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        editTransactionService.invoke(
            transactionId,
            username,
            request.title,
            request.type,
            request.category,
            request.financeInstitution,
            request.date,
            request.value
        )
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{transactionId}")
    fun deleteTransaction(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable transactionId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        deleteTransactionService.invoke(transactionId, username)
        return ResponseEntity(HttpStatus.OK)
    }
}