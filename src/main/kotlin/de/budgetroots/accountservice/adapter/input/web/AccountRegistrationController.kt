package de.budgetroots.accountservice.adapter.input.web

import de.budgetroots.accountservice.adapter.input.web.dto.RegisterAccountRequest
import de.budgetroots.accountservice.adapter.input.web.dto.toCommand
import de.budgetroots.accountservice.domain.port.input.AccountRegistrationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/register")
class AccountRegistrationController(
    private val accountRegistrationUseCase: AccountRegistrationUseCase,
) {
    @PostMapping
    fun register(
        @RequestBody registerAccountRequest: RegisterAccountRequest,
    ): ResponseEntity<String> {
        accountRegistrationUseCase.registerUser(registerAccountRequest.toCommand())
        return ResponseEntity.ok("User registered successfully")
    }
}
