package com.example.actividad12viewweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private WebView webUrl;
    private Button btnFinal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        webUrl = findViewById(R.id.webUrl);
        btnFinal = findViewById(R.id.btnFinal);


        asignarUrl(webUrl);
        btnFinal.setOnClickListener(v -> finalizarApp());
    }

    public void asignarUrl(WebView webView){
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        webView.loadUrl(url);
    }

    public void finalizarApp(){
        //Finaliza todas las actividades de la pila y devuelve al punto de partida
        finish();
        //Detiene el proceso de la aplicacion
        System.exit(0);
    }

}