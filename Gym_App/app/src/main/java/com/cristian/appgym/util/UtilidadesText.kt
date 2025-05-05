package com.cristian.appgym.util

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
                // Formateamos la fecha en el formato YYYY-MM-DD
                val formatDate = String.format(
                    "%04d-%02d-%02d",
                    selectedYear,
                    selectedMonth + 1, // Los meses en Calendar van de 0 a 11
                    selectedDay
                )
                editText.setText(formatDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    fun getEditText(editText: EditText):String{
        return editText.text.toString().trim()
    }

    fun getInt(editText: EditText): Int{
        return editText.text.toString().toIntOrNull() ?: -1
    }

    fun setupFocusChangeListener(editText: EditText, onFocusLost: (String) -> Unit) {
        editText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) { // El foco se pierde
                val inputText = (view as EditText).text.toString()
                onFocusLost(inputText) // Llama al callback con el texto ingresado
            }
        }
    }



}
/*
.toIntoOrNull():Este método intenta convertir la cadena de texto obtenida a un número entero (Int).
Si la conversión tiene éxito, devuelve el valor null
?: -1: Este es el operador Elvis (?:), que se utiliza para manejar valores nulos.
Si la expresión toIntOrNull() devuelve null. Se devolverá el numpero despues del ?
 */