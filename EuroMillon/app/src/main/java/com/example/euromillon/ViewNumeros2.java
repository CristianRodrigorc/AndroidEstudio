package com.example.euromillon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class ViewNumeros2 extends AppCompatActivity {

    private GridLayout gridLayout;
    private GridLayout gridLayout2;

    private Button btnJugar;
    private Button btnVolverNum;
    private Button btnReset;

    private TextView tvTituloNum2;
    private TextView tvNumElegidos;
    private TextView tvEstrellasElegidas;
    private TextView tvNumGanadores;
    private TextView tvEstrellasGanadoras;

    private ArrayList<Integer> numElegidos = new ArrayList<>();
    private ArrayList<Integer> estrElegidas = new ArrayList<>();

    private int contadorNum = 0;
    private int contadorEstrellas = 0;

    private StringBuilder sbTV = new StringBuilder();
    private StringBuilder sbEstrTV = new StringBuilder();

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
        gridLayout2 = findViewById(R.id.gridLayout2);
        btnJugar = findViewById(R.id.btnJugar);
        btnVolverNum = findViewById(R.id.btnVolverNum);
        btnReset = findViewById(R.id.btnReset);

        btnVolverNum.setOnClickListener(v -> volverViewJuego());
        btnReset.setOnClickListener(v -> activarBotones());
        btnJugar.setOnClickListener(v -> compararResultados());


        //Asignamos los numeros y estrellas ganadoras
        numJuego(numGanadores);
        numJuego(estrGanadores);
        tvNumGanadores.setText(imprimirNum(numGanadores));
        tvEstrellasGanadoras.setText(imprimirNum(estrGanadores));
    }

    public void volverViewJuego() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void botonClick(View view) {
        if (contadorNum >= 5) {
            Toast.makeText(this, "Ya seleccionaste 5 botones", Toast.LENGTH_SHORT).show();
            return; // Evita procesar más clics
        }

        if (view instanceof ImageButton) {
            ImageButton imgBtn = (ImageButton) view;

            // Desactiva el botón
            imgBtn.setEnabled(false);

            // Cambiar apariencia
            imgBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            // Obtén el nombre del recurso
            String resourceName = getResources().getResourceEntryName(view.getId());

            // Extrae solo los últimos dos caracteres
            String lastTwoChars = resourceName.substring(Math.max(resourceName.length() - 2, 0));


            // Incrementa el contador
            contadorNum++;

            // Añade el nombre al array y actualiza el TextView
            // Convierte el número del botón (String) a Integer y añade al array
            numElegidos.add(Integer.parseInt(lastTwoChars));
            actualizarNumTV(Integer.parseInt(lastTwoChars));


            // Desactiva los botones si es el quinto clic
            if (contadorNum == 5) {
                deshabilitarBotones(gridLayout);
            }
        }
    }

    public void botonClickEstrellas(View view) {
        if (contadorEstrellas >= 2) {
            Toast.makeText(this, "Ya seleccionaste 2 botones", Toast.LENGTH_SHORT).show();
            return; // Evita procesar más clics
        }

        if (view instanceof ImageButton) {
            ImageButton imgBtn = (ImageButton) view;

            // Desactiva el botón
            imgBtn.setEnabled(false);

            // Cambiar apariencia
            imgBtn.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            // Obtén el nombre del recurso
            String resourceName = getResources().getResourceEntryName(view.getId());

            // Extrae solo los últimos dos caracteres
            String lastTwoChars = resourceName.substring(Math.max(resourceName.length() - 2, 0));


            // Incrementa el contador
            contadorEstrellas++;

            // Añade el nombre al array y actualiza el TextView
            // Convierte el número del botón (String) a Integer y añade al array
            estrElegidas.add(Integer.parseInt(lastTwoChars));
            actualizarEstrTV(Integer.parseInt(lastTwoChars));

            // Desactiva los botones si es el quinto clic
            if (contadorEstrellas == 2) {
                deshabilitarBotones(gridLayout2);
            }
        }
    }


    public void compararResultados() {
        // Comparar los números ganadores con los elegidos
        int coincidenciasNumeros = 0;
        for (Integer numElegido : numElegidos) {
            for (int numGanador : numGanadores) {
                if (numElegido == numGanador) {
                    coincidenciasNumeros++;
                }
            }
        }

        // Comparar las estrellas ganadoras con las elegidas
        int coincidenciasEstrellas = 0;
        for (Integer estrellaElegida : estrElegidas) {
            for (int estrellaGanadora : estrGanadores) {
                if (estrellaElegida == estrellaGanadora) {
                    coincidenciasEstrellas++;
                }
            }
        }

        // Mostrar las coincidencias en los TextViews
        String resultados = "Coincidencias en los números: " + coincidenciasNumeros + "\n" +
                "Coincidencias en las estrellas: " + coincidenciasEstrellas;

        // Calcular el porcentaje de ganancia basado en las coincidencias
        int porcentaje = calcularPorcentaje(coincidenciasNumeros, coincidenciasEstrellas);

        // Pozo de dinero
        double pozoDinero = 5000000;

        // Calcular el dinero ganado
        double dineroGanado = pozoDinero * (porcentaje / 100.0);

        // Mostrar el resultado con el dinero ganado
        resultados += "\nDinero ganado: " + dineroGanado + " unidades.";

        Toast.makeText(this, resultados, Toast.LENGTH_LONG).show();
    }

    private int calcularPorcentaje(int aciertosNumeros, int aciertosEstrellas) {
        // Lógica del porcentaje basado en los aciertos
        if (aciertosNumeros == 5 && aciertosEstrellas == 2) return 100;
        if (aciertosNumeros == 5 && aciertosEstrellas == 1) return 95;
        if (aciertosNumeros == 5 && aciertosEstrellas == 0) return 80;
        if (aciertosNumeros == 4 && aciertosEstrellas == 2) return 75;
        if (aciertosNumeros == 4 && aciertosEstrellas == 1) return 60;
        if (aciertosNumeros == 3 && aciertosEstrellas == 2) return 50;
        if (aciertosNumeros == 4 && aciertosEstrellas == 0) return 40;
        if (aciertosNumeros == 2 && aciertosEstrellas == 2) return 30;
        if (aciertosNumeros == 3 && aciertosEstrellas == 1) return 20;
        if (aciertosNumeros == 3 && aciertosEstrellas == 0) return 10;
        if (aciertosNumeros == 1 && aciertosEstrellas == 2) return 5;
        if (aciertosNumeros == 2 && aciertosEstrellas == 1) return 3;
        if (aciertosNumeros == 2 && aciertosEstrellas == 0) return 0;
        if (aciertosNumeros == 1 && aciertosEstrellas == 0) return 0;
        if (aciertosNumeros == 0 && aciertosEstrellas == 0) return 0;

        return 0; // En caso de que no haya coincidencias, no se gana nada
    }




    public void actualizarNumTV(Integer num) {
        if (contadorNum < 5) {
            sbTV.append(num).append(", ");
        } else {
            sbTV.append(num);
        }
        tvNumElegidos.setText(sbTV);
    }

    public void actualizarEstrTV(Integer num) {
        if (contadorEstrellas < 2) {
            sbEstrTV.append(num).append(", ");
        } else {
            sbEstrTV.append(num);
        }
        tvEstrellasElegidas.setText(sbEstrTV.toString());
    }


    public void activarBotones() {
        // Habilitar botones en gridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof Button || child instanceof ImageButton) {
                child.setEnabled(true);
                child.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
        }

        contadorNum = 0;

        sbTV.delete(0, sbTV.length());
        numElegidos.clear();
        tvNumElegidos.setText(sbTV);

        // Habilitar botones en gridLayout2
        for (int i = 0; i < gridLayout2.getChildCount(); i++) {
            View child = gridLayout2.getChildAt(i);
            if (child instanceof Button || child instanceof ImageButton) {
                child.setEnabled(true);
                child.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
        }

        contadorEstrellas = 0;

        sbEstrTV.delete(0,sbEstrTV.length());
        estrElegidas.clear();
        tvEstrellasElegidas.setText(sbEstrTV);

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

    // Método para deshabilitar todos los botones del GridLayout
    public void deshabilitarBotones(GridLayout targetGridLayout) {
        for (int i = 0; i < targetGridLayout.getChildCount(); i++) {
            View child = targetGridLayout.getChildAt(i);
            if (child instanceof Button || child instanceof ImageButton) {
                child.setEnabled(false);
                child.setBackgroundColor(getResources().getColor(android.R.color.darker_gray)); // Cambia apariencia
            }
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