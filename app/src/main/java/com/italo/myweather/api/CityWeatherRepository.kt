package com.italo.myweather.api

import com.italo.myweather.data.CityWeatherResponse
import javax.inject.Inject

class CityWeatherRepository @Inject constructor(
    private val weatherService: WeatherApiService
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
