package com.cristianproyecto.appgym.util

import android.content.Context
import android.widget.Spinner
import android.widget.Switch
import com.cristianproyecto.appgym.MyApplication

object UtilidadesInterfaz {

    fun cambiarLanguaje(context: Context, spinner: Spinner) {
        val election: String = UtilidadesSpinner.getSpinnerSelect(spinner)

        MyApplication.setAppLocale(context, election)

        if (context is android.app.Activity) {
            context.recreate()
        }
    }

    fun cambiarTema(context: Context, switch: Switch) {
        val isDarkTheme = switch.isChecked

        MyApplication.setAppTheme(context, isDarkTheme)
    }
}
