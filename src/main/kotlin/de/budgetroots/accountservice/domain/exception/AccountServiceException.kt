package de.budgetroots.accountservice.domain.exception

import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

open class AccountServiceException(
    val reason: String = "An unexpected error occurred",
    val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val logLevel: Level = Level.ERROR,
    val throwable: Throwable? = null,
) : RuntimeException(reason, throwable) {
    fun toProblemDetail(): ProblemDetail = ProblemDetail.forStatusAndDetail(statusCode, reason)
}
