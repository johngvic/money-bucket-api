package br.com.moneybucket.exception

import org.springframework.http.HttpStatus

data class ProblemDTO(
    val title: String,
    val status: Int,
    val detail: String,
) {
    companion object {
        const val MEDIA_TYPE = "application/x.problem+json"

        fun fromHttpStatus(httpStatus: HttpStatus, message: String) = ProblemDTO(
            title = httpStatus.name,
            status = httpStatus.value(),
            detail = message
        )
    }
}