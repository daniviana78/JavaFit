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

/**
 * Clase que representa una actividad deportiva del gimnasio JavaFit.
 * Almacena los datos básicos de la actividad, la sala donde se imparte,
 * su planificación de horarios y el monitor encargado.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */


public class Actividad implements Serializable {
    
    /** Título de la actividad. */
    private String titulo;
    /** Tipo de la actividad. */
    private String tipo;
    /** Sala en la que se imparte la actividad. */
    private Sala sala;
    /** Listado que almacena los horarios asignados a la actividad. */
    private ArrayList<Horario> horarios= new ArrayList<>();
    /** Nombre del monitor que imparte la actividad. */    
    private String monitor;
    /** Imagen representativa de la actividad. */
    private ImageIcon imagen;
    /** Lista que almacena las valoraciones que han sido dadas a la actividad. */
    private ArrayList<Valoracion> valoraciones = new ArrayList<>();
    /** Nota media de la actividad. */
    private double media;
    
    /**
     * Constructor para inicializar todos los atributos de la actividad.
     * @param titulo El título identificativo de la actividad.
     * @param tipo El tipo de la actividad.
     * @param sala La sala donde se realiza.
     * @param horarios Lista con los horarios de la actividad.
     * @param monitor El monitor encargado.
     * @param imagen La imagen descriptiva de la actividad.
     */
    public Actividad(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.sala = sala;
        this.monitor = monitor;
        this.imagen = imagen;
        this.horarios = horarios;
        this.valoraciones = new ArrayList<>();
        this.media = 0.0;
        
    }
    
    /**
     * Obtiene el título de la actividad.
     * @return El título actual.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la actividad.
     * @param titulo El nuevo título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Obtiene el tipo de la actividad.
     * @return El tipo de actividad.
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Establece el tipo o categoría de la actividad.
     * @param tipo El nuevo tipo a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtiene la sala donde se realiza la actividad.
     * @return La sala asociada.
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Establece la sala donde se imparte la actividad.
     * @param sala La nueva sala a asignar.
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    /**
     * Obtiene la imagen representativa de la actividad.
     * @return El objeto ImageIcon correspondiente.
     */
    public ImageIcon getImagen() {
        return imagen;
    }
    
    /**
     * Asigna o actualiza la imagen representativa de la actividad.
     *
     * @param imagen El objeto {@link ImageIcon} con la nueva imagen de la actividad.
     */
    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
        
    /**
     * Obtiene el nombre del monitor que imparte la actividad.
     * @return El nombre del monitor.
     */
    public String getMonitor() {
        return monitor;
    }
    
    /**
     * Establece el monitor para la actividad.
     * @param monitor El nombre del nuevo monitor.
     */
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    /**
     * Obtiene la lista de horarios programados.
     * @return Un ArrayList con los horarios.
     */
    public ArrayList<Horario> getHorarios() {
        return horarios;
    }
    
    /**
     * Establece la lista de horarios programados.
     * @param horarios El nuevo ArrayList de horarios.
     */
    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }
    
    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
    
    public void registrarValoracion(Valoracion v) {
        if (v != null && v.getCalificacion() >= 1 && v.getCalificacion() <= 5) {
            this.valoraciones.add(v);
        }
    }
    
    public void calcularNotaMedia() {
        double media = 0.0;

        if (!valoraciones.isEmpty()) {
            int suma = 0;

            for (int i = 0; i < valoraciones.size(); i++) {
                suma += valoraciones.get(i).getCalificacion();
            }

            media = (double) suma / valoraciones.size();
        }

        this.setMedia(media);
    }
    
    /**
     * Compara si esta actividad es igual a otro objeto basándose en el título.
     * @param obj El objeto a comparar.
     * @return true si los títulos coinciden; false en caso contrario.
     */
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
    
    /**
     * Devuelve una cadena de texto con la información detallada de la actividad.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "titulo= " + titulo + ", tipo= " + tipo + ", sala= " + sala + ", horarios= " + horarios + ", monitor= " + monitor + ", imagen= " + imagen + '}';
    }
 
}
