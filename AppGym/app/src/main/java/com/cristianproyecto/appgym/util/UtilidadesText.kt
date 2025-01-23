package com.cristianproyecto.appgym.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.util.Calendar

object UtilidadesText {

    fun mostrarCalendarioET(context: Context, editText: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //Creamos y mostramos el DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            context,
            {_, selectedYear, selectedMonth, selectedDay ->
                val formatDate = selectedYear.toString() + "-${(selectedMonth + 1).toString().padStart(2, '0')}"+
                        "-${selectedDay.toString().padStart(2, '0')}"
                editText.setText(formatDate)
            },
            year,month,day
        )
        datePickerDialog.show()
    }

    fun getEditText(editText: EditText):String{
        return editText.text.toString()
    }

}