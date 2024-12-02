package com.example.euromillon;

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

import java.util.ArrayList;
import java.util.List;

public class ViewJuego extends AppCompatActivity {

    private Button btnVolver;
    private Button btnNum;
    private Button btnEstrellas;
    private TextView tvEstrellas;
    private TextView tvNum;
    private ArrayList<String> numElegidos = new ArrayList();
    private ArrayList<String> estrellasElegidas = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_juego);


        btnVolver = findViewById(R.id.btnVolver);
        btnNum = findViewById(R.id.btnNum);
        btnEstrellas = findViewById(R.id.btnEstrellas);
        tvEstrellas = findViewById(R.id.tvEstrellas);
        tvNum = findViewById(R.id.tvNum);


        btnVolver.setOnClickListener(v -> volver(v));
        btnNum.setOnClickListener(v -> elegirNumeros(v));

    }

    public void elegirNumeros(View view){
        Intent i = new Intent(this, ViewNumeros.class);
        startActivity(i);
    }

    public void volver(View view){
        //finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void setNumElegidos(ArrayList<String> numElegidos) {
        this.numElegidos = numElegidos;
    }

    public void setEstrellasElegidas(ArrayList<String> estrellasElegidas) {
        this.estrellasElegidas = estrellasElegidas;
    }
}