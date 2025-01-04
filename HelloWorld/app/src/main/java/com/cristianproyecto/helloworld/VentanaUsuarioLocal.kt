package com.cristianproyecto.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cristianproyecto.helloworld.databinding.ActivityVentanaDosBinding
import com.cristianproyecto.helloworld.records.LocalUser

class VentanaUsuarioLocal : AppCompatActivity() {

    private lateinit var binding: ActivityVentanaDosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVentanaDosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val localList = LocalUser()



        val list = findViewById<ListView>(R.id.listView)


    }

    fun volverMain (view: View) {
        when (view.id) {
            R.id.btnVolverMain -> {
                val i = Intent(this,VentanaPrincipal::class.java)
                startActivity(i)
            }
        }
    }
}