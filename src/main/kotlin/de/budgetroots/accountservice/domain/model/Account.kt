package de.budgetroots.accountservice.domain.model

data class Account(
    val keycloakId: String,
    val email: String,
    val firstName: String,
    val lastName: String,
)
