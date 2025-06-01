package de.budgetroots.accountservice.adapter.input.web.dto

import de.budgetroots.accountservice.domain.model.Account

data class AccountDetails(
    val keycloakId: String,
    val email: String,
    val firstName: String,
    val lastName: String,
)

fun Account.toAccountDetails() =
    AccountDetails(
        keycloakId = keycloakId,
        email = email,
        firstName = firstName,
        lastName = lastName,
    )
