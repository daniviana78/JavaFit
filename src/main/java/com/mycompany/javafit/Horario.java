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
 * Clase que representa el Horario asignado a las actividades en el sistema JavaFit.
 * Define la planificación temporal especificando el día de la semana y el turno 
 * o franja horaria correspondiente.
 * * @author Daniel viana y Adrián Fernández
 * @version 1.0
 */
public class Horario implements Serializable{

    /** Día de la semana programado para el horario (ej. "Lunes", "Martes"). */
    private String dia;
    /** Turno o franja horaria específica de la sesión (ej. "9:00-10:00", "Tarde"). */
    private String turno;

    /**
     * Constructor para inicializar un horario con su día y turno específicos.
     * @param dia El día de la semana asignado.
     * @param turno El turno o franja horaria correspondiente.
     */
    public Horario(String dia, String turno) {
        this.dia = dia;
        this.turno= turno;
    }
    

    /**
     * Obtiene el día de la semana del horario.
     * @return El día actual.
     */
    public String getDia() {
        return dia;
    }

    /**
     * Establece el día de la semana del horario.
     * @param dia El nuevo día a asignar.
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Obtiene el turno o franja horaria.
     * @return El turno actual.
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Establece el turno o franja horaria.
     * @param turno El nuevo turno a asignar.
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    

    /**
     * Devuelve una cadena de texto con la información detallada del horario.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "Horario{" + "dia=" + dia + ", turno=" + turno + '}';
    }
    
}