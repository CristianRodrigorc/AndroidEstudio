package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentLoginBinding
import com.cristian.appgym.model.Usuario
import com.cristian.appgym.network.LoginRequest
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.utils.SessionManager
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        userRepository = UserRepository(RetrofitClient.apiService)
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginLoginS.setOnClickListener {
            iniciarSesion()
        }

        binding.btnHomeLoginS.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }
    }

    private fun iniciarSesion() {
        val username = binding.etUsernameLoginS.text.toString()
        val password = binding.etPassLoginS.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Mostrar ProgressBar y deshabilitar botón
        binding.progressBarLogin.visibility = View.VISIBLE
        binding.btnLoginLoginS.isEnabled = false

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val usuario = response.body()
                    if (usuario != null && usuario.id_user != null) {
                        // Guardar la sesión del usuario
                        sessionManager.saveUserSession(usuario.id_user.toInt(), usuario.username)
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        // Navegar a la pantalla de usuario
                        findNavController().navigate(R.id.action_login_to_user)
                    } else {
                        Toast.makeText(context, "Error: Usuario no válido", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                // Ocultar ProgressBar y habilitar botón
                binding.progressBarLogin.visibility = View.GONE
                binding.btnLoginLoginS.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}