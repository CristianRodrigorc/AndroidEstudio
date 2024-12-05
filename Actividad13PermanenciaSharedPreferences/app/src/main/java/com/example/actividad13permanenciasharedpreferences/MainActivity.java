package com.example.actividad13permanenciasharedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText eTEntrada;
    private TextView tvDatos;
    private Button btnAplicar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        eTEntrada = findViewById(R.id.eTEntrada);
        tvDatos = findViewById(R.id.tvDatos);
        btnAplicar = findViewById(R.id.btnAplicar);

        btnAplicar.setOnClickListener(this::aplicarBoton);

        //Creamos un SharedPreference que guarda datos clave-valor
        //Context.MODE_PRIVATE: Este modo indica que el archivo de preferencias será accesible solo
        // por la aplicación que lo creó. Otros programas no podrán leer o modificar este archivo.
        // Creamos o accedemos a un archivo de SharedPreferences llamado "datos"
        SharedPreferences shared = getSharedPreferences("datos", Context.MODE_PRIVATE);

        // Recuperamos el valor guardado con la clave "mail" y lo asignamos al EditText y al TextView
        String mailGuardado = shared.getString("mail", ""); // "" como valor predeterminado
        tvDatos.setText(mailGuardado);   // Mostramos en el TextView
    }

    // Método que se ejecuta al hacer clic en un botón (debe estar enlazado en el archivo XML)
    public void aplicarBoton(View view) {
        // Accedemos nuevamente al archivo SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        // Obtenemos el texto ingresado por el usuario
        String nuevoMail = eTEntrada.getText().toString();

        // Guardamos el texto bajo la clave "mail"
        editor.putString("mail", nuevoMail);
        editor.commit(); // Guardamos los cambios

        // Mostramos el texto guardado en el TextView
        tvDatos.setText(nuevoMail);
    }



}