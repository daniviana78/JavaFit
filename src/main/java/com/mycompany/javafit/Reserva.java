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
import java.time.LocalDate;

/**
 * Clase que representa una Reserva de actividad en el sistema JavaFit.
 * Vincula una actividad deportiva concreta con el socio que la solicita,
 * el horario elegido, la fecha de la reserva y el importe correspondiente.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class Reserva implements Serializable {
    
    /** Actividad deportiva objeto de la reserva. */
    private Actividad actividad;
    /** Socio que realiza la reserva. */
    private Socio cliente;
    /** Horario y turno asignado para la reserva. */
    private Horario horario;
    /** Fecha exacta en la que se registra la reserva. */
    private LocalDate fechaReserva;
    /** Importe o coste económico asociado a la reserva. */
    private double importe;

    /**
     * Constructor para inicializar una reserva con sus componentes esenciales.
     * @param actividad La actividad que se va a reservar.
     * @param cliente El socio que realiza la reserva.
     * @param turno El horario elegido para asistir.
     * @param fechaReserva La fecha en la que se efectúa la reserva.
     */
    public Reserva(Actividad actividad, Socio cliente, Horario turno, LocalDate fechaReserva) {
        this.actividad = actividad;
        this.cliente = cliente;
        this.horario = turno;
        this.fechaReserva = fechaReserva;
    }
    
    /**
     * Obtiene la actividad vinculada a la reserva.
     * @return La actividad reservada.
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Establece la actividad vinculada a la reserva.
     * @param actividad La nueva actividad a asignar.
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    
    /**
     * Obtiene el socio que realizó la reserva.
     * @return El cliente asociado.
     */
    public Socio getCliente() {
        return cliente;
    }
    
    /**
     * Establece el socio que realiza la reserva.
     * @param cliente El nuevo socio a asignar.
     */
    public void setCliente(Socio cliente) {
        this.cliente = cliente;
    }
   
    /**
     * Obtiene el horario asignado a la reserva.
     * @return El objeto Horario correspondiente.
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Establece el horario asignado a la reserva.
     * @param horario El nuevo horario a asignar.
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    
    /**
     * Obtiene la fecha en la que se registró la reserva.
     * @return La fecha de la reserva.
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha en la que se registra la reserva.
     * @param fechaReserva La nueva fecha a asignar.
     */
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    /**
     * Obtiene el coste económico de la reserva.
     * @return El importe actual.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece el coste económico de la reserva.
     * @param importe El nuevo importe a asignar.
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Devuelve una cadena de texto con la información detallada de la reserva.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "Reserva{" + "actividad= " + actividad + ", cliente= " + cliente + ", turno= " + horario + ", fechaReserva= " + fechaReserva + ", importe= " + importe + '}';
    }
    

}
