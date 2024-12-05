package com.example.practicasexamen.pasardatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practicasexamen.R;

public class PasarDatos1 extends AppCompatActivity {

    private EditText pasar1EtEntrada;
    private Button pasar1btnPasar;
    private String textoEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pasar_datos1);

        pasar1EtEntrada = findViewById(R.id.pasar1EtEntrada);
        pasar1btnPasar = findViewById(R.id.pasar1btnPasar);

        pasar1btnPasar.setOnClickListener(v -> enviarInfo(v));

    }


    public void enviarInfo(View view){
        Intent intent = new Intent(this,RecibirDatos1.class);
        intent.putExtra("dato",pasar1EtEntrada.getText().toString());
        //iniciamos el activity
        startActivity(intent);
    }
}