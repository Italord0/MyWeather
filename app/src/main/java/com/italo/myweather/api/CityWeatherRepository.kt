package com.italo.myweather.api

import com.italo.myweather.data.CityWeatherResponse
import javax.inject.Inject

class CityWeatherRepository @Inject constructor(
    private val weatherService: WeatherApiService
) {
    suspend fun getCity(query: String): CityWeatherResponse? =
        weatherService.getCity(
            query
        )
}
