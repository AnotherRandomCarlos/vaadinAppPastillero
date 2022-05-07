package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Usuarios extends AbstractEntity {

    private String nombreUsu;
    private String apellidoUsu;
    private String userUsu;
    private String passUsu;
    private boolean important;

    public String getNombreUsu() {
        return nombreUsu;
    }
    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }
    public String getApellidoUsu() {
        return apellidoUsu;
    }
    public void setApellidoUsu(String apellidoUsu) {
        this.apellidoUsu = apellidoUsu;
    }
    public String getUserUsu() {
        return userUsu;
    }
    public void setUserUsu(String userUsu) {
        this.userUsu = userUsu;
    }
    public String getPassUsu() {
        return passUsu;
    }
    public void setPassUsu(String passUsu) {
        this.passUsu = passUsu;
    }
    public boolean isImportant() {
        return important;
    }
    public void setImportant(boolean important) {
        this.important = important;
    }

}
