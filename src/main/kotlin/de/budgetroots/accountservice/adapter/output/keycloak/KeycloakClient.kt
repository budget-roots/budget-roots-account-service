package de.budgetroots.accountservice.adapter.output.keycloak

import de.budgetroots.accountservice.adapter.output.keycloak.dto.toKeycloakRequestPayload
import de.budgetroots.accountservice.adapter.output.keycloak.exception.AccountAlreadyExistsException
import de.budgetroots.accountservice.adapter.output.keycloak.exception.AccountCreateException
import de.budgetroots.accountservice.adapter.output.keycloak.exception.InvalidCredentialsException
import de.budgetroots.accountservice.configuration.keycloak.KeycloakProperties
import de.budgetroots.accountservice.domain.exception.AccountServiceException
import de.budgetroots.accountservice.domain.model.KeycloakToken
import de.budgetroots.accountservice.domain.model.command.LoginAccountCommand
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand
import de.budgetroots.accountservice.domain.port.output.AccountKeycloakPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClient

@Component
class KeycloakClient(
    private val client: RestClient,
    private val keycloakProperties: KeycloakProperties,
) : AccountKeycloakPort {
    override fun register(command: RegisterAccountCommand): String {
        val payload = command.toKeycloakRequestPayload()
        try {
            val adminToken = getAdminToken()

            val response =
                client
                    .post()
                    .uri(keycloakProperties.getCreateUserUrl())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer $adminToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity()

            return response.headers.location
                ?.toString()
                ?.substringAfterLast('/') ?: throw AccountCreateException(
                command = command,
            )
        } catch (ex: HttpClientErrorException) {
            when (ex.statusCode) {
                HttpStatus.CONFLICT -> throw AccountAlreadyExistsException(command = command)

                else -> throw AccountServiceException(
                    statusCode = HttpStatus.valueOf(ex.statusCode.value()),
                    throwable = ex,
                )
            }
        }
    }

    override fun login(command: LoginAccountCommand): KeycloakToken =
        client
            .post()
            .uri(keycloakProperties.getLoginTokenUrl())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(
                LinkedMultiValueMap<String, String>().apply {
                    add("grant_type", "password")
                    add("client_id", keycloakProperties.clientId)
                    add("client_secret", keycloakProperties.clientSecret)
                    add("username", command.email)
                    add("password", command.password)
                },
            ).retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw InvalidCredentialsException(command = command)
            }.body(KeycloakToken::class.java)
            .let { it ?: throw AccountServiceException(reason = "Failed to retrieve access token from Keycloak") }

    private fun getAdminToken(): String =
        client
            .post()
            .uri(keycloakProperties.getAdminUrl())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(
                LinkedMultiValueMap<String, String>().apply {
                    add("grant_type", "password")
                    add("client_id", "admin-cli")
                    add("username", keycloakProperties.adminUsername)
                    add("password", keycloakProperties.adminPassword)
                },
            ).retrieve()
            .onStatus({ it.is4xxClientError || it.is5xxServerError }) { _, response ->
                throw AccountServiceException(
                    reason = "Failed to retrieve admin token from Keycloak",
                    statusCode = HttpStatus.valueOf(response.statusCode.value()),
                )
            }.body(object : ParameterizedTypeReference<Map<String, String>>() {})
            .let {
                it?.get("access_token")
                    ?: throw AccountServiceException(reason = "Failed to retrieve access token from Keycloak")
            }
}
