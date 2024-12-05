package com.example.practicasexamen.pasardatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practicasexamen.R;

public class RecibirDatos1 extends AppCompatActivity {

    private TextView recibir1TvSalida;
    private Button recibir1BtnVolver;
    private String textoRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recibir_datos1);

        recibir1TvSalida = findViewById(R.id.recibir1TvSalida);
        recibir1BtnVolver = findViewById(R.id.recibir1BtnVolver);

        //Forma de recibir el intent
        textoRecibido = getIntent().getStringExtra("dato");

        recibir1TvSalida.setText("Hola "+ textoRecibido);

        recibir1BtnVolver.setOnClickListener(v -> regresar(v));
    }

    public void regresar(View view){
        Intent intent = new Intent(this, PasarDatos1.class);
        //Iniciamos el intent
        startActivity(intent);
    }
}