package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Medicina extends AbstractEntity {



    private String nombreMed;
    private String descripcionMed;
    private String days;
    private int cantidadToma;
    private int cantidadCaja;
    private int horas;
    private boolean important;

    public String getNombreMed() {
        return nombreMed;
    }

    public void setNombreMed(String nombreMed) {
        this.nombreMed = nombreMed;
    }

    public String getDescripcionMed() {
        return descripcionMed;
    }

    public void setDescripcionMed(String descripcionMed) {
        this.descripcionMed = descripcionMed;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getCantidadToma() {
        return cantidadToma;
    }

    public void setCantidadToma(int cantidadToma) {
        this.cantidadToma = cantidadToma;
    }

    public int getCantidadCaja() {
        return cantidadCaja;
    }

    public void setCantidadCaja(int cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
