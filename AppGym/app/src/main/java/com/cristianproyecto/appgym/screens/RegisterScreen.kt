package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.UtilidadesRadioGroup
import com.cristianproyecto.appgym.util.UtilidadesText

class RegisterScreen : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_screen2)


        val etNameSS = findViewById<EditText>(R.id.etNameSS)
        val etLastNameSS = findViewById<EditText>(R.id.etLastNameSS)
        val etEmailSS = findViewById<EditText>(R.id.etEmailSS)
        val etUsernameSS = findViewById<EditText>(R.id.etUsernameSS)
        val etPasswordSS = findViewById<EditText>(R.id.etPasswordSS)
        val etDateSS = findViewById<EditText>(R.id.etDateSS)
        val rgSexSS = findViewById<RadioGroup>(R.id.rgSexSS)
        val btnRegisterSS = findViewById<Button>(R.id.btnRegisterSS)
        val btnBackSS = findViewById<Button>(R.id.btnBackSS)


        etDateSS.setOnClickListener{UtilidadesText.mostrarCalendarioET(this,etDateSS)}
        //val sexSelect =  UtilidadesRadioGroup.getOptionBtn(this, rgSexSS)


        UtilidadesBotones.cambiarScreen(
            btnRegisterSS,
            this,
            UserDataScreen::class.java,
            etNameSS,
            etLastNameSS,
            etEmailSS,
            etUsernameSS,
            etPasswordSS,
            etDateSS,
            rgSexSS)
        UtilidadesBotones.cambiarScreen(btnBackSS,this, MainActivity::class.java)
    }
}

/*
El error que tenía era que capturaba el valor desde que se creaba la ventana y en ese momento los edit text estaban vacíos
 */