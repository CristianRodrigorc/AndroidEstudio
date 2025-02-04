package com.cristianproyecto.appgym.util

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R


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
            val sexT = UtilidadesRadioGroup.getOptionBtn(currentActivity, sex)
            val dbMethods = MetodoDataBase(currentActivity)

            if (stringValido(nameT)) {
                if (stringValido(lastNameT)) {
                    if (esCorreoValido(emailT)) {
                        if (stringValido(usernameT)) {
                            if (!dbMethods.comprobarUsernamePassword(usernameT)) {
                                if (stringValido(passwordT)) {
                                    if (stringValido(dateT)) {
                                        if (stringValido(sexT)) {
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
                                                currentActivity.getString(R.string.errorSexoNoSelect),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            currentActivity,
                                            "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvDate)}...",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        currentActivity,
                                        "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvContra)}...",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    currentActivity,
                                    currentActivity.getString(R.string.errorUsuarioExiste),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                currentActivity,
                                "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvEmail)}...",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            currentActivity,
                            "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvEmail)}...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        currentActivity,
                        "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvLastName)}...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    currentActivity,
                    "${currentActivity.getString(R.string.errorDatosIngresados)} ${currentActivity.getString(R.string.tvName)}...",
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


    fun loginUsuario(
        btn: Button,
        currentActivity: AppCompatActivity,
        usernameEditText: EditText,
        passwordEditText: EditText,
        targetActivity: Class<out AppCompatActivity>
    ) {
        btn.setOnClickListener {
            val username = UtilidadesText.getEditText(usernameEditText).trim()
            val password = UtilidadesText.getEditText(passwordEditText).trim()

            if (stringValido(username) && stringValido(password)) {
                val db = MetodoDataBase(currentActivity)
                val loginExitoso = db.comprobarUserPass(username, password)

                if (loginExitoso) {
                    Toast.makeText(currentActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(currentActivity, targetActivity)
                    intent.putExtra("UserName", username)
                    currentActivity.startActivity(intent)
                } else {
                    Toast.makeText(currentActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(currentActivity, "Completa ambos campos", Toast.LENGTH_SHORT).show()
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
        val regex = Regex("[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}")
        return correo != null && correo.matches(regex)
    }
}