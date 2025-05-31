package de.budgetroots.accountservice.adapter.output.keycloak.exception

import de.budgetroots.accountservice.domain.exception.AccountServiceException
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand
import org.slf4j.event.Level
import org.springframework.http.HttpStatus

class AccountAlreadyExistsException(
    command: RegisterAccountCommand,
) : AccountServiceException(
        reason = "User with email [${command.email}] already exists",
        statusCode = HttpStatus.CONFLICT,
        logLevel = Level.WARN,
    )
