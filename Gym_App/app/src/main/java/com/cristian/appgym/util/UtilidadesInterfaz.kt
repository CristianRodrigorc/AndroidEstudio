package com.cristian.appgym.util

import android.content.Context
import android.content.res.Configuration
import android.widget.Switch
import java.util.Locale
import androidx.appcompat.app.AppCompatDelegate

object UtilidadesInterfaz {

    fun cambiarLenguaje(context: Context, locale: Locale) {
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun cambiarTema(context: Context, switch: Switch) {
        val isDarkTheme = switch.isChecked
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES 
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun obtenerLenguajeGuardado(context: Context): String {
        val sharedPrefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getString("language", Locale.getDefault().language) ?: Locale.getDefault().language
    }

    fun obtenerTemaGuardado(context: Context): Boolean {
        val sharedPrefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean("dark_theme", false)
    }
}
