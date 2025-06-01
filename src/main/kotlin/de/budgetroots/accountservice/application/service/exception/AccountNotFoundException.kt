package de.budgetroots.accountservice.application.service.exception

import de.budgetroots.accountservice.domain.exception.AccountServiceException
import org.slf4j.event.Level
import org.springframework.http.HttpStatus

class AccountNotFoundException(
    keycloakId: String,
) : AccountServiceException(
        statusCode = HttpStatus.NOT_FOUND,
        reason = "Account with id [$keycloakId] not found",
        logLevel = Level.WARN,
    )
