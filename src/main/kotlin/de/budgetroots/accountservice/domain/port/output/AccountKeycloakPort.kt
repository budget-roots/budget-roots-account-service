package de.budgetroots.accountservice.domain.port.output

import de.budgetroots.accountservice.domain.model.KeycloakToken
import de.budgetroots.accountservice.domain.model.command.LoginAccountCommand
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand

interface AccountKeycloakPort {
    fun register(command: RegisterAccountCommand): String

    fun login(command: LoginAccountCommand): KeycloakToken
}
