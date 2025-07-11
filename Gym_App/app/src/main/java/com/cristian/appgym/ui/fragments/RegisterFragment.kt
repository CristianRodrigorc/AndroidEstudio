package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentRegisterBinding
import com.cristian.appgym.data.model.RegisterRequest
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.util.UtilidadesText
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        userRepository = UserRepository(RetrofitClient.apiService)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el listener para el campo de fecha
        binding.etDateSS.setOnClickListener {
            UtilidadesText.mostrarCalendarioET(requireContext(), binding.etDateSS)
        }

        binding.btnRegisterSS.setOnClickListener {
            registrarUsuario()
        }

        binding.btnBackSS.setOnClickListener {
            findNavController().navigate(R.id.action_registerUser_to_home)
        }
    }

    private fun registrarUsuario() {
        val email = binding.etEmailSS.text.toString()
        val username = binding.etUsernameSS.text.toString()
        val password = binding.etPasswordSS.text.toString()
        val name = binding.etNameSS.text.toString()
        val lastName = binding.etLastNameSS.text.toString()
        val dateStr = binding.etDateSS.text.toString()
        val sex = when (binding.rgSexSS.checkedRadioButtonId) {
            R.id.rbtnMale -> "MALE"
            R.id.rbtnFemale -> "FEMALE"
            else -> ""
        }

        // Validaciones básicas
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || 
            name.isEmpty() || lastName.isEmpty() || dateStr.isEmpty() || sex.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_complete_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, getString(R.string.error_invalid_email), Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(context, getString(R.string.error_password_length), Toast.LENGTH_SHORT).show()
            return
        }

        // Mostrar ProgressBar y deshabilitar botón
        binding.progressBar.visibility = View.VISIBLE
        binding.btnRegisterSS.isEnabled = false

        lifecycleScope.launch {
            try {
                // Verificar si el email ya existe
                when (val emailResult = userRepository.verificarEmailExistente(email)) {
                    is Result.Success -> {
                        if (emailResult.data) {
                            binding.progressBar.visibility = View.GONE
                            binding.btnRegisterSS.isEnabled = true
                            Toast.makeText(context, getString(R.string.error_email_exists), Toast.LENGTH_SHORT).show()
                            return@launch
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnRegisterSS.isEnabled = true
                        Toast.makeText(context, emailResult.message, Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    is Result.Loading -> {
                        // Ya estamos mostrando el ProgressBar
                    }
                }

                // Verificar si el username ya existe
                when (val usernameResult = userRepository.verificarUsernameExistente(username)) {
                    is Result.Success -> {
                        if (usernameResult.data) {
                            binding.progressBar.visibility = View.GONE
                            binding.btnRegisterSS.isEnabled = true
                            Toast.makeText(context, getString(R.string.error_username_exists), Toast.LENGTH_SHORT).show()
                            return@launch
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnRegisterSS.isEnabled = true
                        Toast.makeText(context, usernameResult.message, Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    is Result.Loading -> {
                        // Ya estamos mostrando el ProgressBar
                    }
                }

                // Convertir la fecha al formato PostgreSQL y validar edad
                val date = try {
                    val birthDate = LocalDate.parse(dateStr, dateFormatter)
                    val today = LocalDate.now()
                    val age = today.year - birthDate.year - 
                        (if (today.monthValue < birthDate.monthValue || 
                            (today.monthValue == birthDate.monthValue && today.dayOfMonth < birthDate.dayOfMonth)) 1 else 0)
                    
                    if (age < 18) {
                        Toast.makeText(context, getString(R.string.error_under_age), Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                        binding.btnRegisterSS.isEnabled = true
                        return@launch
                    }
                    
                    birthDate
                } catch (e: Exception) {
                    Toast.makeText(context, getString(R.string.error_invalid_date), Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    binding.btnRegisterSS.isEnabled = true
                    return@launch
                }

                // Crear el RegisterRequest
                val registerRequest = RegisterRequest(
                    nombre = name,
                    apellidos = lastName,
                    email = email,
                    username = username,
                    password = password,
                    fechaNacimiento = date.format(dateFormatter), // Convertir LocalDate a String
                    sexo = sex
                )

                when (val result = userRepository.crearUsuario(registerRequest)) {
                    is Result.Success -> {
                        Toast.makeText(context, getString(R.string.success_register), Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerUser_to_home)
                    }
                    is Result.Error -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        // Ya estamos mostrando el ProgressBar
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, getString(R.string.error_register, e.message), Toast.LENGTH_SHORT).show()
            } finally {
                // Ocultar ProgressBar y habilitar botón
                binding.progressBar.visibility = View.GONE
                binding.btnRegisterSS.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}