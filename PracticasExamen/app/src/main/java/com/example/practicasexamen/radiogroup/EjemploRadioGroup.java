package com.example.practicasexamen.radiogroup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practicasexamen.R;

public class EjemploRadioGroup extends AppCompatActivity {

    private RadioGroup ejRadioRGroupContenedor;
    private RadioButton ejRadioRBtunSum;
    private RadioButton ejRadioRBtunRest;
    private Button ejRadioBtnOperar;
    private EditText ejRadioETNum1;
    private EditText ejRadioETNum2;
    private TextView ejRadioTVResultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_radio_group);

        ejRadioRBtunSum = findViewById(R.id.ejRadioRBtunSum);
        ejRadioRBtunRest = findViewById(R.id.ejRadioRBtunRest);
        ejRadioBtnOperar = findViewById(R.id.ejRadioBtnOperar);
        ejRadioETNum1 = findViewById(R.id.ejRadioETNum1);
        ejRadioETNum2 = findViewById(R.id.ejRadioETNum2);
        ejRadioTVResultado = findViewById(R.id.ejRadioTVResultado);

        ejRadioBtnOperar.setOnClickListener(v -> operarRadioGroup(v));

    }

    public void operarRadioGroup(View view){

        double num1 = Double.parseDouble(ejRadioETNum1.getText().toString());
        double num2 = Double.parseDouble(ejRadioETNum2.getText().toString());
        double resultado;

        if(ejRadioRBtunSum.isChecked()){
            ejRadioTVResultado.setText(String.valueOf(num1+num2));
        }else if (ejRadioRBtunRest.isChecked()){
            ejRadioTVResultado.setText(String.valueOf(num1-num2));
        }
    }
}