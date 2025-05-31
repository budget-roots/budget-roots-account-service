package de.budgetroots.accountservice.domain.port.output

import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand

interface AccountKeycloakPort {
    fun registerUserInKeycloak(command: RegisterAccountCommand): String
}
