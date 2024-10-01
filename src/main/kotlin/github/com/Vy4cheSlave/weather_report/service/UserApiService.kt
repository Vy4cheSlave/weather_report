package github.com.Vy4cheSlave.weather_report.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.*//awaitBody
import org.springframework.stereotype.*
import org.springframework.http.MediaType
import github.com.Vy4cheSlave.weather_report.dto.GeocodingResponse
import github.com.Vy4cheSlave.weather_report.dto.WeatherDataResponse
import org.springframework.http.HttpStatus
// import org.springframework.web.reactive.function.client.WebClient.ResponseSpec.tryAwaitBodyOrElseLogged

@Service
class UserApiService (
    private val webClient: WebClient,
    private val apiKey: String?,
    private val cityName: String?,
    var lat: Double?,
    var lon: Double?
) {
    suspend fun getGeolocation(): List<GeocodingResponse> {
        return webClient.get()
            .uri("/geo/1.0/direct?q=${cityName}&appid=${apiKey}")
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange { response ->
                if (response.statusCode() == HttpStatus.OK) {
                    response.awaitBody<List<GeocodingResponse>>()
                }
                else {
                    throw Exception("Ошибка ответа на запрос getGeolocation")
                }
            }
    }

    fun setGeolocation(geolocation: GeocodingResponse): Unit {
        this.lat = geolocation.lat
        this.lon = geolocation.lon
    }

    suspend fun getWeatherData(): WeatherDataResponse {
        return webClient.get()
            .uri("/data/2.5/weather?lat=${lat}&lon=${lon}&lang=ru&appid=${apiKey}&units=metric")
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange { response ->
                if (response.statusCode() == HttpStatus.OK) {
                    response.awaitBody<WeatherDataResponse>()
                }
                else {
                    throw Exception("Ошибка ответа на запрос getWeatherData")
                }
            }
    }
}