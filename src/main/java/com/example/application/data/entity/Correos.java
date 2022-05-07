package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Correos extends AbstractEntity {

    private String correoUsu;
    private String correoSup;

    private boolean important;

    public String getCorreoUsu() {
        return correoUsu;
    }
    public void setCorreoUsu(String correoUsu) {
        this.correoUsu = correoUsu;
    }
    public String getCorreoSup() {
        return correoSup;
    }
    public void setCorreoSup(String correoSup) {
        this.correoSup = correoSup;
    }

    public boolean isImportant() {
        return important;
    }
    public void setImportant(boolean important) {
        this.important = important;
    }

}
