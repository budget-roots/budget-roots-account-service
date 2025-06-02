package de.budgetroots.accountservice.configuration.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "budget-roots.keycloak")
data class KeycloakProperties(
    val url: String,
    val realm: String,
    val adminUsername: String,
    val adminPassword: String,
    val clientId: String,
    val clientSecret: String,
) {
    fun getAdminUrl(): String = "$url/realms/master/protocol/openid-connect/token"

    fun getCreateUserUrl(): String = "$url/$adminUsername/realms/$realm/users"

    fun getLoginTokenUrl(): String = "$url/realms/$realm/protocol/openid-connect/token"
}
