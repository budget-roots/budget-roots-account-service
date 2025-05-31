package de.budgetroots.accountservice

import de.budgetroots.accountservice.configuration.keycloak.KeycloakProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(KeycloakProperties::class)
class BudgedRootsAccountServiceApplication

fun main(args: Array<String>) {
    runApplication<BudgedRootsAccountServiceApplication>(*args)
}
