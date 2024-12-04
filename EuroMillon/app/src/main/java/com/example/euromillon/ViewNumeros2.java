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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class ViewNumeros2 extends AppCompatActivity {

    private GridLayout gridLayout;

    private Button btnGuardarNum;
    private Button btnVolverNum;
    private Button btnResetNum;

    private TextView tvTituloNum2;
    private TextView tvNumElegidos;
    private TextView tvEstrellasElegidas;
    private TextView tvNumGanadores;
    private TextView tvEstrellasGanadoras;

    private ArrayList<String> numElegidos = new ArrayList<>();

    private int contador = 0;

    private StringBuilder sbTV = new StringBuilder();

    private int[] numGanadores = new int[5];
    private int[] estrGanadores = new int[2];


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_numeros2);

        tvTituloNum2 = findViewById(R.id.tvTituloNum2);
        tvNumElegidos = findViewById(R.id.tvNumElegidos);
        tvEstrellasElegidas = findViewById(R.id.tvEstrellasElegidas);
        tvNumGanadores = findViewById(R.id.tvNumGanadores);
        tvEstrellasGanadoras = findViewById(R.id.tvEstrellasGanadoras);
        gridLayout = findViewById(R.id.gridLayout);
        btnGuardarNum = findViewById(R.id.btnGuardarNum);
        btnVolverNum = findViewById(R.id.btnVolverNum);
        btnResetNum = findViewById(R.id.btnResetNum);

        btnVolverNum.setOnClickListener(v -> volverViewJuego());

        asignarBotonesNum();

        //Asignamos los numeros y estrellas ganadoras
        numJuego(numGanadores);
        numJuego(estrGanadores);
        tvNumGanadores.setText(imprimirNum(numGanadores));
        tvEstrellasGanadoras.setText(imprimirNum(estrGanadores));
    }

    public void volverViewJuego() {
        Intent i = new Intent(this, ViewJuego.class);
        startActivity(i);
    }

    public void asignarBotonesNum() {
        // Recorremos todos los hijos del GridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            // Obtenemos cada hijo del GridLayout, que puede ser un Button
            View child = gridLayout.getChildAt(i);

            // Comprobamos si el hijo es un Button (ya que pueden haber otros tipos de vistas)
            if (child instanceof Button) {
                Button btn = (Button) child;

                // Asignamos el evento de click a cada botón
                btn.setOnClickListener(v -> handleButtonClick(btn));
            }
        }
    }

    private void handleButtonClick(Button btn) {
        // Desactivar el botón
        btn.setEnabled(false);

        // Cambiar la apariencia
        btn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        btn.setTextColor(ContextCompat.getColor(this, android.R.color.white));

        // Agregar número al array y actualizar TextView
        contador++;
        numElegidos.add(btn.getText().toString());
        actualizarNumTV(btn.getText().toString());

        // Deshabilitar botones si ya se eligieron 5 números
        if (contador >= 5) {
            deshabilitarBotones();
        }

        Toast.makeText(this, "Botón " + btn.getText() + " pulsado", Toast.LENGTH_SHORT).show();
    }

    public void guardarNum() {
        if (contador == 5) {
            Intent intent = new Intent(this, ViewJuego.class);
            intent.putStringArrayListExtra("NUMEROS_ELEGIDOS", numElegidos);
            startActivity(intent);
        } else {
            Toast.makeText(this, "DEBE SELECCIONAR 5 NÚMEROS", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarNumTV(String num) {
        if (contador < 5) {
            sbTV.append(num).append(", ");
            tvNumElegidos.setText(sbTV);
        } else {
            sbTV.append(num);
            tvNumElegidos.setText(sbTV);
        }
    }

    public void activarBotones() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button) {
                child.setEnabled(true);
                child.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // Color original
                ((Button) child).setTextColor(getResources().getColor(android.R.color.black)); // Texto negro original
            }
        }
        contador = 0;

        sbTV.delete(0, sbTV.length());
        numElegidos.clear();
        tvNumElegidos.setText(sbTV);
    }

    public void numJuego(int[] numGanadores) {
        Random random = new Random();
        int longitud = numGanadores.length;
        int cantNum = 0;
        int count = 0;
        if (longitud == 5) {
            cantNum = 50;
        } else if (longitud == 2) {
            cantNum = 12;
        }

        while (count < longitud) {
            int num = random.nextInt(cantNum) + 1; // Genera un número entre 1 y 50
            boolean exists = false;

            // Verificar si ya existe en el array
            for (int i = 0; i < count; i++) {
                if (numGanadores[i] == num) {
                    exists = true;
                    break;
                }
            }

            // Si no existe, añadir al array
            if (!exists) {
                numGanadores[count] = num;
                count++;
            }
        }
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

    public String imprimirNum(int[] num) {
        StringBuilder sb = new StringBuilder("[");
        for (int numeros : num) {
            sb.append(numeros).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}