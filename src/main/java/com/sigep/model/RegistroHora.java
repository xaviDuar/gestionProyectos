package com.sigep.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class RegistroHora {

    private final StringProperty fecha;
    private final IntegerProperty horas;
    private final StringProperty tarea;

    public RegistroHora(String fecha, int horas, String tarea) {
        this.fecha = new SimpleStringProperty(fecha);
        this.horas = new SimpleIntegerProperty(horas);
        this.tarea = new SimpleStringProperty(tarea);
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public int getHoras() {
        return horas.get();
    }

    public void setHoras(int horas) {
        this.horas.set(horas);
    }

    public IntegerProperty horasProperty() {
        return horas;
    }

    public String getTarea() {
        return tarea.get();
    }

    public void setTarea(String tarea) {
        this.tarea.set(tarea);
    }

    public StringProperty tareaProperty() {
        return tarea;
    }

    @Override
    public String toString() {
        return getFecha() + " - " + getHoras() + "hs - " + getTarea();
    }
}
