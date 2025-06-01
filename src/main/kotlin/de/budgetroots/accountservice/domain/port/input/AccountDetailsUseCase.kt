package de.budgetroots.accountservice.domain.port.input

import de.budgetroots.accountservice.domain.model.Account

interface AccountDetailsUseCase {
    fun getAccountDetails(keycloakId: String): Account
}
