package com.example.practicasexamen;

public class Usuario {
    private String id;
    private String contrasena;

    public Usuario(String id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public String getContrasena() {
        return contrasena;
    }
}
