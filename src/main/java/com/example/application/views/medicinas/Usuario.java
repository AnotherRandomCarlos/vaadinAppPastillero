package com.example.application.views.medicinas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    @JsonProperty("id_usuario")
    private int id_usuario;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("apellidos")
    private String apellidos;
    @JsonProperty("nombre_usuario")
    private String nombre_usuario;
    @JsonProperty("contrasenia")
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String nombre_usuario, String contrasenia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}

