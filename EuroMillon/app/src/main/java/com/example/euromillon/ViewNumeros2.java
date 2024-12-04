package com.example.euromillon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewNumeros2 extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnGuardarNum;
    private Button btnVolverNum;
    private Button btnResetNum;
    private TextView tvTituloNum2;


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





    }
}