package com.cristianproyecto.appgym.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import com.cristianproyecto.appgym.BaseActivity
import com.cristianproyecto.appgym.MyApplication
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.MetodoDataBase
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.UtilidadesInterfaz
import com.cristianproyecto.appgym.util.UtilidadesSpinner

class MainActivity : BaseActivity() {

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Primera instancia de la base de datos para crearla
        val dbMethods = MetodoDataBase(this)//Instanciamos la clase con los metodos
        val db = dbMethods.writableDatabase//Abrir o crear la base de datos

        /*
        if (db.isOpen){
            Toast.makeText(this,"Base de datos creada Correctamente",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error al crear la DB... ", Toast.LENGTH_SHORT).show()
        }
         */

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val spnLenguaje = findViewById<Spinner>(R.id.spnLenguaje)
        val switchTemaMain = findViewById<Switch>(R.id.switchTemaMain)
        val btnCLenguaje = findViewById<Button>(R.id.btnCLenguaje)


        UtilidadesSpinner.cargarValoresSpinner(this, spnLenguaje, R.array.lenguajes)
        UtilidadesBotones.cambiarScreen(btnLogin, this, LoginScreen::class.java)
        UtilidadesBotones.cambiarScreen(btnRegister, this, RegisterScreen::class.java)

        btnCLenguaje.setOnClickListener { UtilidadesInterfaz.cambiarLanguaje(this,spnLenguaje) }
        switchTemaMain.setOnCheckedChangeListener{ _, isChecked -> UtilidadesInterfaz.cambiarTema(this,switchTemaMain)}
        val isDarkTheme = MyApplication.getSavedTheme(this)
        switchTemaMain.isChecked = isDarkTheme
    }
}

/*
INSERT INTO tabla_usuarios (username, password, name, lastname, date, email, sex)
VALUES ('usuarioEjemplo', 'password123', 'Juan', 'Pérez', '1990-05-10', 'juan.perez@email.com', 'Masculino');

INSERT INTO tabla_datos_usuarios (id_user, size, weigth, actividad_fisica, dias_entrenar, problemas_salud, preferencia_horario, motivacion)
VALUES (1, 180, 75, 'Gimnasio', 'Lunes, Miércoles, Viernes', 'Ninguno', 'Mañana', 'Mejorar mi salud y forma física');

 */