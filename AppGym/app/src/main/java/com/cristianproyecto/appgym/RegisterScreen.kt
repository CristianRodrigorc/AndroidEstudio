package com.cristianproyecto.appgym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterScreen : AppCompatActivity() {

    private val navigationMethods = NavigationMethods()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_screen2)

        val btnRegisterSS = findViewById<Button>(R.id.btnRegisterSS)
        val btnBackSS = findViewById<Button>(R.id.btnBackSS)

        navigationMethods.cambiarScreen(btnRegisterSS,this,UserDataScreen::class.java)
        navigationMethods.cambiarScreen(btnBackSS,this,MainActivity::class.java)
    }
}