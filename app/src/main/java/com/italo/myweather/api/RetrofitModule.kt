package com.italo.myweather.api

import com.italo.myweather.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import javax.inject.Named
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun createOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val ohHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        return ohHttpClient.build()
    }

    @Provides
    fun logInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    @Provides
    fun provideExecutor(): Executor {
        return Executor { command ->
            GlobalScope.launch(Dispatchers.IO) {
                command.run()
            }
        }
    }

    @Provides
    @Named(RETROFIT_BASE_NAMED)
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: GsonConverterFactory,
        @Named(API_OPEN_WEATHER_NAMED)
        baseURL: String,
        executor: Executor
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converter)
            .client(okHttpClient)
            .callbackExecutor(executor)
            .build()
    }

    @Provides
    @Named(API_OPEN_WEATHER_NAMED)
    fun provideBaseURL(): String {
        return BASE_URL
    }

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_OPEN_WEATHER_NAMED = "API_OPEN_WEATHER_NAMED"
        const val RETROFIT_BASE_NAMED = "RETROFIT_BASE_NAMED"
    }
}
