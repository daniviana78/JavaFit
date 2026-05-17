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

public class ActividadEspecial extends Actividad implements Serializable {
    
    private double precio;
    private String descripcion;

    public ActividadEspecial(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen, double precio, String descripcion) {
        super(titulo, tipo, sala, horarios, monitor, imagen);
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ActividadEspecial{" + "precio=" + precio + ", descripcion=" + descripcion + '}';
    }
    

    
}
