package com.cristianproyecto.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.helloworld.databinding.ActivityMainBinding

class VentanaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Iniciar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnAcceso = findViewById<Button>(R.id.btnAcceso)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)

    }

    fun clickOption(view: View) {
        when (view.id) {
            R.id.btnAcceso -> {
                val etUser = binding.etUser
                val etPass = binding.etPass
                if (etUser != null && etPass != null) {
                    val intent = Intent(this, VentanaUsuarioLocal::class.java)
                    startActivity(intent)
                }
            }

            R.id.btnAccesoLocal -> {
                val i = Intent(this,VentanaUsuarioLocal::class.java)
                startActivity(i)
            }

            R.id.btnRegistro -> {
                val intent = Intent(this, VentanaRegistroUsuario::class.java)
                startActivity(intent)
            }
        }
    }
}