package com.example.examenandroid;

public class Contacto {
    private String nombre;
    private String numero;
    private String email;

    public Contacto(String nombre, String numero, String email) {
        this.nombre = nombre;
        this.numero = numero;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
