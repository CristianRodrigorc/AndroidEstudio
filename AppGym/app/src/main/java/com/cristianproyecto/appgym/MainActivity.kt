package com.cristianproyecto.appgym

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)


    }

    fun clickOption(view: View) {
        when(view.id) {
            R.id.btnLogin -> {
                val i = Intent(this, LoginScreen::class.java)
                startActivity(i)
            }
            R.id.btnRegister ->{
                val i = Intent(this,RegisterScreen::class.java)
                startActivity(i)
            }
        }
    }
}