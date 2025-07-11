package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentLoginBinding
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.viewmodel.UserViewModel
import com.cristian.appgym.utils.SessionManager

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        
        // Inicializar ViewModel
        val userRepository = UserRepository(RetrofitClient.apiService)
        userViewModel = UserViewModel(userRepository)
        
        // Inicializar SessionManager
        sessionManager = SessionManager(requireContext())
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar listeners
        binding.btnLoginLoginS.setOnClickListener {
            realizarLogin()
        }

        binding.btnHomeLoginS.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }

        // Observar cambios en el ViewModel
        userViewModel.usuario.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                // Guardar datos de sesión
                sessionManager.saveUserSession(
                    userId = it.id?.toInt() ?: 0,
                    username = it.username
                )
                
                Toast.makeText(context, getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_login_to_user)
            }
        }

        userViewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                userViewModel.clearError()
            }
        }

        userViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLoginLoginS.isEnabled = !isLoading
        }
    }

    private fun realizarLogin() {
        val username = binding.etUsernameLoginS.text.toString()
        val password = binding.etPassLoginS.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_complete_fields), Toast.LENGTH_SHORT).show()
            return
        }

        // Realizar login usando username
        userViewModel.loginByUsername(username, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}