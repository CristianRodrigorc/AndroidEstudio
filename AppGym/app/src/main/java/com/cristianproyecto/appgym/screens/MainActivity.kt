package com.cristianproyecto.appgym.screens

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cristianproyecto.appgym.R
import com.cristianproyecto.appgym.util.UtilidadesBotones
import com.cristianproyecto.appgym.util.MetodoDataBase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        UtilidadesBotones.cambiarScreen(btnLogin, this, LoginScreen::class.java)
        UtilidadesBotones.cambiarScreen(btnRegister, this, RegisterScreen::class.java)


    }
}
/*
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
     */

/*
    //Primera instancia de la base de datos para crearla
    val dbMethods = MetodoDataBase(this)//Instanciamos la clase con los metodos
    val db = dbMethods.writableDatabase//Abrir o crear la base de datos

    if (db.isOpen){
        Toast.makeText(this,"Base de datos creada Correctamente",Toast.LENGTH_SHORT).show()
    }else{
        Toast.makeText(this,"Error al crear la DB... ", Toast.LENGTH_SHORT).show()
    }

*/