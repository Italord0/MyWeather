package com.italo.myweather.ui.settings

import androidx.lifecycle.ViewModel
import com.italo.myweather.preferences.Preferences

class SettingsViewModel : ViewModel() {

    var savedTemp: Int? = null
    var savedLanguage: Int? = null

    fun getSavedSettings() {
    }

    fun saveSettings(temp: Int, language: Int) {
        Preferences.set("TEMPERATURE", temp)
        Preferences.set("LANGUAGE", language)
    }
}
