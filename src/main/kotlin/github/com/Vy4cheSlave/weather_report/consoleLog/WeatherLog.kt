package github.com.Vy4cheSlave.weather_report.consoleLog

import github.com.Vy4cheSlave.weather_report.dto.WeatherDataResponse
import java.text.SimpleDateFormat
import java.util.Date

fun getWeatherInformation(weatherData: WeatherDataResponse): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = Date(weatherData.date * 1000)

    return "[${sdf.format(date)}]\n" +
        "Описание погоды: ${weatherData.weather[0].description}\n" +
        "Температура: ${weatherData.main.temperature} градусa(ов) Цельсия\n" +
        "Атмосферное давление: ${weatherData.main.atmospherePressure} гПа\n" +
        "Скорость ветра: ${weatherData.windSpeed.speedInMettersPerSecond} метрa(ов) в секунду\n" +
        "Облачность: ${weatherData.clouds.percent}%\n" +
        if (weatherData.rain != null) "Дождь: ${weatherData.rain.milimetersPerHour} милиметрa(ов) в час\n"
        else "" +
        if (weatherData.snow != null) "Снег: ${weatherData.snow.milimetersPerHour} милиметрa(ов) в час\n"
        else ""
}