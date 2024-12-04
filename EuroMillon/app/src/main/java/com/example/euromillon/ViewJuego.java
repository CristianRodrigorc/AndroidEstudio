package com.example.euromillon;

import android.annotation.SuppressLint;
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
import java.util.Random;

public class ViewJuego extends AppCompatActivity {

    private Button btnVolver;
    private Button btnNum;
    private Button btnEstrellas;
    private TextView tvEstrellas;
    private TextView tvEstrellasGanadoras;
    private TextView tvNum;
    private TextView tvNumGanadores;
    private ArrayList<String> numElegidos = new ArrayList();
    private int [] numGanadores = new int [5];
    private int [] estrGanadores = new int [2];
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
        tvEstrellasGanadoras = findViewById(R.id.tvEstrellasGanadoras);
        tvNum = findViewById(R.id.tvNum);
        tvNumGanadores = findViewById(R.id.tvNumGanadores);


        btnVolver.setOnClickListener(v -> volver(v));
        btnNum.setOnClickListener(v -> elegirNumeros(v));
        btnEstrellas.setOnClickListener(v -> elegirEstrellas(v));

        Intent intent = getIntent();
        numElegidos = intent.getStringArrayListExtra("NUMEROS_ELEGIDOS");
        if (numElegidos != null && !numElegidos.isEmpty()) {
            tvNum.setText(numElegidos.toString());
        } else {
            tvNum.setText("No se han seleccionado números.");
        }

        numJuego(numGanadores);
        numJuego(estrGanadores);
        tvEstrellasGanadoras.setText(imprimirNum(estrGanadores));
        tvNumGanadores.setText(imprimirNum(numGanadores));


    }

    public void elegirNumeros(View view){
        Intent i = new Intent(this, ViewNumeros2.class);
        startActivity(i);
    }

    public void elegirEstrellas(View view){
        Intent i = new Intent(this, ViewEstrellas.class);
        startActivity(i);
    }

    public void volver(View view){
        //finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void numJuego(int[] numGanadores) {
        Random random = new Random();
        int longitud = numGanadores.length;
        int cantNum = 0;
        int count = 0;
        if(longitud == 5){
            cantNum = 50;
        }else if (longitud == 2){
            cantNum = 12;
        }

        while (count < longitud) {
            int num = random.nextInt(cantNum) + 1; // Genera un número entre 1 y 50
            boolean exists = false;

            // Verificar si ya existe en el array
            for (int i = 0; i < count; i++) {
                if (numGanadores[i] == num) {
                    exists = true;
                    break;
                }
            }

            // Si no existe, añadir al array
            if (!exists) {
                numGanadores[count] = num;
                count++;
            }
        }
    }

    public String imprimirNum (int [] num){
        StringBuilder sb = new StringBuilder("[");
        for(int numeros : num){
            sb.append(numeros).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
                return sb.toString();
    }
}