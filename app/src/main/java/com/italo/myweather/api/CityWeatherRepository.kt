package com.italo.myweather.api

import com.italo.myweather.data.CityWeatherResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CityWeatherRepository() : KoinComponent {

    private val weatherService: WeatherApiService by inject<WeatherApiService>()

    suspend fun getCities(query: String): CityWeatherResponse? =
        weatherService.getCities(
            query
        )

    suspend fun getCitiesById(ids: List<Long>): CityWeatherResponse? =
        weatherService.getCitiesById(
            ids.joinToString(separator = ",")
        )
}
