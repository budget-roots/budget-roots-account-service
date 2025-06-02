package de.budgetroots.accountservice.adapter.input.web

import de.budgetroots.accountservice.adapter.input.web.dto.*
import de.budgetroots.accountservice.domain.port.input.AccountDetailsUseCase
import de.budgetroots.accountservice.domain.port.input.AccountLoginUseCase
import de.budgetroots.accountservice.domain.port.input.AccountRegistrationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountController(
    private val accountRegistrationUseCase: AccountRegistrationUseCase,
    private val accountDetailsUseCase: AccountDetailsUseCase,
    private val accountLoginUseCase: AccountLoginUseCase,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody registerAccountRequest: RegisterAccountRequest,
    ): ResponseEntity<String> {
        accountRegistrationUseCase.registerUser(registerAccountRequest.toCommand())
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginAccountRequest: LoginAccountRequest,
    ): ResponseEntity<LoginAccountResponse> = ResponseEntity.ok(accountLoginUseCase.login(loginAccountRequest.toCommand()).toResponse())

    @GetMapping("/{keycloakId}")
    fun getAccountDetails(
        @PathVariable keycloakId: String,
    ): ResponseEntity<AccountDetails> = ResponseEntity.ok(accountDetailsUseCase.getAccountDetails(keycloakId).toAccountDetails())
}
