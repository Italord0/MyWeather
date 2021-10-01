package com.italo.myweather.api

import com.italo.myweather.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

object RetrofitModule {

    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    fun createOkHttpClient(loggingInterceptor: HttpLoggingInterceptor = logInterceptor()): OkHttpClient {
        val ohHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        return ohHttpClient.build()
    }

    fun logInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    fun provideExecutor(): Executor {
        return Executor { command ->
            GlobalScope.launch(Dispatchers.IO) {
                command.run()
            }
        }
    }

    fun provideBaseRetrofit(
        okHttpClient: OkHttpClient = createOkHttpClient(),
        converter: GsonConverterFactory = provideGson(),
        baseURL: String = provideBaseURL(),
        executor: Executor = provideExecutor()
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converter)
            .client(okHttpClient)
            .callbackExecutor(executor)
            .build()
    }

    fun provideBaseURL(): String {
        return BASE_URL
    }

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
}
