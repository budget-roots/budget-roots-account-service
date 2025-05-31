package de.budgetroots.accountservice.domain.model.command

import de.budgetroots.accountservice.adapter.output.persistance.entity.AccountEntity

data class RegisterAccountCommand(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
)

fun RegisterAccountCommand.toEntity(keycloakId: String) =
    AccountEntity(
        keycloakId = keycloakId,
        email = email,
        firstName = firstName,
        lastName = lastName,
    )
