package com.italo.myweather.domain

import com.italo.myweather.api.CityWeatherRepository
import com.italo.myweather.data.CityWeatherResponse
import javax.inject.Inject

class CityUseCase @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) {
    suspend fun getCities(query: String): CityWeatherResponse? =
        cityWeatherRepository.getCities(query)

    suspend fun getCitiesById(ids: List<Long>): CityWeatherResponse? =
        cityWeatherRepository.getCitiesById(ids)
}
