package com.italo.myweather.di

import com.italo.myweather.api.RetrofitModule
import com.italo.myweather.api.WeatherApiService
import retrofit2.Retrofit

object WeatherServiceModule {

    fun providesWeatherService(retrofit: Retrofit = RetrofitModule.provideBaseRetrofit()): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}
