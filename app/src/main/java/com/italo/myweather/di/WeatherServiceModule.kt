package com.italo.myweather.di

import com.italo.myweather.api.RetrofitModule
import com.italo.myweather.api.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherServiceModule {

    @Provides
    @Singleton
    fun providesWeatherService(@Named(RetrofitModule.RETROFIT_BASE_NAMED) retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}
