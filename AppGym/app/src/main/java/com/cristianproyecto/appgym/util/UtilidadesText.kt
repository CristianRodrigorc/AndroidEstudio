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


    /*
    setupFocusChangeListener(myEditText) { inputText ->
    if (inputText.isNotEmpty()) {
        // Acción si el texto no está vacío
        Toast.makeText(this, "Texto ingresado: $inputText", Toast.LENGTH_SHORT).show()
    } else {
        // Acción si el campo está vacío
        Toast.makeText(this, "Por favor, llena este campo", Toast.LENGTH_SHORT).show()
    }
}




    val nameEditText = findViewById<EditText>(R.id.etName)
    val emailEditText = findViewById<EditText>(R.id.etEmail)

    // Configurar focus listener para el campo de nombre
    setupFocusChangeListener(nameEditText) { name ->
        if (name.isEmpty()) {
            nameEditText.error = "El nombre no puede estar vacío"
        }
    }

    // Configurar focus listener para el campo de email
    setupFocusChangeListener(emailEditText) { email ->
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Por favor, ingresa un email válido"
        }
    }
     */

}
/*
.toIntoOrNull():Este método intenta convertir la cadena de texto obtenida a un número entero (Int).
Si la conversión tiene éxito, devuelve el valor null
?: -1: Este es el operador Elvis (?:), que se utiliza para manejar valores nulos.
Si la expresión toIntOrNull() devuelve null. Se devolverá el numpero despues del ?
 */