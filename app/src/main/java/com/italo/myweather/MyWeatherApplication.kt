package com.italo.myweather

import android.app.Application
import com.italo.myweather.di.FavoriteCityDBModule
import com.italo.myweather.di.KoinModules
import com.italo.myweather.preferences.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initPreferences()
        initDatabase()
        startKoin{
            //androidLogger()
            androidContext(this@MyWeatherApplication)
            modules(KoinModules.retrofitModule,KoinModules.serviceModule)
        }
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
