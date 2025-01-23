package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.UtilidadesSpinner

class UserDataScreen : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_data_screen)

        val etSizeDU = findViewById<EditText>(R.id.etSizeDU)
        val etWeightDU = findViewById<EditText>(R.id.etWeightDU)
        val spnNivelDU = findViewById<Spinner>(R.id.spnNivelDU)
        val spnDiasEntrenarDU = findViewById<Spinner>(R.id.spnDiasEntrenarDU)
        val etProblemaSaludDU = findViewById<EditText>(R.id.etProblemaSaludDU)
        val spnPreferenciaHorarioDU = findViewById<Spinner>(R.id.spnPreferenciaHorarioDU)
        val spnMotivacionDU = findViewById<Spinner>(R.id.spnMotivacionDU)

        UtilidadesSpinner.cargarValoresSpinner(this,spnNivelDU,R.array.niveles_actividad)
        UtilidadesSpinner.cargarValoresSpinner(this,spnDiasEntrenarDU,R.array.dias_entrenar)
        UtilidadesSpinner.cargarValoresSpinner(this,spnPreferenciaHorarioDU,R.array.preferencia_Horario)
        UtilidadesSpinner.cargarValoresSpinner(this,spnMotivacionDU,R.array.motivaciones)


    }
}