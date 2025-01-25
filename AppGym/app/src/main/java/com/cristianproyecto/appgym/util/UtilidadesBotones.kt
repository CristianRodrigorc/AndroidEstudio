package com.cristianproyecto.appgym.util

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.screens.UserDataScreen


object UtilidadesBotones {

    fun cambiarScreen(
        btn: Button,
        currentActivity: AppCompatActivity,
        targetActivity: Class<out AppCompatActivity>
    ) {
        btn.setOnClickListener {
            val i = Intent(currentActivity, targetActivity)
            currentActivity.startActivity(i)
        }
    }

    fun cambiarScreen(
        btn: Button,
        currentActivity: AppCompatActivity,
        targetActivity: Class<out AppCompatActivity>,
        name: EditText,
        lastName: EditText,
        email: EditText,
        username: EditText,
        password: EditText,
        date: EditText,
        sex: RadioGroup
    ) {
        btn.setOnClickListener {
            val nameT = UtilidadesText.getEditText(name)
            val lastNameT = UtilidadesText.getEditText(lastName)
            val emailT = UtilidadesText.getEditText(email)
            val usernameT = UtilidadesText.getEditText(username)
            val passwordT = UtilidadesText.getEditText(password)
            val dateT = UtilidadesText.getEditText(date)
            val sexT = UtilidadesRadioGroup.getOptionBtn(currentActivity,sex)

            if (stringValido(nameT) &&
                stringValido(lastNameT) &&
                esCorreoValido(emailT) &&
                stringValido(usernameT) &&
                stringValido(passwordT) &&
                stringValido(dateT) &&
                stringValido(sexT)
            ) {

                val intent = Intent(currentActivity, targetActivity)
                intent.putExtra("Name", nameT)
                intent.putExtra("LastName", lastNameT)
                intent.putExtra("Email", emailT)
                intent.putExtra("UserName", usernameT)
                intent.putExtra("PassWord", passwordT)
                intent.putExtra("Date", dateT)
                intent.putExtra("Sex", sexT)
                currentActivity.startActivity(intent)
            } else {
                Toast.makeText(
                    currentActivity,
                    "Datos inválidos. Verifica los campos...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun cambiarScreen(
        btn: Button,
        currentActivity: AppCompatActivity,
        targetActivity: Class<out AppCompatActivity>,
        name: String,
        lastName: String,
        email: String,
        username: String,
        password: String,
        date: String,
        sex: String,
        size: EditText,
        weight: EditText,
        spnNivel: Spinner,
        spnDiasEntrenar: Spinner,
        problemaSalud: EditText,
        spnPreferenciaHorario: Spinner,
        spnMotivacion: Spinner,
    ) {
        btn.setOnClickListener {
            val sizeT = UtilidadesText.getInt(size)
            val weightT = UtilidadesText.getInt(weight)
            val spnNivelT = UtilidadesSpinner.getSpinnerSelect(spnNivel)
            val spnDiasEntrenarT = UtilidadesSpinner.getSpinnerSelect(spnDiasEntrenar)
            val problemaSaludT = UtilidadesText.getEditText(problemaSalud)
            val spnPreferenciaHorarioT = UtilidadesSpinner.getSpinnerSelect(spnPreferenciaHorario)
            val spnMotivacionT = UtilidadesSpinner.getSpinnerSelect(spnMotivacion)

            if (intValido(sizeT) && intValido(weightT)) {
                val metodoDataBase = MetodoDataBase(currentActivity)
                val userId =
                    metodoDataBase.insertUser(username, password, name, lastName, date, email, sex)

                if (userId > 0) {
                    val resultado = metodoDataBase.insertUserData(
                        userId.toInt(),
                        sizeT, weightT,
                        spnNivelT,
                        spnDiasEntrenarT,
                        problemaSaludT,
                        spnPreferenciaHorarioT,
                        spnMotivacionT
                    )
                    if (resultado > 0) {
                        Toast.makeText(
                            currentActivity,
                            "Datos guardados correctamente",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(currentActivity, targetActivity)
                        currentActivity.startActivity(intent)
                    } else {
                        Toast.makeText(
                            currentActivity,
                            "Error al guardar datos adicionales",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        currentActivity,
                        "Error al guardar datos básicos del usuario",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    currentActivity,
                    "Verifica que el tamaño y el peso sean válidos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun stringValido(string: String): Boolean {
        return string != null
    }

    fun intValido(num: Int): Boolean {
        return num > 0 && num != null
    }

    fun esCorreoValido(correo: String): Boolean {
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$".toRegex()
        return correo != null && correo.matches(regex)
    }
}

/*
Explicación de la Regex:
^: Comienza la cadena.
[A-Za-z0-9._%+-]+: Acepta letras, números y ciertos caracteres (., _, %, +, -) antes del @.
@: Debe contener un @.
[A-Za-z0-9.-]+: Acepta letras, números, . o - después del @.
\\.com$: Debe terminar en .com.
$: Fin de la cadena.
 */


/*
JAVA
    public void cambiarScreen(Button btn, AppCompatActivity currentActivity, Class<? extends AppCompatActivity> targetActivity) {
    btn.setOnClickListener(view -> {
        Intent intent = new Intent(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
    });
}
 */