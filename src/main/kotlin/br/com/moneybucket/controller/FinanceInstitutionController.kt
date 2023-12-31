package br.com.moneybucket.controller

import br.com.moneybucket.dto.finance_institutions.req.CreateFinanceInstitutionRequest
import br.com.moneybucket.dto.finance_institutions.req.EditFinanceInstitutionRequest
import br.com.moneybucket.dto.finance_institutions.res.CreateFinanceInstitutionResponse
import br.com.moneybucket.dto.finance_institutions.res.GetFinanceInstitutionsResponse
import br.com.moneybucket.entity.FinanceInstitution
import br.com.moneybucket.exception.ResourceNotFoundException
import br.com.moneybucket.helpers.StringParser
import br.com.moneybucket.service.finance_institution.*
import br.com.moneybucket.service.user.TokenService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("finance-institutions")
class FinanceInstitutionController(
    @Autowired
    private val tokenService: TokenService,
    private val createFinanceInstitutionService: CreateFinanceInstitutionService,
    private val getFinanceInstitutionsService: GetFinanceInstitutionsService,
    private val getFinanceInstitutionsByIdService: GetFinanceInstitutionByIdService,
    private val editFinanceInstitutionService: EditFinanceInstitutionService,
    private val deleteFinanceInstitutionService: DeleteFinanceInstitutionService
) {
    @PostMapping
    fun createFinanceInstitution(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CreateFinanceInstitutionRequest
    ): ResponseEntity<CreateFinanceInstitutionResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = createFinanceInstitutionService.invoke(username, request.name)
        return ResponseEntity(CreateFinanceInstitutionResponse(response), HttpStatus.CREATED)
    }

    @GetMapping
    fun getFinanceInstitutions(
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<GetFinanceInstitutionsResponse> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = getFinanceInstitutionsService.invoke(username)
        return ResponseEntity(GetFinanceInstitutionsResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{financeInstitutionId}")
    fun getFinanceInstitutionById(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable financeInstitutionId: UUID
    ): ResponseEntity<FinanceInstitution> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        val response = getFinanceInstitutionsByIdService.invoke(financeInstitutionId, username)

        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        }

        throw ResourceNotFoundException("Finance institution not found");
    }

    @PutMapping("/{financeInstitutionId}")
    fun editFinanceInstitution(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: EditFinanceInstitutionRequest,
        @PathVariable financeInstitutionId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        editFinanceInstitutionService.invoke(financeInstitutionId, username, request.name)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{financeInstitutionId}")
    fun deleteFinanceInstitution(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable financeInstitutionId: UUID
    ): ResponseEntity<Unit> {
        val username = tokenService.validateToken(StringParser.split(authorization))
        deleteFinanceInstitutionService.invoke(financeInstitutionId, username)
        return ResponseEntity(HttpStatus.OK)
    }
}