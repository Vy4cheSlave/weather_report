package github.com.Vy4cheSlave.weather_report.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class InputParameters(
    @JsonProperty("city") val cityName: String,
    @JsonProperty("api_key") val apiKey: String,
)