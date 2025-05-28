package de.budgetroots.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BudgedRootsUserServiceApplication

fun main(args: Array<String>) {
    runApplication<BudgedRootsUserServiceApplication>(*args)
}
