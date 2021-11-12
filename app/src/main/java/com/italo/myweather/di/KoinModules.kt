package com.italo.myweather.di

import com.italo.myweather.BuildConfig
import com.italo.myweather.api.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

object KoinModules {

    val retrofitModule = module {
        factory {
            GsonConverterFactory.create()
        } bind Converter.Factory::class
        factory {
            val logInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            logInterceptor
        }
        factory {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>()).build()
        }
        factory {
            BASE_URL
        }
        factory {
            Executor { command ->
                GlobalScope.launch(Dispatchers.IO) {
                    command.run()
                }
            }
        }
        factory {
            Retrofit
                .Builder()
                .baseUrl(get<String>())
                .addConverterFactory(get())
                .client(get())
                .callbackExecutor(get())
                .build()
        }
    }
    val serviceModule = module {
        single {
            get<Retrofit>().create(WeatherApiService::class.java)
        } bind WeatherApiService::class
    }

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
}
