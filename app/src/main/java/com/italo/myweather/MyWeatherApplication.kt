package com.italo.myweather

import android.app.Application
import com.italo.myweather.di.FavoriteCityDBModule
import com.italo.myweather.preferences.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initPreferences()
        initDatabase()
    }

    private fun initDatabase() {
        FavoriteCityDBModule.init(this)
    }

    private fun initPreferences() {
        with(Preferences) {
            init(this@MyWeatherApplication)

            if (!this.exists("TEMPERATURE")) {
                this.set("TEMPERATURE", R.id.radioButtonC)
            }
            if (!this.exists("LANGUAGE")) {
                this.set("LANGUAGE", R.id.radioButtonEnglish)
            }
        }
    }
}
