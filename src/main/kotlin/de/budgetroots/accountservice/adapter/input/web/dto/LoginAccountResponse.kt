package de.budgetroots.accountservice.adapter.input.web.dto

import de.budgetroots.accountservice.domain.model.KeycloakToken

data class LoginAccountResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val refreshExpiresIn: Long,
    val tokenType: String,
    val scope: String,
)

fun KeycloakToken.toResponse(): LoginAccountResponse =
    LoginAccountResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiresIn = expiresIn,
        refreshExpiresIn = refreshExpiresIn,
        tokenType = tokenType,
        scope = scope,
    )
