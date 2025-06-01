package de.budgetroots.accountservice.adapter.output.persistance

import de.budgetroots.accountservice.adapter.output.persistance.entity.AccountEntity
import de.budgetroots.accountservice.domain.port.output.AccountPersistencePort
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Component
import java.util.*

interface UserRepositorySpring : ListCrudRepository<AccountEntity, UUID> {
    fun findByKeycloakId(keycloakId: String): AccountEntity?
}

@Component
class AccountPersistenceRepository(
    private val repo: UserRepositorySpring,
) : AccountPersistencePort {
    override fun saveUser(entity: AccountEntity): AccountEntity = repo.save(entity)

    override fun findByKeycloakId(keycloakId: String): AccountEntity? = repo.findByKeycloakId(keycloakId)
}
