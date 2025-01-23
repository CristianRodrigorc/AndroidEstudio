package com.cristianproyecto.appgym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginScreen : AppCompatActivity() {

    private val navigationMethods = NavigationMethods()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_screen)

        val btnLoginLoginS = findViewById<Button>(R.id.btnLoginLoginS)
        val btnHomeLoginS = findViewById<Button>(R.id.btnHomeLoginS)

        navigationMethods.cambiarScreen(btnHomeLoginS,this,MainActivity::class.java)

    }
}