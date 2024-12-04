package com.example.euromillon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewNumeros2 extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnGuardarNum;
    private Button btnVolverNum;
    private Button btnResetNum;
    private TextView tvTituloNum2;
    private ArrayList<String> numElegidos = new ArrayList<>();
    private int contador = 0;
    private TextView tvNumElegidos;
    private StringBuilder sbTV = new StringBuilder();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_numeros2);

        tvTituloNum2 = findViewById(R.id.tvTituloNum2);
        gridLayout = findViewById(R.id.gridLayout);
        btnGuardarNum = findViewById(R.id.btnGuardarNum);
        btnVolverNum = findViewById(R.id.btnVolverNum);
        btnResetNum = findViewById(R.id.btnResetNum);

        btnVolverNum.setOnClickListener(v -> volverViewJuego());
        //btnResetNum.setOnClickListener(v -> );
    }

    public void volverViewJuego() {
        Intent i = new Intent(this, ViewJuego.class);
        startActivity(i);
    }

    public void guardarNum(){
        if(contador == 5) {
            Intent intent = new Intent(this, ViewJuego.class);
            intent.putStringArrayListExtra("NUMEROS_ELEGIDOS", numElegidos);
            startActivity(intent);
        }else{
            Toast.makeText(this, "DEBE SELECCIONAR 5 NÚMEROS", Toast.LENGTH_SHORT).show();
        }
    }

    public void activarBotones(){
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                child.setEnabled(true);
                child.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // Color original
                ((Button) child).setTextColor(getResources().getColor(android.R.color.black)); // Texto negro original
            }
        }
        contador = 0;

        sbTV.delete(0,sbTV.length());
        numElegidos.clear();
        tvNumElegidos.setText(sbTV);


    }
}