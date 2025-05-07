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
            login()
        }

        binding.btnHomeLoginS.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }
    }

    private fun login() {
        val username = binding.etUsernameLoginS.text.toString()
        val password = binding.etPassLoginS.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_login_fields), Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                binding.progressBarLogin.visibility = View.VISIBLE
                binding.btnLoginLoginS.isEnabled = false

                val response = RetrofitClient.apiService.login(LoginRequest(username, password))
                
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        // Guardar la sesión del usuario
                        usuario.id_user?.let { id ->
                            sessionManager.saveUserSession(id.toInt(), usuario.username)
                            Toast.makeText(context, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                            // Navegar a la pantalla de usuario
                            findNavController().navigate(R.id.action_login_to_user)
                        } ?: run {
                            Toast.makeText(context, getString(R.string.error_login_invalid), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    when (response.code()) {
                        401 -> Toast.makeText(context, getString(R.string.error_login_invalid), Toast.LENGTH_SHORT).show()
                        404 -> Toast.makeText(context, getString(R.string.error_login_user), Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(context, getString(R.string.error_login_connection, response.message()), Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, getString(R.string.error_login_connection, e.message), Toast.LENGTH_SHORT).show()
            } finally {
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