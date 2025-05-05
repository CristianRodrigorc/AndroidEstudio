package com.cristian.appgym

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.cristian.appgym.util.UtilidadesInterfaz
import java.util.Locale

class MyApplication : Application() {

    companion object {
        private const val PREFS_NAME = "AppSettings"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_THEME = "theme"

        fun getSavedLanguage(context: Context): String {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(KEY_LANGUAGE, Locale.getDefault().language) ?: Locale.getDefault().language
        }

        fun getSavedTheme(context: Context): Boolean {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_THEME, false)
        }
    }

    override fun onCreate() {
        super.onCreate()
        applySavedSettings()
    }

    private fun applySavedSettings() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Aplicar idioma guardado
        val savedLanguage = sharedPreferences.getString(KEY_LANGUAGE, "es")
        val currentLocale = resources.configuration.locale
        if (savedLanguage != currentLocale.language) {
            val newLocale = Locale(savedLanguage!!)
            UtilidadesInterfaz.cambiarLenguaje(this, newLocale)
        }

        // Aplicar tema guardado
        val isDarkTheme = sharedPreferences.getBoolean(KEY_THEME, false)
        val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val shouldBeDark = currentMode == Configuration.UI_MODE_NIGHT_YES

        if (isDarkTheme != shouldBeDark) {
            val newMode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(newMode)
        }
    }

    fun setAppLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        UtilidadesInterfaz.cambiarLenguaje(context, locale)
    }

    fun setAppTheme(context: Context, isDarkTheme: Boolean) {
        val newMode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(newMode)
    }
}
