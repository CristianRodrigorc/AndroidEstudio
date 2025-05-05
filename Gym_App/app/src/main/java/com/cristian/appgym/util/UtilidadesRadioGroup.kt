package com.cristian.appgym.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RadioButton
import android.widget.RadioGroup

object UtilidadesRadioGroup {

    @SuppressLint("DiscouragedApi")
    fun getOptionBtn(context: Context, radioGroup: RadioGroup): String {
        // Obtener el ID del botón seleccionado
        val selectedId = radioGroup.checkedRadioButtonId

       return if (selectedId != -1){
            val selectedButton = radioGroup.findViewById<RadioButton>(selectedId)
            selectedButton.text.toString()
        }else{
            "" //retorna vacio
        }
    }
}