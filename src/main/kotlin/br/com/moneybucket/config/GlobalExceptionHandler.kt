package br.com.moneybucket.config

import br.com.moneybucket.exception.ProblemDTO
import br.com.moneybucket.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(exception: ResourceNotFoundException): ResponseEntity<ProblemDTO> {
        return ResponseEntity(
            ProblemDTO(HttpStatus.NOT_FOUND.name, 404, exception.message),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(Exception::class)
    fun handlerGenericError(exception: Exception): ResponseEntity<ProblemDTO> {
        println(exception.message)

        return ResponseEntity(
            ProblemDTO(HttpStatus.INTERNAL_SERVER_ERROR.name, 500, "Error on server"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}