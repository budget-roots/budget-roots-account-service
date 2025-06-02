package de.budgetroots.accountservice.adapter.output.keycloak.exception

import de.budgetroots.accountservice.domain.exception.AccountServiceException
import de.budgetroots.accountservice.domain.model.command.LoginAccountCommand
import org.slf4j.event.Level
import org.springframework.http.HttpStatus

class InvalidCredentialsException(
    command: LoginAccountCommand,
) : AccountServiceException(
        reason = "Invalid credentials for user [${command.email}]",
        statusCode = HttpStatus.BAD_REQUEST,
        logLevel = Level.WARN,
    )
