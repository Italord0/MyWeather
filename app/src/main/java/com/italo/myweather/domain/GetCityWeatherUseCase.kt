package com.italo.myweather.domain

import com.italo.myweather.api.CityWeatherRepository
import com.italo.myweather.data.CityWeatherResponse
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) {
    suspend fun getCity(query: String): CityWeatherResponse? =
        cityWeatherRepository.getCity(query)
}
