package com.italo.myweather.api

import com.italo.myweather.BuildConfig
import com.italo.myweather.data.CityWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCity(
        @Query("q") query: String,
        @Query("appid") appId: String = BuildConfig.API_KEY
    ): CityWeatherResponse?
}
