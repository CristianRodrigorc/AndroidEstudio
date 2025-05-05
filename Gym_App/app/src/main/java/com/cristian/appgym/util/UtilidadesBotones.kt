package com.cristian.appgym.util

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.model.Usuario
import com.cristian.appgym.model.UserDataRequest
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UtilidadesBotones {

    fun cambiarScreenConNavController(
        btn: Button,
        fragment: Fragment,
        actionId: Int
    ) {
        btn.setOnClickListener {
            val navController = fragment.findNavController()
            navController.navigate(actionId)
        }
    }

    fun cambiarScreen(
        btn: Button,
        fragment: Fragment,
        actionId: Int,
        name: EditText,
        lastName: EditText,
        email: EditText,
        username: EditText,
        password: EditText,
        date: EditText,
        sex: RadioGroup,
        userRepository: UserRepository
    ) {
        btn.setOnClickListener {
            val context = fragment.requireContext()

            val nameT = UtilidadesText.getEditText(name)
            val lastNameT = UtilidadesText.getEditText(lastName)
            val emailT = UtilidadesText.getEditText(email)
            val usernameT = UtilidadesText.getEditText(username)
            val passwordT = UtilidadesText.getEditText(password)
            val dateT = UtilidadesText.getEditText(date)
            val sexT = UtilidadesRadioGroup.getOptionBtn(context, sex)

            if (stringValido(nameT) && stringValido(lastNameT) && esCorreoValido(emailT) &&
                stringValido(usernameT) && stringValido(passwordT) && 
                stringValido(dateT) && stringValido(sexT)) {

                val usuario = Usuario(
                    username = usernameT,
                    password = passwordT,
                    name = nameT,
                    lastname = lastNameT,
                    date = dateT,
                    email = emailT,
                    sex = sexT
                )

                CoroutineScope(Dispatchers.Main).launch {
                    when (val result = userRepository.crearUsuario(usuario)) {
                        is Result.Success -> {
                            val bundle = Bundle().apply {
                                putString("Name", nameT)
                                putString("LastName", lastNameT)
                                putString("Email", emailT)
                                putString("UserName", usernameT)
                                putString("PassWord", passwordT)
                                putString("Date", dateT)
                                putString("Sex", sexT)
                            }
                            fragment.findNavController().navigate(actionId, bundle)
                        }
                        is Result.Error -> {
                            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Loading -> {
                            // Mostrar progreso si es necesario
                        }
                    }
                }
            } else {
                mostrarToast(context, R.string.errorCompletarCampos)
            }
        }
    }

    fun cambiarScreen(
        btn: Button,
        fragment: Fragment,
        actionId: Int,
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
        userRepository: UserRepository
    ) {
        btn.setOnClickListener {
            val sizeT = UtilidadesText.getEditText(size).toDoubleOrNull()
            val weightT = UtilidadesText.getEditText(weight).toDoubleOrNull()
            val spnNivelT = UtilidadesSpinner.getSpinnerSelect(spnNivel)
            val spnDiasEntrenarT = UtilidadesSpinner.getSpinnerSelect(spnDiasEntrenar).toIntOrNull()
            val problemaSaludT = UtilidadesText.getEditText(problemaSalud)
            val spnPreferenciaHorarioT = UtilidadesSpinner.getSpinnerSelect(spnPreferenciaHorario)
            val spnMotivacionT = UtilidadesSpinner.getSpinnerSelect(spnMotivacion)

            if (sizeT != null && weightT != null && stringValido(spnNivelT) &&
                spnDiasEntrenarT != null && stringValido(problemaSaludT) &&
                stringValido(spnPreferenciaHorarioT) && stringValido(spnMotivacionT)) {

                val userDataRequest = UserDataRequest(
                    userId = 0, // Se actualizará con el ID del usuario
                    size = sizeT,
                    weight = weightT,
                    physicalActivity = spnNivelT,
                    daysTraining = spnDiasEntrenarT,
                    healthProblems = problemaSaludT,
                    preferenceSchedule = spnPreferenciaHorarioT,
                    motivation = spnMotivacionT
                )

                CoroutineScope(Dispatchers.Main).launch {
                    when (val result = userRepository.guardarUserData(userDataRequest)) {
                        is Result.Success -> {
                            val bundle = Bundle().apply {
                                putString("Name", name)
                                putString("LastName", lastName)
                                putString("Email", email)
                                putString("UserName", username)
                                putString("PassWord", password)
                                putString("Date", date)
                                putString("Sex", sex)
                            }
                            fragment.findNavController().navigate(actionId, bundle)
                        }
                        is Result.Error -> {
                            Toast.makeText(fragment.requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Loading -> {
                            // Mostrar progreso si es necesario
                        }
                    }
                }
            } else {
                mostrarToast(fragment.requireContext(), R.string.errorCompletarCampos)
            }
        }
    }

    fun loginUsuario(
        btn: Button,
        fragment: Fragment,
        usernameEditText: EditText,
        passwordEditText: EditText,
        navActionId: Int,
        userRepository: UserRepository
    ) {
        btn.setOnClickListener {
            val username = UtilidadesText.getEditText(usernameEditText).trim()
            val password = UtilidadesText.getEditText(passwordEditText).trim()

            if (stringValido(username) && stringValido(password)) {
                CoroutineScope(Dispatchers.Main).launch {
                    when (val result = userRepository.obtenerUsuarioPorUsername(username)) {
                        is Result.Success -> {
                            val usuario = result.data
                            if (usuario.password == password) {
                                Toast.makeText(fragment.requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                                val bundle = Bundle()
                                bundle.putString("UserName", username)
                                fragment.findNavController().navigate(navActionId, bundle)
                            } else {
                                Toast.makeText(fragment.requireContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                            }
                        }
                        is Result.Error -> {
                            Toast.makeText(fragment.requireContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Loading -> {
                            // Mostrar progreso si es necesario
                        }
                    }
                }
            } else {
                Toast.makeText(fragment.requireContext(), "Completa ambos campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun stringValido(texto: String): Boolean {
        return texto.isNotBlank()
    }

    fun intValido(numero: Int): Boolean {
        return numero > 0
    }

    fun esCorreoValido(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun mostrarToast(context: Context, mensajeId: Int) {
        Toast.makeText(context, context.getString(mensajeId), Toast.LENGTH_SHORT).show()
    }
}