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

public class Reserva implements Serializable {
    
    private Actividad actividad;
    private Socio cliente;
    private Horario horario;
    private LocalDate fechaReserva;
    private double importe;

    public Reserva(Actividad actividad, Socio cliente, Horario turno, LocalDate fechaReserva) {
        this.actividad = actividad;
        this.cliente = cliente;
        this.horario = turno;
        this.fechaReserva = fechaReserva;
    }
    

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    
    public Socio getCliente() {
        return cliente;
    }

    public void setCliente(Socio cliente) {
        this.cliente = cliente;
    }
   
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Reserva{" + "actividad=" + actividad + ", cliente=" + cliente + ", turno=" + horario + ", fechaReserva=" + fechaReserva + ", importe=" + importe + '}';
    }
    

}
