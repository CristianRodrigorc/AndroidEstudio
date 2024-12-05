package com.example.actividad12viewweb;

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

public class MainActivity extends AppCompatActivity {
    private EditText eTUrl;
    private Button btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        eTUrl = findViewById(R.id.eTUrl);
        btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(v -> goView(v));
    }

    public void goView(View view){
        //Creamos el objeto y cómo parametros pasamos la clase actual y a la que dirigiremos el intent
        Intent intent = new Intent(this, MainActivity2.class);
        //como parámetro pasamos un id para el intent y el string que enviaremos
        intent.putExtra("url",eTUrl.getText().toString());
        //iniciamos el intent
        startActivity(intent);
    }
}