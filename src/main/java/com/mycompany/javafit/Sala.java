/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafit;

/**
 *
 * @author Dani
 */

import java.io.*;

/**
 * Clase que representa una Sala física dentro de las instalaciones de JavaFit.
 * Define las características del espacio donde se desarrollan las actividades,
 * controlando su nombre identificativo y la capacidad máxima de personas permitidas.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class Sala implements Serializable {
    
    /** Nombre o identificador de la sala (ej. "Sala de Ciclo", "Sala Polivalente"). */
    private String nombre;
    /** Capacidad máxima o aforo límite de usuarios permitidos simultáneamente en la sala. */
    private int aforo;

    /**
     * Constructor para inicializar una sala con su nombre y aforo máximo.
     * @param nombre El nombre identificativo de la sala.
     * @param aforo El número máximo de personas permitido.
     */
    public Sala(String nombre, int aforo) {
        this.nombre = nombre;
        this.aforo = aforo;
    }

    /**
     * Obtiene el nombre de la sala.
     * @return El nombre actual de la sala.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la sala.
     * @param nombre El nuevo nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la capacidad máxima de aforo de la sala.
     * @return El aforo permitido.
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * Establece la capacidad máxima de aforo de la sala.
     * @param aforo El nuevo límite de aforo a asignar.
     */
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    /**
     * Devuelve una cadena de texto con la información de la sala.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "Sala{" + "nombre= " + nombre + ", aforo= " + aforo + '}';
    }
    


        
}
