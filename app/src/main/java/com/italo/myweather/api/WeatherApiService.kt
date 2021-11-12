package com.italo.myweather.api

import com.italo.myweather.BuildConfig
import com.italo.myweather.data.CityWeatherResponse
import com.italo.myweather.preferences.Preferences
import org.koin.core.component.KoinComponent
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("find")
    suspend fun getCities(
        @Query("q") query: String,
        @Query("appid") appId: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = Preferences.getLangAPI(),
        @Query("units") units: String = Preferences.getUnitsAPI()
    ): CityWeatherResponse?

    @GET("group")
    suspend fun getCitiesById(
        @Query("id") ids: String,
        @Query("appid") appId: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = Preferences.getLangAPI(),
        @Query("units") units: String = Preferences.getUnitsAPI()
    ): CityWeatherResponse?
}
