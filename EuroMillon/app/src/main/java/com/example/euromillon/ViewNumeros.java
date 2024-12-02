package com.example.euromillon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewNumeros extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnVolverNum;
    private Button btnResetNum;
    private Button btnGuardarNum;
    private ArrayList<String> numElegidos = new ArrayList<>();
    private TextView tvTitulo;
    private TextView tvNumElegidos;
    private int contador = 0;
    private StringBuilder sbTV = new StringBuilder();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_numeros);

        btnVolverNum = findViewById(R.id.btnVolverNum);
        btnResetNum = findViewById(R.id.btnResetNum);
        btnGuardarNum = findViewById(R.id.btnGuardarNum);
        gridLayout = findViewById(R.id.gridLayout);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvNumElegidos = findViewById(R.id.tvNumElegidos);


        btnVolverNum.setOnClickListener(v -> volverViewJuego());
        btnResetNum.setOnClickListener(v -> activarBotones());

        pintarBotones();

    }

    public void pintarBotones() {
        for (int i = 0; i < 50; i++) {
            Button btn = new Button(this);
            if(i<9) {
                btn.setText("0"+(i + 1));
            }else{
                btn.setText(String.valueOf(i + 1));
            }


            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 150;
            params.height = 150;
            //Usamos pesos para que los botones ocupen todo el espacio disponible de forma homogenia
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(8, 8, 8, 8);
            btn.setLayoutParams(params);


            //evento para desactivar el boton al precionar
            btn.setOnClickListener(v -> {
                //Desactivar el boton
                btn.setEnabled(false);

                // Cambiar apariencia para mostrar que está seleccionado
                btn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray)); // Cambia el color
                btn.setTextColor(getResources().getColor(android.R.color.white)); // Cambia el texto a blanco
                Toast.makeText(this, "Botón " + btn.getText() + " pulsado", Toast.LENGTH_SHORT).show();
                contador++;
                //añadimos el num al array
                numElegidos.add(btn.getText().toString());

                //añadimos el num al textView con el string builder
                actualizarNumTV(btn.getText().toString());


                // Si el contador llega a 5, deshabilitar todos los botones
                if (contador >= 5) {
                    deshabilitarBotones();
                }
            });

            gridLayout.addView(btn);
        }
    }

    public void volverViewJuego() {
        Intent i = new Intent(this, ViewJuego.class);
        startActivity(i);
    }

    // Método para deshabilitar todos los botones
    public void deshabilitarBotones() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);
            btn.setEnabled(false); // Deshabilitar el botón
            btn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray)); // Cambiar el color de fondo
            btn.setTextColor(getResources().getColor(android.R.color.white)); // Cambiar el color del texto
        }
    }

    public void activarBotones(){
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                child.setEnabled(true);
                child.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // Color original
                ((Button) child).setTextColor(getResources().getColor(android.R.color.black)); // Texto negro original
            }
        }
        contador = 0;

        sbTV.delete(0,sbTV.length());
        numElegidos.clear();
        tvNumElegidos.setText(sbTV);


    }

    public void actualizarNumTV(String num){
        if(contador < 5) {
            sbTV.append(num).append(", ");
            tvNumElegidos.setText(sbTV);
        }else{
            sbTV.append(num);
            tvNumElegidos.setText(sbTV);
        }
    }

    public void guardarNum(){

        volverViewJuego();
    }

}
