package com.example.proyecto003;

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
    private TextView result;
    private RadioButton sumBtn, restBtn, multBtn, dvBtn, prm1Num;
    private RadioGroup grupoBtns;
    private Button actResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = findViewById(R.id.valor1);
        valor2 = findViewById(R.id.valor2);
        result = findViewById(R.id.result);
        grupoBtns = findViewById(R.id.grupoBtns);
        sumBtn = findViewById(R.id.sumBtn);
        restBtn = findViewById(R.id.restBtn);
        multBtn = findViewById(R.id.multBtn);
        dvBtn = findViewById(R.id.dvBtn);
        prm1Num = findViewById(R.id.prm1Num);
    }

    // Este método se ejecutará cuando se presione el botón
    public void operar(View view) {
        String valorRescatado1 = valor1.getText().toString();
        String valorRescatado2 = valor2.getText().toString();
        int nro1 = Integer.parseInt(valorRescatado1);
        int nro2 = Integer.parseInt(valorRescatado2);

        if (sumBtn.isChecked() == true) {
            int suma = nro1 + nro2;
            String resu = String.valueOf(suma);
            result.setText(resu);
        }

        if (restBtn.isChecked() == true) {
            int resta = nro1 - nro2;
            String resu = String.valueOf(resta);
            result.setText(resu);
        }

        if (multBtn.isChecked() == true) {
            int multi = nro1 * nro2;
            String resu = String.valueOf(multi);
            result.setText(resu);
        }

        if (dvBtn.isChecked() == true) {
            if (nro2 == 0) {
                System.out.println("No se puede dividir entre 0");
            }
            int division = nro1 / nro2;
            String resu = String.valueOf(division);
            result.setText(resu);
        }

        if (prm1Num.isChecked() == true) {
            if (nro1 % 2 == 0 && nro1 != 2) {
                result.setText("No es primo...");
            } else {
                result.setText("Es primo...");
            }
        }
    }
}