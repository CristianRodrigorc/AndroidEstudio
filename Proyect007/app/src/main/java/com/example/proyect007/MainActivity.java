package com.example.proyect007;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView  resultado2;
    private ImageButton iBtnSuma, iBtnMenos, iBtnMulti, iBtnDiv;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        resultado2 = findViewById(R.id.resultado2);
        iBtnSuma = findViewById(R.id.iBtnSuma);
        iBtnMenos = findViewById(R.id.iBtnMenos);
        iBtnMulti = findViewById(R.id.iBtnMulti);
        iBtnDiv = findViewById(R.id.iBtnDiv);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);


        iBtnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("+");
            }
        });

        iBtnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("-");
            }
        });

        iBtnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("*");
            }
        });

        iBtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("/");
            }
        });
    }

    private void realizarOperacion(String operacion) {
        int numero1 = Integer.parseInt(et1.getText().toString());
        int numero2 = Integer.parseInt(et2.getText().toString());
        int resultadoOperacion = 0;

        switch (operacion) {
            case "+":
                resultadoOperacion = numero1 + numero2;
                break;
            case "-":
                resultadoOperacion = numero1 - numero2;
                break;
            case "*":
                resultadoOperacion = numero1 * numero2;
                break;
            case "/":
                if (numero2 != 0) {
                    resultadoOperacion = numero1 / numero2;
                } else {
                    resultado2.setText("Error: División por cero");
                    return;
                }
                break;
        }

        resultado2.setText(String.valueOf(resultadoOperacion));
    }
}
