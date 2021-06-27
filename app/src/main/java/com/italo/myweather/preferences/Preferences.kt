package com.italo.myweather.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.italo.myweather.R
import java.lang.reflect.Type

object Preferences {

    private const val SHARED_PREFERENCE_NAME = "MYWEATHER_PREFERENCES"
    private lateinit var sharedPreference: SharedPreferences
    private val gson = Gson()

    fun init(context: Context) {
        sharedPreference =
            context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> get(key: String, type: Type): T {
        val stringValue = sharedPreference.getString(key, null) ?: throw Exception()
        return gson.fromJson(stringValue, type) ?: throw Exception()
    }

    fun set(key: String, value: Any?) {
        value?.let {
            sharedPreference.edit().putString(key, gson.toJson(it)).apply()
        } ?: sharedPreference.edit().remove(key).apply()
    }

    fun exists(key: String): Boolean {
        val stringValue = sharedPreference.getString(key, null)
        return !stringValue.isNullOrBlank()
    }

    fun getLanguage(): String {
        return when (get<Int>("LANGUAGE", Int::class.java)) {
            R.id.radioButtonEnglish -> "en"
            R.id.radioButtonPortuguese -> "pt"
            else -> "ERROR"
        }
    }

    fun getLanguageId(): Int {
        return get<Int>("LANGUAGE", Int::class.java)
    }

    fun getCountry(): String {
        return when (get<Int>("LANGUAGE", Int::class.java)) {
            R.id.radioButtonEnglish -> "US"
            R.id.radioButtonPortuguese -> "BR"
            else -> "ERROR"
        }
    }

    fun getTemperature(): String {
        return when (get<Int>("TEMPERATURE", Int::class.java)) {
            R.id.radioButtonC -> "C"
            R.id.radioButtonF -> "F"
            R.id.radioButtonK -> "K"
            else -> "ERROR"
        }
    }

    fun getTemperatureId(): Int {
        return get<Int>("TEMPERATURE", Int::class.java)
    }

    fun clear() {
        sharedPreference.edit().clear().apply()
    }
}
