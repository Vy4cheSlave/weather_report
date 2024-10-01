package github.com.Vy4cheSlave.weather_report

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


import org.springframework.web.reactive.function.client.WebClient
import github.com.Vy4cheSlave.weather_report.service.UserApiService
import github.com.Vy4cheSlave.weather_report.config.Configuration
import github.com.Vy4cheSlave.weather_report.consoleLog.getWeatherInformation
import kotlinx.coroutines.*

import java.io.File 
import java.nio.file.Paths
import github.com.Vy4cheSlave.weather_report.dto.InputParameters
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

// @SpringBootApplication
// class WeatherReportApplication

val SECOND_DURATION: Long = 1000
val DAY_DURATION: Long = 24 * 60 * 60 * SECOND_DURATION

fun main(/*args: Array<String>*/) {
	// runApplication<WeatherReportApplication>(*args)
	val content: String = File("src/main/resources", "inputParameters.json").readText()
	val mapper = jacksonObjectMapper()
	val inputParameters: InputParameters = mapper.readValue(content, InputParameters::class.java)

	var webClient = Configuration().WebClient(WebClient.builder())
	var userApiService = UserApiService(webClient, 
		apiKey=inputParameters.apiKey, 
		cityName=inputParameters.cityName,
		lat=null,
		lon=null,
		)
	runBlocking {
		launch {
			var isBreaked = false
			for (i in 0..3) {
				try {
					val geolocation = userApiService.getGeolocation()
					userApiService.setGeolocation(geolocation[0])
					isBreaked = !isBreaked
					break
				} 
				catch (e: Exception) {
					println(e)
					delay(3*SECOND_DURATION)
				}
			}
			if (!isBreaked) {
				println("сервер временно не отвечает, попробуйте позже")
				System.exit(1)
			}
		}
	}

	while (true) {
		runBlocking {
			launch {
				while (true) {
					try {
						val weatherData = userApiService.getWeatherData()
						println(getWeatherInformation(weatherData))
						break
					} catch (e: Exception) { println(e) }
				}
				delay(DAY_DURATION)
			}
		}
	}
}