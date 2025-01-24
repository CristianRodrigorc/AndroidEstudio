package com.cristianproyecto.appgym.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RadioGroup

object UtilidadesRadioGroup {

    @SuppressLint("DiscouragedApi")
    fun getOptionBtn(context: Context, radioGroup: RadioGroup): String {
        // Obtener el ID del botón seleccionado
        val selectedId = radioGroup.checkedRadioButtonId

        // Devolver el texto según el botón seleccionado
        return when (selectedId) {
            context.resources.getIdentifier("rbMale","id",context.packageName) -> "Male"
            context.resources.getIdentifier("rbFemale","id",context.packageName) -> "Female"
            else -> ""
        }
    }
}