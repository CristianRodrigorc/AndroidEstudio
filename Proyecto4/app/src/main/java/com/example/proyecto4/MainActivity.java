package com.example.proyecto4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText valor1, valor2;
    private TextView resultado;
    private RadioButton sumCheck, restCheck, multCheck, dvCheck;
    private Button actResult;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = findViewById(R.id.valor1);
        valor2 = findViewById(R.id.valor2);
        resultado = findViewById(R.id.resultado);
        sumCheck = findViewById(R.id.sumCheck);
        restCheck = findViewById(R.id.restCheck);
        multCheck = findViewById(R.id.multCheck);
        dvCheck = findViewById(R.id.dvCheck);
        actResult = findViewById(R.id.actResult);
    }

    // Este método se ejecutará cuando se presione el botón
    public void operar(View view) {
        String valorRescatado1 = valor1.getText().toString();
        String valorRescatado2 = valor2.getText().toString();
        int nro1 = Integer.parseInt(valorRescatado1);
        int nro2 = Integer.parseInt(valorRescatado2);

        if (sumCheck.isChecked()) {
            int suma = nro1 + nro2;
            String resu = String.valueOf(suma);
            resultado.setText(resu);
        }

        if (restCheck.isChecked()) {
            int resta = nro1 - nro2;
            String resu = String.valueOf(resta);
            resultado.setText(resu);
        }

        if (multCheck.isChecked()) {
            int multi = nro1 * nro2;
            String resu = String.valueOf(multi);
            resultado.setText(resu);
        }

        if (dvCheck.isChecked()) {
            if (nro2 == 0) {
                System.out.println("No se puede dividir entre 0");
            }
            int division = nro1 / nro2;
            String resu = String.valueOf(division);
            resultado.setText(resu);
        }
    }
}