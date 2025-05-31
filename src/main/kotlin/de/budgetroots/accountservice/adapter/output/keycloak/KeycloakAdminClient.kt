package de.budgetroots.accountservice.adapter.output.keycloak

import de.budgetroots.accountservice.adapter.output.keycloak.dto.toKeycloakRequestPayload
import de.budgetroots.accountservice.adapter.output.keycloak.exception.AccountAlreadyExistsException
import de.budgetroots.accountservice.adapter.output.keycloak.exception.AccountCreateException
import de.budgetroots.accountservice.configuration.keycloak.KeycloakProperties
import de.budgetroots.accountservice.domain.exception.AccountServiceException
import de.budgetroots.accountservice.domain.model.command.RegisterAccountCommand
import de.budgetroots.accountservice.domain.port.output.AccountKeycloakPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClient

@Component
class KeycloakAdminClient(
    private val client: RestClient,
    private val keycloakProperties: KeycloakProperties,
) : AccountKeycloakPort {
    override fun registerUserInKeycloak(command: RegisterAccountCommand): String {
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

            return response
                .headers
                .location
                ?.toString()
                ?.substringAfterLast('/')
                ?: throw AccountCreateException(command = command)
        } catch (ex: HttpClientErrorException) {
            when (ex.statusCode) {
                HttpStatus.CONFLICT ->
                    throw AccountAlreadyExistsException(command = command)

                else ->
                    throw AccountServiceException(
                        statusCode = HttpStatus.valueOf(ex.statusCode.value()),
                        throwable = ex,
                    )
            }
        }
    }

    private fun getAdminToken(): String {
        try {
            val response =
                client
                    .post()
                    .uri(keycloakProperties.getAdminUrl())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(
                        "grant_type=password&client_id=admin-cli&username=${keycloakProperties.adminUsername}&password=${keycloakProperties.adminPassword}",
                    ).retrieve()
                    .body(Map::class.java)

            return response?.get("access_token") as? String
                ?: throw AccountServiceException(reason = "Failed to retrieve access token from Keycloak")
        } catch (ex: Exception) {
            throw AccountServiceException(
                throwable = ex,
            )
        }
    }
}
