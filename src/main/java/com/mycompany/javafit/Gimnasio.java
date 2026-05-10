/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafit;

/**
 *
 * @author Dani
 */
import java.util.ArrayList;

public class Gimnasio {
    
    private ArrayList<Socio> socios;
    private ArrayList<Administrador> administradores;
    private ArrayList<Actividad> actividades;
    private ArrayList<Sala> salas;
    private ArrayList<Reserva> reservas;
    private Usuario usuarioLogeado;

    public Gimnasio(ArrayList<Socio> socios, ArrayList<Administrador> administradores, ArrayList<Actividad> actividades, ArrayList<Sala> salas, ArrayList<Reserva> reservas, Usuario usuarioLogeado) {
        this.socios = new ArrayList();
        this.administradores = new ArrayList();
        this.administradores.add(new Administrador("admin@javafit.com", "admin"));        
        this.actividades = new ArrayList();
        this.salas = new ArrayList();
        this.reservas = new ArrayList();
        this.usuarioLogeado = usuarioLogeado;
    }

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public void setSocios(ArrayList<Socio> socios) {
        this.socios = socios;
    }
    
    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }
    
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
    
    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    @Override
    public String toString() {
        return "Gimnasio{" + "socios=" + socios + ", administradores=" + administradores + ", actividades=" + actividades + ", salas=" + salas + ", reservas=" + reservas + ", usuarioLogeado=" + usuarioLogeado + '}';
    }
    

    
}
