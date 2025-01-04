package com.cristianproyecto.helloworld

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.helloworld.databinding.ActivityRegistroUsuarioBinding

class VentanaRegistroUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etUserCreation = findViewById<EditText>(R.id.etUserCreation)
        val etPassCreation = findViewById<EditText>(R.id.etPassCreation)
        val etPassConfigCreation = findViewById<EditText>(R.id.etPassConfigCreation)
        val etEmailCreation = findViewById<EditText>(R.id.etEmailCreation)
    }

    fun createUser (view: View){

    }

    fun resetRecord(view: View){
        binding.etUserCreation.text.clear()
        binding.etPassCreation.text.clear()
        binding.etPassConfigCreation.text.clear()
        binding.etEmailCreation.text.clear()
    }
}