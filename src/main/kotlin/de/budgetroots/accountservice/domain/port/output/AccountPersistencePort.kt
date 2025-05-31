package de.budgetroots.accountservice.domain.port.output

import de.budgetroots.accountservice.adapter.output.persistance.entity.AccountEntity

interface AccountPersistencePort {
    fun saveUser(entity: AccountEntity): AccountEntity
}
