package de.budgetroots.accountservice.domain.model.command

data class LoginAccountCommand(
    val email: String,
    val password: String,
)
