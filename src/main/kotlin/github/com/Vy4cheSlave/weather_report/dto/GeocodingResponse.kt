package github.com.Vy4cheSlave.weather_report.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GeocodingResponse(
    @JsonProperty("lat") val lat: Double,
    @JsonProperty("lon") val lon: Double
)

