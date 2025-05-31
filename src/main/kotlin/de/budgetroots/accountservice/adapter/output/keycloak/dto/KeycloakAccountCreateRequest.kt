package de.budgetroots.accountservice.adapter.output.keycloak.dto

import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand

data class KeycloakAccountCreateRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val enabled: Boolean = true,
    val credentials: List<Credentials>,
)

data class Credentials(
    val type: String = "password",
    val value: String,
    val temporary: Boolean = false,
)

fun RegisterAccountCommand.toKeycloakRequestPayload() =
    KeycloakAccountCreateRequest(
        email = email,
        firstName = firstName,
        lastName = lastName,
        enabled = true,
        credentials = listOf(Credentials(value = password)),
    )
