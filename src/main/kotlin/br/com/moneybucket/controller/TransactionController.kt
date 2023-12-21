package br.com.moneybucket.controller

import br.com.moneybucket.dto.transactions.req.CreateTransactionRequest
import br.com.moneybucket.dto.transactions.res.CreateTransactionResponse
import br.com.moneybucket.helpers.StringParser
import br.com.moneybucket.service.user.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@Slf4j
@RestController
@RequestMapping("transactions")
class TransactionController(
    @Autowired
    private val tokenService: TokenService,
) {
    @PostMapping
    fun createTransaction(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CreateTransactionRequest
    ): ResponseEntity<CreateTransactionResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        return ResponseEntity(CreateTransactionResponse(UUID.randomUUID()), HttpStatus.CREATED)
    }
}