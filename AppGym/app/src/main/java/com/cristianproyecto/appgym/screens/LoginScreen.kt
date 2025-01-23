package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.UtilidadesBotones

class LoginScreen : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_screen)

        val etUsernameLoginS = findViewById<EditText>(R.id.etUsernameLoginS)
        val etPassLoginS = findViewById<EditText>(R.id.etPassLoginS)

        val btnLoginLoginS = findViewById<Button>(R.id.btnLoginLoginS)
        val btnHomeLoginS = findViewById<Button>(R.id.btnHomeLoginS)

        UtilidadesBotones.cambiarScreen(btnHomeLoginS,this, MainActivity::class.java)

    }
}