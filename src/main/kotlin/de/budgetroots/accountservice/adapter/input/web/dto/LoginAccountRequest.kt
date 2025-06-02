package de.budgetroots.accountservice.adapter.input.web.dto

import de.budgetroots.accountservice.domain.model.command.LoginAccountCommand

data class LoginAccountRequest(
    val email: String,
    val password: String,
)

fun LoginAccountRequest.toCommand() =
    LoginAccountCommand(
        email = this.email,
        password = this.password,
    )
