package com.italo.myweather.api

import com.italo.myweather.data.CityWeatherResponse
import com.italo.myweather.di.WeatherServiceModule

class CityWeatherRepository(
    private val weatherService: WeatherApiService = WeatherServiceModule.providesWeatherService(),
) {
    suspend fun getCities(query: String): CityWeatherResponse? =
        weatherService.getCities(
            query
        )

    suspend fun getCitiesById(ids: List<Long>): CityWeatherResponse? =
        weatherService.getCitiesById(
            ids.joinToString(separator = ",")
        )
}
