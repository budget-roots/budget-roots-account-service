package de.budgetroots.accountservice.configuration.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class WebConfig {
    @Bean
    fun restClient(): RestClient =
        RestClient
            .builder()
            .build()
}
