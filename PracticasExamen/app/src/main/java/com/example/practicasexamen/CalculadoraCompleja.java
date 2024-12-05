package com.example.practicasexamen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CalculadoraCompleja extends AppCompatActivity {
    private TableLayout tLComand;
    private TableLayout tLView;

    private TextView tvNumCalc1;
    private TextView tvSimbolCal;
    private TextView tvNumCalc2;
    private TextView tvResulCal;

    // Variables para almacenar los números y operador
    private String num1 = "";
    private String num2 = "";
    private String operador = "";


    //Otra forma de hacerlo
    /*
    private Button btnSuma;
    private Button btnResta;
    private Button btnIgual;
    private Button btnMulti;
    private Button btnDiv;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
     */
    private ArrayList <Button> botonesNumList;
    private ArrayList <Button> botonesSimbolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora_compleja);

        tLComand = findViewById(R.id.tLComand);
        tLView = findViewById(R.id.tLView);
        tvNumCalc1 = findViewById(R.id.tvNumCalc1);
        tvSimbolCal = findViewById(R.id.tvSimbolCal);
        tvNumCalc2 = findViewById(R.id.tvNumCalc2);
        tvResulCal = findViewById(R.id.tvResulCal);
        /*
        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnIgual = findViewById(R.id.btnIgual);
        btnMulti = findViewById(R.id.btnMulti);
        btnDiv = findViewById(R.id.btnDiv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
         */

        botonesNumList = new ArrayList<>();
        botonesSimbolList = new ArrayList<>();

        llenarArray(botonesNumList,botonesSimbolList,tLComand);


        // Asignamos los listeners
        asignarListeners();

    }


    //Método que recorre la tablaLayout y agrega al arraylist los botones
    public void llenarArray(ArrayList <Button> numeros, ArrayList <Button> simbolos, TableLayout table){

        //Recorremos las filas de la tabla
        for (int i = 0; i < table.getChildCount(); i++) {
            //Creamos un atributo tipo View llamado row
            View row = table.getChildAt(i);

            //Comprobamos si el hijo es un TableRow
            if(row instanceof TableRow){
                //Creamos un atributo tablerow y tomará el valor que de row casteado a tableRow
                TableRow tableRow = (TableRow) row;
                for (int j = 0; j < tableRow.getChildCount(); j++) {
                    //Creamos otro atributo tipo View para recorrer el TableRow
                    View celda = tableRow.getChildAt(j);

                    //Comprobamos si la celda es un boton
                    if(celda instanceof Button){
                        Button boton = (Button) celda;

                        // Obtenemos el texto del botón para diferenciar
                        String buttonText = boton.getText().toString();

                        // Clasificamos el botón
                        if (buttonText.matches("\\d")) { // Si el texto es un dígito (0-9)
                            numeros.add(boton);
                        } else { // Cualquier otro texto lo clasificamos como símbolo
                            simbolos.add(boton);
                        }
                    }
                }
            }
        }
    }

    // Método para asignar los listeners
    public void asignarListeners() {
        // Listener para botones numéricos
        for (Button boton : botonesNumList) {
            boton.setOnClickListener(v -> {
                String numero = boton.getText().toString();

                // Determinamos si estamos escribiendo el primer o segundo número
                if (operador.isEmpty()) {
                    num1 += numero;
                    tvNumCalc1.setText(num1); // Mostramos el número en el primer TextView
                } else {
                    num2 += numero;
                    tvNumCalc2.setText(num2); // Mostramos el número en el segundo TextView
                }
            });
        }

        // Listener para botones de operación
        for (Button boton : botonesSimbolList) {
            boton.setOnClickListener(v -> {
                String simbolo = boton.getText().toString();

                if (simbolo.equals("=")) {
                    calcularResultado(); // Llamamos al método de cálculo
                } else {
                    if (!num1.isEmpty() && operador.isEmpty()) {
                        operador = simbolo;
                        tvSimbolCal.setText(operador); // Mostramos el operador
                    }
                }
            });
        }
    }

    // Método para calcular el resultado
    public void calcularResultado() {
        if (!num1.isEmpty() && !num2.isEmpty() && !operador.isEmpty()) {
            double resultado = 0;
            double numero1 = Double.parseDouble(num1);
            double numero2 = Double.parseDouble(num2);

            // Realizamos la operación según el operador
            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        tvResulCal.setText("Error"); // Manejo de división por 0
                        return;
                    }
                    break;
            }

            // Mostramos el resultado
            tvResulCal.setText(String.valueOf(resultado));

            // Reiniciamos los valores para otra operación
            num1 = "";
            num2 = "";
            operador = "";
            tvNumCalc1.setText("");
            tvSimbolCal.setText("");
            tvNumCalc2.setText("");
        }
    }
}