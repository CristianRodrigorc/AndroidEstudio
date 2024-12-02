package com.example.proyecto009;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etEntrada;
    private TextView tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvPrimEstrella, tvSecEstrella, tvResultado;
    private Button jugarBtn, resetBtn;
    private String cadenaEntrada;
    private String [] arrayEntrada, arrayRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEntrada = findViewById(R.id.etEntrada);
        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        tvNum3 = findViewById(R.id.tvNum3);
        tvNum4 = findViewById(R.id.tvNum4);
        tvNum5 = findViewById(R.id.tvNum5);
        tvPrimEstrella = findViewById(R.id.tvPrimEstrella);
        tvSecEstrella = findViewById(R.id.tvSecEstrella);
        tvResultado = findViewById(R.id.tvResultado);
        jugarBtn = findViewById(R.id.jugarBtn);
        resetBtn = findViewById(R.id.resetBtn);



    }
}