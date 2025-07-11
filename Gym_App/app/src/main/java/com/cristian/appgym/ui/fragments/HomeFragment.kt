package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cristian.appgym.databinding.FragmentHomeBinding
import com.cristian.appgym.util.UtilidadesBotones
import com.cristian.appgym.R

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Configurar los botones usando View Binding
        UtilidadesBotones.cambiarScreenConNavController(binding.btnLogin, this, R.id.action_home_to_login)
        UtilidadesBotones.cambiarScreenConNavController(binding.btnRegister, this, R.id.action_home_to_createUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
