package com.example.practicasexamen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String [] optionSelec = {"sumar","restar","dividir","multiplicar"};
    private Spinner spnOptOperaciones;
    private EditText etNum1;
    private EditText etNum2;
    private TextView tvTitle;
    private TextView tvResultado;
    private Button btnOperar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spnOptOperaciones = findViewById(R.id.spnOptOperaciones);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvTitle = findViewById(R.id.tvTitle);
        tvResultado = findViewById(R.id.tvResultado);
        spnOptOperaciones = findViewById(R.id.spnOptOperaciones);
        btnOperar = findViewById(R.id.btnOperar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,optionSelec);
        spnOptOperaciones.setAdapter(adapter);

        btnOperar.setOnClickListener(v -> operarNum(v));

    }

    public void operarNum(View view){
        //Obtenemos el valor de los EditText
        String valor1 = etNum1.getText().toString();
        String valor2 = etNum2.getText().toString();

        //Convertimos a integers los string

        double nro1 = Integer.parseInt(valor1);
        double nro2 = Integer.parseInt(valor2);
        double nroResultado = 0;

        String operacion = spnOptOperaciones.getSelectedItem().toString();

        switch (operacion){
            case "sumar" :
                nroResultado = nro1+nro2;
                break;

            case "restar":
                nroResultado = nro1-nro2;
                break;

            case "dividir":
                if(nro2 != 0) {
                    nroResultado = (nro1/nro2);
                    break;
                }else {
                    tvResultado.setText("No se puede dividir entre 0");
                    break;
                }
            case "multiplicar":
                nroResultado = nro1*nro2;
                break;
        }

        String resultadoString = String.valueOf(nroResultado);

        tvResultado.setText(resultadoString);
    }
}