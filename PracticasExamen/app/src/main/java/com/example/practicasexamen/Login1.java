package com.example.practicasexamen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Login1 extends AppCompatActivity {


    //Para cambiar el ícono entramos a la opcion File>new>Image Asset



    private Usuario [] usuarios;
    private EditText login1EtId;
    private EditText login1EtPassword;
    private TextView login1TvTitle;
    private Button login1BtnComprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login1);

        login1EtId = findViewById(R.id.login1EtId);
        login1EtPassword = findViewById(R.id.login1EtPassword);
        login1TvTitle = findViewById(R.id.login1TvTitle);
        login1BtnComprobar = findViewById(R.id.login1BtnComprobar);


        Usuario usuario1 = new Usuario("usuario1","12345");
        Usuario usuario2 = new Usuario("usuario2","12345");
        Usuario usuario3 = new Usuario("usuario3","12345");
        Usuario usuario4 = new Usuario("usuario4","12345");

        usuarios = new Usuario[4];
        usuarios[0] = usuario1;
        usuarios[1] = usuario2;
        usuarios[2] = usuario3;
        usuarios[3] = usuario4;


        login1BtnComprobar.setOnClickListener(v -> comprobarUsuarios(v));
    }

    public void comprobarUsuarios(View view){
        String idUser = login1EtId.getText().toString();
        String contrasenaUser = login1EtPassword.getText().toString();

        for (Usuario user: usuarios) {
            if(idUser.equals(user.getId()) && contrasenaUser.equals(user.getContrasena())){
                Toast.makeText(this,"Usuario Correcto",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Error en los datos",Toast.LENGTH_LONG).show();
            }
        }
    }
}