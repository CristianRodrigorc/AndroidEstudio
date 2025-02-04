package com.cristianproyecto.appgym

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class MyApplication : Application() {

    companion object {
        private const val PREFS_NAME = "AppSettings"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_THEME = "isDarkTheme"

        // Cambiar el idioma globalmente
        fun setAppLocale(context: Context, language: String) {
            // Guardamos el idioma seleccionado en SharedPreferences
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_LANGUAGE, language).apply()

            // Establecer el idioma globalmente
            val locale = Locale(language)
            Locale.setDefault(locale)

            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)

            // Aplica la nueva configuración a todos los recursos
            context.createConfigurationContext(config)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }

        // Recuperar el idioma guardado
        fun getSavedLanguage(context: Context): String {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(KEY_LANGUAGE, Locale.getDefault().language) ?: Locale.getDefault().language
        }

        // Cambiar el tema
        fun setAppTheme(context: Context, isDark: Boolean) {
            // Guardar el tema seleccionado
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_THEME, isDark).apply()

            // Aplicar el modo de tema oscuro o claro
            if (isDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Obtener el tema guardado
        fun getSavedTheme(context: Context): Boolean {
            val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_THEME, false) // por defecto, oscuro desactivado
        }
    }

    override fun onCreate() {
        super.onCreate()

        // Recupera el idioma y lo aplica
        val savedLanguage = getSavedLanguage(this)
        setAppLocale(this, savedLanguage)

        // Recupera y aplica el tema guardado
        val isDarkTheme = getSavedTheme(this)
        setAppTheme(this, isDarkTheme)
    }
}
