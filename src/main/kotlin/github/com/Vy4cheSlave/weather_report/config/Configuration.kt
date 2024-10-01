package github.com.Vy4cheSlave.weather_report.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.beans.factory.annotation.*

@Configuration
class Configuration {
    @Bean
    fun WebClient(builder: WebClient.Builder): WebClient = 
        builder
            .baseUrl("https://api.openweathermap.org")
            .build()
}
