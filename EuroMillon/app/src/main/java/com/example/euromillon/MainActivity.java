package com.example.euromillon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgEuroMillon;
    private TextView tv1;
    private Button btnJugar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        btnJugar = findViewById(R.id.btnJugar);
        imgEuroMillon= findViewById(R.id.imgEuroMillon);

        btnJugar.setOnClickListener(v -> viewJuego(v));
    }

    public void viewJuego(View view) {

        Intent i = new Intent(this, ViewNumeros2.class);
        startActivity(i);
    }
}