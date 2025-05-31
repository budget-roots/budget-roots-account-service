package de.budgetroots.accountservice.adapter.input.web.dto

import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand

data class RegisterAccountRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
)

fun RegisterAccountRequest.toCommand(): RegisterAccountCommand =
    RegisterAccountCommand(
        email = this.email,
        firstName = firstName,
        lastName = lastName,
        password = password,
    )
