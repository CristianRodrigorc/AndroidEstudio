package com.example.euromillon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewEstrellas extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvEstrellasElegidas;
    private GridLayout gridLayoutEstrellas;
    private Button btnVolverEstrellas;
    private Button btnGuardarEstrellas;
    private Button btnResetEstrellas;
    private int contador = 0;
    private ArrayList<String> estrellasElegidas = new ArrayList<>();
    private StringBuilder sbTVEstrellas = new StringBuilder();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_estrellas);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvEstrellasElegidas = findViewById(R.id.tvEstrellasElegidas);
        gridLayoutEstrellas = findViewById(R.id.gridLayoutEstrellas); // Corregido el ID
        btnVolverEstrellas = findViewById(R.id.btnVolverEstrellas);
        btnGuardarEstrellas = findViewById(R.id.btnGuardarEstrellas);
        btnResetEstrellas = findViewById(R.id.btnResetEstrellas);

        pintarEstrellas();
        btnVolverEstrellas.setOnClickListener(v -> volverViewJuego());

    }

    public void pintarEstrellas() {
        for (int i = 0; i < 12; i++) {
            Button btn = new Button(this);
            btn.setText(i < 9 ? "0" + (i + 1) : String.valueOf(i + 1));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 150;
            params.height = 150;
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(8, 8, 8, 8);
            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> {
                btn.setEnabled(false);
                btn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                btn.setTextColor(getResources().getColor(android.R.color.white));
                Toast.makeText(this, "Botón " + btn.getText() + " pulsado", Toast.LENGTH_SHORT).show();
                contador++;
                estrellasElegidas.add(btn.getText().toString());
                actualizarEstrellasTV(btn.getText().toString());

                if (contador >= 2) {
                    deshabilitarEstrellas();
                }
            });

            gridLayoutEstrellas.addView(btn);
        }
    }

    public void actualizarEstrellasTV(String num) {
        if (contador < 2) {
            sbTVEstrellas.append(num).append(", ");
        } else {
            sbTVEstrellas.append(num);
        }
        tvEstrellasElegidas.setText(sbTVEstrellas);
    }

    public void deshabilitarEstrellas() {
        for (int i = 0; i < gridLayoutEstrellas.getChildCount(); i++) {
            View child = gridLayoutEstrellas.getChildAt(i);
            if (child instanceof Button) {
                Button btn = (Button) child;
                btn.setEnabled(false);
                btn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                btn.setTextColor(getResources().getColor(android.R.color.white));
            }
        }
    }

    public void volverViewJuego() {
        Intent i = new Intent(this, ViewJuego.class);
        startActivity(i);
    }
}
