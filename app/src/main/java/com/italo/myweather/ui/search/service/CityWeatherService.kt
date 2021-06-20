package com.italo.myweather.ui.search.service

import com.italo.myweather.ui.search.data.WeatherResponse
import retrofit2.http.GET

interface CityWeatherService {

    @GET("")
    fun searchCity(): List<WeatherResponse>
}
