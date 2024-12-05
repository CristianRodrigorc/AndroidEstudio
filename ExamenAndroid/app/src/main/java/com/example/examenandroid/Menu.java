package com.example.examenandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    private Button btnGuardar1;
    private Button btnMostrarContacto;
    private Button btnListarContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        btnGuardar1 = findViewById(R.id.btnGuardar1);
        btnMostrarContacto = findViewById(R.id.btnMostrarContacto);
        btnListarContactos = findViewById(R.id.btnListarContactos);

        btnGuardar1.setOnClickListener(v -> irVentanGuardar(v));
        btnMostrarContacto.setOnClickListener(v -> irVentanBuscarContacto(v));
        btnListarContactos.setOnClickListener(v -> irVentanListarContactos(v));
    }

    public void irVentanGuardar(View view){
        Intent intent = new Intent(this, Guardar.class);
        startActivity(intent);
    }

    public void irVentanBuscarContacto(View view){
        Intent intent = new Intent(this, BuscarContacto.class);
        startActivity(intent);
    }

    public void irVentanListarContactos(View view){
        Intent intent = new Intent(this, ListarContactos.class);
        startActivity(intent);
    }
}