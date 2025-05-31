package de.budgetroots.accountservice.adapter.output.keycloak.exception

import de.budgetroots.accountservice.domain.exception.AccountServiceException
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand
import org.springframework.http.HttpStatus

class AccountCreateException(
    command: RegisterAccountCommand,
) : AccountServiceException(
        reason = "Failed to create user with email [${command.email}]",
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    )
