package com.cristian.appgym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cristian.appgym.util.UtilidadesBotones
import com.cristian.appgym.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inicializar los elementos de la interfaz
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)

        // Configurar los botones
        UtilidadesBotones.cambiarScreenConNavController(btnLogin, this, R.id.action_home_to_login)
        UtilidadesBotones.cambiarScreenConNavController(btnRegister, this, R.id.action_home_to_createUser)

        return view
    }
}
