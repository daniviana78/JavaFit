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
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Clase que representa una actividad especial en el gimnasio JavaFit.
 * Hereda de la clase Actividad e incorpora características adicionales
 * como un precio específico y una descripción detallada.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class ActividadEspecial extends Actividad implements Serializable {
    
    /** Precio asignado a la actividad especial. */
    private double precio;
    /** Descripción detallada sobre el contenido o requisitos de la actividad. */
    private String descripcion;

    /**
     * Constructor para inicializar una actividad especial con sus atributos propios y heredados.
     * @param titulo El título identificativo de la actividad.
     * @param tipo El tipo o categoría de la actividad.
     * @param sala La sala donde se realiza.
     * @param horarios Lista con los horarios de la actividad.
     * @param monitor El monitor encargado.
     * @param imagen La imagen descriptiva de la actividad.
     * @param precio El coste económico de la actividad.
     * @param descripcion Detalle o explicación de la actividad.
     */
    public ActividadEspecial(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen, double precio, String descripcion) {
        super(titulo, tipo, sala, horarios, monitor, imagen);
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene el precio de la actividad especial.
     * @return El precio actual.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la actividad especial.
     * @param precio El nuevo precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Obtiene la descripción de la actividad especial.
     * @return La descripción actual.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la actividad especial.
     * @param descripcion La nueva descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve una cadena de texto con la información de los atributos propios de la actividad especial.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return super.toString() + ", precio= " + precio + ", descripcion= " + descripcion + '}';
    }
    

    
}