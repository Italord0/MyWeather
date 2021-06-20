package com.italo.myweather.ui.home.service

import com.italo.myweather.ui.home.data.WeatherResponse
import retrofit2.http.GET

interface CityWeatherService {

    @GET("")
    fun searchCity(): List<WeatherResponse>

}