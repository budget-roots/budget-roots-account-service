package de.budgetroots.accountservice.adapter.output.persistance.entity

import de.budgetroots.accountservice.domain.model.Account
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Version
import java.util.UUID

@Entity(name = "account")
data class AccountEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Version var version: Long? = null,
    var keycloakId: String,
    var email: String,
    var firstName: String,
    var lastName: String,
)

fun AccountEntity.toAccount(): Account =
    Account(
        id = keycloakId,
        email = email,
        firstName = firstName,
        lastName = lastName,
    )
