package com.example.examenandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Guardar extends AppCompatActivity {

    private EditText eTNombre;
    private EditText eTNumero;
    private EditText eTEmail;
    private Button btnGuardar;
    private Button btnVerContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guardar);

        eTNombre = findViewById(R.id.eTNombre);
        eTNumero = findViewById(R.id.eTNumero);
        eTEmail = findViewById(R.id.eTEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVerContacto = findViewById(R.id.btnVerContacto);

        btnGuardar.setOnClickListener(v -> guardarContacto(v));
        btnVerContacto.setOnClickListener(v -> irVentanBuscarContacto(v));

    }

    public void guardarContacto(View view){
        SharedPreferences prefNombre = getSharedPreferences("contactoNombre", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNombre = prefNombre.edit();

        String nuevoContactoNombre = eTNombre.getText().toString();
        editorNombre.putString("contactoNombre",nuevoContactoNombre);
        editorNombre.commit();


        SharedPreferences prefNumero = getSharedPreferences("contactoNumero", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNumero = prefNumero.edit();
        String nuevoContactoNumero = eTNumero.getText().toString();
        editorNumero.putString("contactoNumero",nuevoContactoNumero);

        SharedPreferences prefEmail = getSharedPreferences("contactoEmail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorEmail = prefEmail.edit();
        String nuevoContactoEmail = eTEmail.getText().toString();
        editorEmail.putString("contactoEmail",nuevoContactoEmail );
        ListarContactos.agregarContacto();

    }

    public void irVentanBuscarContacto(View view){
        Intent intent = new Intent(this, BuscarContacto.class);
        startActivity(intent);
    }
}