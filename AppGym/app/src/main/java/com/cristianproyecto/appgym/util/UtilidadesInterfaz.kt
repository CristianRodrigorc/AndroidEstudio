package com.cristianproyecto.appgym.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

object UtilidadesInterfaz {

    fun cambiarLanguaje(context: Activity, spinner: Spinner){
        val election:String = UtilidadesSpinner.getSpinnerSelect(spinner)
        // Crear un nuevo Locale basado en la selección
        val locale = Locale(election)
        //Establecer cómo preterminado
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        // Crea un nuevo contexto
        val newContext = context.createConfigurationContext(config)

        // Actualiza los recursos
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        // Reinicia la actividad
        context.recreate()
    }
}


/* ES PREFERIBLE USAR CONTEXT A ACTIVITY PARA MÁS FLEXIBILIDAD*/