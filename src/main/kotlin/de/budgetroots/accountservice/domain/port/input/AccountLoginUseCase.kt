package de.budgetroots.accountservice.domain.port.input

import de.budgetroots.accountservice.domain.model.KeycloakToken
import de.budgetroots.accountservice.domain.model.command.LoginAccountCommand

interface AccountLoginUseCase {
    fun login(command: LoginAccountCommand): KeycloakToken
}
