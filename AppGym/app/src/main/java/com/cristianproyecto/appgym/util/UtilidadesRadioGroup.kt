package com.cristianproyecto.appgym.util

import android.widget.RadioGroup

object UtilidadesRadioGroup {

    fun getOptionBtn(radioGroup: RadioGroup):String{
        return radioGroup.isSelected.toString()
    }
}