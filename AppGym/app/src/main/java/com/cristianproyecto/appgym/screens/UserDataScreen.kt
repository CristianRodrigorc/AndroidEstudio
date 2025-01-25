package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.MetodoDataBase
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.UtilidadesSpinner
import com.cristianproyecto.appgym.util.UtilidadesText

class UserDataScreen : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_data_screen)
        //Recoger datos
        val name = intent.getStringExtra("Name") ?: ""
        val lastName = intent.getStringExtra("LastName") ?: ""
        val email = intent.getStringExtra("Email") ?: ""
        val username = intent.getStringExtra("UserName") ?: ""
        val password = intent.getStringExtra("PassWord") ?: ""
        val date = intent.getStringExtra("Date") ?: ""
        val sex = intent.getStringExtra("Sex") ?: ""


        val etSizeDU = findViewById<EditText>(R.id.etSizeDU)
        val etWeightDU = findViewById<EditText>(R.id.etWeightDU)
        val spnNivelDU = findViewById<Spinner>(R.id.spnNivelDU)
        val spnDiasEntrenarDU = findViewById<Spinner>(R.id.spnDiasEntrenarDU)
        val etProblemaSaludDU = findViewById<EditText>(R.id.etProblemaSaludDU)
        val spnPreferenciaHorarioDU = findViewById<Spinner>(R.id.spnPreferenciaHorarioDU)
        val spnMotivacionDU = findViewById<Spinner>(R.id.spnMotivacionDU)
        val btnGuardarDU = findViewById<Button>(R.id.btnGuardarDU)

        UtilidadesSpinner.cargarValoresSpinner(this,spnNivelDU,R.array.niveles_actividad)
        UtilidadesSpinner.cargarValoresSpinner(this,spnDiasEntrenarDU,R.array.dias_entrenar)
        UtilidadesSpinner.cargarValoresSpinner(this,spnPreferenciaHorarioDU,R.array.preferencia_Horario)
        UtilidadesSpinner.cargarValoresSpinner(this,spnMotivacionDU,R.array.motivaciones)




        val dbMethods = MetodoDataBase(this)

        UtilidadesBotones.cambiarScreen(
            btnGuardarDU,
            this,
            MainActivity::class.java,
            name,
            lastName,
            email,
            username,
            password,
            date,
            sex,
            etSizeDU,
            etWeightDU,
            spnNivelDU,
            spnDiasEntrenarDU,
            etProblemaSaludDU,
            spnPreferenciaHorarioDU,
            spnMotivacionDU,
        )
    }
}