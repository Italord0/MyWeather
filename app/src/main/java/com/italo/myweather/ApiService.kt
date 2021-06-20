package com.italo.myweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private var instance: ApiService? = null

    fun getInstance(): ApiService? {
        if (instance == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("api.openweathermap.org/data/2.5/weather")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            instance = retrofit.create(ApiService::class.java)
        }
        return instance
    }

}