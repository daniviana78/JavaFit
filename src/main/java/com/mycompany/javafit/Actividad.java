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
import java.util.Objects;
import javax.swing.ImageIcon;


public class Actividad implements Serializable {
    
    private String titulo;
    private String tipo;
    private Sala sala;
    private ArrayList<Horario> horarios;
    private String monitor;
    private ImageIcon imagen;

    public Actividad(String titulo, String tipo, Sala sala, String monitor, ImageIcon imagen) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.sala = sala;
        this.monitor = monitor;
        this.imagen = imagen;
        this.horarios = new ArrayList();
    }
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    
    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    
    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividad other = (Actividad) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actividad{" + "titulo=" + titulo + ", tipo=" + tipo + ", sala=" + sala + ", horarios=" + horarios + ", monitor=" + monitor + ", imagen=" + imagen + '}';
    }
 
}
