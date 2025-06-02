package de.budgetroots.accountservice.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class KeycloakToken(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    @JsonProperty("expires_in")
    val expiresIn: Long,
    @JsonProperty("refresh_expires_in")
    val refreshExpiresIn: Long,
    @JsonProperty("token_type")
    val tokenType: String,
    val scope: String,
)
