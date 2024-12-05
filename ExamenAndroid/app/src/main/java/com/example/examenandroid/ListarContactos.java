package com.example.examenandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListarContactos extends AppCompatActivity {

    private ListView lVContactos;
    public static ArrayList<Contacto> contactos;
    private Button btnSalirListaContactos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_contactos);

        lVContactos = findViewById(R.id.lVContactos);
        btnSalirListaContactos = findViewById(R.id.btnSalirListaContactos);


        contactos = new ArrayList<>();
        Contacto prueba = new Contacto("prueba", "132123", "hola@yo.com");
        contactos.add(prueba);

        ArrayList<String> nombresContactos = new ArrayList<>();
        for (int i = 0; i < contactos.size(); i++) {
            nombresContactos.add(contactos.get(i).getNombre());
        }


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContactos);
        lVContactos.setAdapter(adapter);


        btnSalirListaContactos.setOnClickListener(v -> irMenu(v));

    }

    public Contacto obtenerContacto(){
        SharedPreferences nombre = getSharedPreferences("contactoNombre", Context.MODE_PRIVATE);
        String nombreGuardado = nombre.getString("contactoNombre", "");
        SharedPreferences numero = getSharedPreferences("contactoNumero", Context.MODE_PRIVATE);
        String numeroGuardado = numero.getString("contactoNumero", "");
        SharedPreferences email = getSharedPreferences("contactoEmail", Context.MODE_PRIVATE);
        String emailGuardado = email.getString("contactoEmail", "");

        Contacto contacto = new Contacto(nombreGuardado,numeroGuardado,emailGuardado);

        return contacto;
    }
    public void irMenu(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

}