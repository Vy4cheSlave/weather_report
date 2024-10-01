package github.com.Vy4cheSlave.weather_report.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherDataResponse(
    @JsonProperty("dt") val date: Long,
    @JsonProperty("weather") val weather: List<Weather>,
    @JsonProperty("main") val main: Main,
    @JsonProperty("wind") val windSpeed: Wind,
    @JsonProperty("clouds") val clouds: Clouds,
    @JsonProperty("rain") val rain: Rain?,
    @JsonProperty("snow") val snow: Snow?,
)

data class Main(
    @JsonProperty("temp") val temperature: Double,
    @JsonProperty("pressure") val atmospherePressure: Int,
)

data class Weather(
    @JsonProperty("description") val description: String
)

data class Wind(
    @JsonProperty("speed") val speedInMettersPerSecond: Double
)

data class Clouds(
    @JsonProperty("all") val percent: Int
)

data class Rain(
    @JsonProperty("h1") val milimetersPerHour: Double
)

data class Snow(
    @JsonProperty("h1") val milimetersPerHour: Double
)