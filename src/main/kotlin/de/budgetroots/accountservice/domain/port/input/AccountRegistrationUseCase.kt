package de.budgetroots.accountservice.domain.port.input

import de.budgetroots.accountservice.domain.model.Account
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand

interface AccountRegistrationUseCase {
    fun registerUser(command: RegisterAccountCommand): Account
}
