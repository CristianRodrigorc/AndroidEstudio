package com.example.examenandroid;

import static com.example.examenandroid.ListarContactos.contactos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuscarContacto extends AppCompatActivity {

    private EditText eTBuscador;
    private TextView tVNombre;
    private TextView tVNumero;
    private TextView tVEmail;
    private Button btnSalir;
    private Button btnBuscarContacto;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_contacto);

        eTBuscador = findViewById(R.id.eTBuscador);
        tVNombre = findViewById(R.id.tVNombre);
        tVNumero = findViewById(R.id.tVNumero);
        tVEmail = findViewById(R.id.tVEmail);
        btnSalir = findViewById(R.id.btnSalir);
        btnBuscarContacto = findViewById(R.id.btnBuscarContacto);

    }

    public void buscarContactoArray(){
        for (int i = 0; i < contactos.size(); i++) {
            String nombreArray = contactos.get(i).getNombre();
            String nombreLocal = eTBuscador.getText().toString();

            if(nombreLocal== nombreArray){
                tVNombre.setText(contactos.get(i).getNombre());
                tVNumero.setText(contactos.get(i).getNumero());
                tVEmail.setText(contactos.get(i).getEmail());
            }

        }
    }
}