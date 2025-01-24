package com.cristianproyecto.appgym.util

import android.content.Intent
import android.widget.Button
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
        name: String,
        lastName: String,
        email: String,
        username: String,
        password: String,
        date: String,
        sex: String
    ) {
        btn.setOnClickListener {
            if (stringValido(name) &&
                stringValido(lastName) &&
                esCorreoValido(email) &&
                stringValido(username) &&
                stringValido(password) &&
                stringValido(date) &&
                stringValido(sex)
            ) {

                val intent = Intent(currentActivity, targetActivity)
                intent.putExtra("Name", name)
                intent.putExtra("LastName", lastName)
                intent.putExtra("Email", email)
                intent.putExtra("UserName", username)
                intent.putExtra("PassWord", password)
                intent.putExtra("Date", date)
                intent.putExtra("Sex", sex)
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
        size: Int,
        weight: Int,
        spnNivel: String,
        spnDiasEntrenar: String,
        problemaSalud: String,
        spnPreferenciaHorario: String,
        spnMotivacion: String,
    ) {
        btn.setOnClickListener {
            if (intValido(size) && intValido(weight)) {
                Toast.makeText(
                    currentActivity,
                    "El valor de $size y $weight no puede ser 0 o estar vacío...",
                    Toast.LENGTH_SHORT
                ).show()
                val metodoDataBase = MetodoDataBase(currentActivity)
                val userId =
                    metodoDataBase.insertUser(username, password, name, lastName, date, email, sex)

                if (userId > 0) {
                    val resultado = metodoDataBase.insertUserData(
                        userId.toInt(),
                        size, weight,
                        spnNivel,
                        spnDiasEntrenar,
                        problemaSalud,
                        spnPreferenciaHorario,
                        spnMotivacion
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