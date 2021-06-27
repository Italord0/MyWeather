package com.italo.myweather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.italo.myweather.context.ContextWrapper
import com.italo.myweather.preferences.Preferences
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale(Preferences.getLanguage(), Preferences.getCountry())

        super.attachBaseContext(ContextWrapper.wrap(newBase, locale))
    }
}
