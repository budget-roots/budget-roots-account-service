package de.budgetroots.accountservice.adapter.input.web.exception

import de.budgetroots.accountservice.domain.exception.AccountServiceException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Throwable::class)
    private fun defaultExceptionHandler(ex: Throwable): ResponseEntity<ProblemDetail> {
        log.error("An unexpected error occurred", ex)
        return ResponseEntity.internalServerError().body(
            ProblemDetail
                .forStatusAndDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.message ?: "An unexpected error occurred",
                ),
        )
    }

    @ExceptionHandler(AccountServiceException::class)
    private fun userServiceExceptionHandler(ex: AccountServiceException): ResponseEntity<ProblemDetail> {
        log
            .atLevel(ex.logLevel)
            .setMessage { ex.message }
            .setCause(ex.throwable)
            .log()
        return ResponseEntity
            .status(ex.statusCode)
            .body(ex.toProblemDetail())
    }
}
