package com.sigep.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de Lista Enlazada para la gestión del Historial de Horas.
 * Permite agregar elementos y deshacer la última inserción.
 */
public class ListaEnlazada<T> {

    private Nodo<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la lista enlazada.
     */
    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /**
     * Deshace la última carga eliminando el último nodo de la lista enlazada.
     * @return El elemento eliminado o null si la lista está vacía.
     */
    public T deshacer() {
        if (cabeza == null) {
            return null;
        }

        if (cabeza.getSiguiente() == null) {
            T dato = cabeza.getDato();
            cabeza = null;
            tamanio--;
            return dato;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(null);
        tamanio--;
        return dato;
    }

    /**
     * Retorna los elementos de la lista enlazada como una lista estándar de Java.
     */
    public List<T> aLista() {
        List<T> lista = new ArrayList<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            lista.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return lista;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}
