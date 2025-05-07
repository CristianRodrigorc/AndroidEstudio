package com.cristian.appgym.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cristian.appgym.R
import com.cristian.appgym.databinding.FragmentUserDataBinding
import com.cristian.appgym.model.UserDataRequest
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import com.cristian.appgym.utils.SessionManager
import com.cristian.appgym.network.RetrofitClient
import com.cristian.appgym.util.UtilidadesSpinner
import kotlinx.coroutines.launch

class UserDataFragment : Fragment() {
    private var _binding: FragmentUserDataBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository
    private lateinit var sessionManager: SessionManager

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDataBinding.inflate(inflater, container, false)
        userRepository = UserRepository(RetrofitClient.apiService)
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // Cargar valores a los spinners
        UtilidadesSpinner.cargarValoresSpinner(this, binding.spnNivelDU, R.array.niveles_actividad)
        UtilidadesSpinner.cargarValoresSpinner(this, binding.spnDiasEntrenarDU, R.array.dias_entrenar)
        UtilidadesSpinner.cargarValoresSpinner(this, binding.spnPreferenciaHorarioDU, R.array.preferencia_Horario)
        UtilidadesSpinner.cargarValoresSpinner(this, binding.spnMotivacionDU, R.array.motivaciones)

        // Inicializar spinners con la primera opción seleccionada
        binding.spnNivelDU.setSelection(0, false)
        binding.spnDiasEntrenarDU.setSelection(0, false)
        binding.spnPreferenciaHorarioDU.setSelection(0, false)
        binding.spnMotivacionDU.setSelection(0, false)
    }

    private fun setupListeners() {
        binding.btnGuardarDU.setOnClickListener {
            guardarDatosUsuario()
        }
    }

    private fun guardarDatosUsuario() {
        val size = binding.etSizeDU.text.toString().toDoubleOrNull()
        val weight = binding.etWeightDU.text.toString().toDoubleOrNull()
        val physicalActivity = binding.spnNivelDU.selectedItem.toString()
        val daysTraining = binding.spnDiasEntrenarDU.selectedItem.toString().toIntOrNull()
        val healthProblems = binding.etProblemaSaludDU.text.toString()
        val preferenceSchedule = binding.spnPreferenciaHorarioDU.selectedItem.toString()
        val motivation = binding.spnMotivacionDU.selectedItem.toString()

        if (size == null || weight == null || daysTraining == null) {
            Toast.makeText(context, getString(R.string.error_userdata_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (physicalActivity.isEmpty() || healthProblems.isEmpty() || 
            preferenceSchedule.isEmpty() || motivation.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_userdata_complete), Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnGuardarDU.isEnabled = false

        val userDataRequest = UserDataRequest(
            userId = sessionManager.getUserId().toLong(),
            size = size,
            weight = weight,
            physicalActivity = physicalActivity,
            daysTraining = daysTraining,
            healthProblems = healthProblems,
            preferenceSchedule = preferenceSchedule,
            motivation = motivation
        )

        lifecycleScope.launch {
            try {
                when (val result = userRepository.guardarUserData(userDataRequest)) {
                    is Result.Success -> {
                        Toast.makeText(context, getString(R.string.success_userdata), Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_userData_to_profile)
                    }
                    is Result.Error -> {
                        Toast.makeText(context, getString(R.string.error_userdata_save, result.message), Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        // Ya estamos mostrando el ProgressBar
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, getString(R.string.error_userdata_save, e.message), Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
                binding.btnGuardarDU.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}