package com.cristianproyecto.appgym

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(updateContextWithLocale(newBase))
    }

    // Método para actualizar el contexto con el idioma actual
    private fun updateContextWithLocale(context: Context?): Context? {
        val language = MyApplication.getSavedLanguage(context!!)
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }
}