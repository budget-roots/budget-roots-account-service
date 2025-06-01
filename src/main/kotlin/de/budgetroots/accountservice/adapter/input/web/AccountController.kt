package de.budgetroots.accountservice.adapter.input.web

import de.budgetroots.accountservice.adapter.input.web.dto.AccountDetails
import de.budgetroots.accountservice.adapter.input.web.dto.RegisterAccountRequest
import de.budgetroots.accountservice.adapter.input.web.dto.toAccountDetails
import de.budgetroots.accountservice.adapter.input.web.dto.toCommand
import de.budgetroots.accountservice.domain.port.input.AccountDetailsUseCase
import de.budgetroots.accountservice.domain.port.input.AccountRegistrationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountController(
    private val accountRegistrationUseCase: AccountRegistrationUseCase,
    private val accountDetailsUseCase: AccountDetailsUseCase,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody registerAccountRequest: RegisterAccountRequest,
    ): ResponseEntity<String> {
        accountRegistrationUseCase.registerUser(registerAccountRequest.toCommand())
        return ResponseEntity.ok("User registered successfully")
    }

    @GetMapping("/{keycloakId}")
    fun getAccountDetails(
        @PathVariable keycloakId: String,
    ): ResponseEntity<AccountDetails> = ResponseEntity.ok(accountDetailsUseCase.getAccountDetails(keycloakId).toAccountDetails())
}
