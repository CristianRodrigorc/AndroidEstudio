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
import com.cristian.appgym.databinding.FragmentProfileBinding
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.SessionManager
import com.cristian.appgym.utils.Result
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        userRepository = UserRepository(RetrofitClient.apiService)
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        loadUserData()
    }

    private fun setupUI() {
        // Mostrar ID del usuario
        val userId = sessionManager.getUserId()
        binding.tvUserId.text = getString(R.string.user_id_format, if (userId != -1) userId.toString() else getString(R.string.default_value))
    }

    private fun setupListeners() {
        binding.btnMenuProfile.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userDataFragment)
        }

        binding.btnLogout.setOnClickListener {
            sessionManager.clearSession()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    binding.drawerLayout.close()
                    true
                }
                // ... otros casos del menú ...
                else -> false
            }
        }
    }

    private fun loadUserData() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val userId = sessionManager.getUserId()
                if (userId != -1) {
                    // Obtener datos básicos del usuario
                    val userResult = userRepository.obtenerUsuario(userId.toLong())
                    when (userResult) {
                        is Result.Success -> {
                            val usuario = userResult.data
                            // Datos básicos de la tabla usuarios - usando el nuevo modelo
                            binding.tvName.text = "${usuario.nombre} ${usuario.apellidos}"
                            binding.tvEmail.text = usuario.email
                            binding.tvUserId.text = getString(R.string.user_id_format, usuario.id?.toString() ?: getString(R.string.default_value))

                            // Obtener datos adicionales
                            val userDataResult = userRepository.obtenerUserData(userId.toLong())
                            when (userDataResult) {
                                is Result.Success -> {
                                    val userData = userDataResult.data
                                    binding.tvHeight.text = userData.size?.toString() ?: getString(R.string.default_value)
                                    binding.tvWeight.text = userData.weight?.toString() ?: getString(R.string.default_value)
                                    binding.tvActivityLevel.text = userData.physicalActivity ?: getString(R.string.default_value)
                                    binding.tvTrainingDays.text = userData.daysTraining?.toString() ?: getString(R.string.default_value)
                                    binding.tvPreferredTime.text = userData.preferenceSchedule ?: getString(R.string.default_value)
                                    binding.tvHealthIssues.text = userData.healthProblems ?: getString(R.string.default_value)
                                    binding.tvMotivation.text = userData.motivation ?: getString(R.string.default_value)
                                }
                                is Result.Error -> {
                                    // Si hay error al obtener datos adicionales, mostrar valores por defecto
                                    binding.tvHeight.text = getString(R.string.default_value)
                                    binding.tvWeight.text = getString(R.string.default_value)
                                    binding.tvActivityLevel.text = getString(R.string.default_value)
                                    binding.tvTrainingDays.text = getString(R.string.default_value)
                                    binding.tvPreferredTime.text = getString(R.string.default_value)
                                    binding.tvHealthIssues.text = getString(R.string.default_value)
                                    binding.tvMotivation.text = getString(R.string.default_value)
                                    Toast.makeText(context, userDataResult.message, Toast.LENGTH_SHORT).show()
                                }
                                is Result.Loading -> {
                                    // Ya estamos mostrando el ProgressBar
                                }
                            }
                        }
                        is Result.Error -> {
                            Toast.makeText(context, userResult.message, Toast.LENGTH_SHORT).show()
                        }
                        is Result.Loading -> {
                            // Ya estamos mostrando el ProgressBar
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar los datos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 