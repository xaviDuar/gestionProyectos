package com.sigep.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Practica {

    private final StringProperty alumno;
    private final StringProperty organizacion;
    private final StringProperty estado;
    private final StringProperty inicio;

    public Practica(String alumno, String organizacion, String estado, String inicio) {
        this.alumno = new SimpleStringProperty(alumno);
        this.organizacion = new SimpleStringProperty(organizacion);
        this.estado = new SimpleStringProperty(estado);
        this.inicio = new SimpleStringProperty(inicio);
    }

    public String getAlumno() {
        return alumno.get();
    }

    public void setAlumno(String alumno) {
        this.alumno.set(alumno);
    }

    public StringProperty alumnoProperty() {
        return alumno;
    }

    public String getOrganizacion() {
        return organizacion.get();
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion.set(organizacion);
    }

    public StringProperty organizacionProperty() {
        return organizacion;
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public String getInicio() {
        return inicio.get();
    }

    public void setInicio(String inicio) {
        this.inicio.set(inicio);
    }

    public StringProperty inicioProperty() {
        return inicio;
    }

    @Override
    public String toString() {
        return getAlumno() + " - " + getOrganizacion() + " (" + getEstado() + ")";
    }
}
