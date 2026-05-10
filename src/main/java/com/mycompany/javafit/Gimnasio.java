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
import java.io.*;
import javax.swing.ImageIcon;

public class Gimnasio {
    
    private static Gimnasio instancia= null;
    private ArrayList<Socio> socios;
    private ArrayList<Administrador> administradores;
    private ArrayList<Actividad> actividades;
    private ArrayList<Sala> salas;
    private ArrayList<Reserva> reservas;
    private Usuario usuarioLogeado;
    
    public Gimnasio() {
    }
    
    public static Gimnasio getInstancia() {
        if (instancia == null) {
            instancia = new Gimnasio();
        }
            return instancia;
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
    
    public boolean crearActividad(String titulo, String tipo, Sala sala, String monitor, ImageIcon imagen){
        
        Actividad nuevaActividad = new Actividad(titulo, tipo, sala, monitor, imagen);
        
        if(!actividades.contains(nuevaActividad)){
            
            actividades.add(nuevaActividad);
            this.guardarDatos();
            return true;
        }
        else{
            return false;
        }

    }
    
    public boolean crearActividad(String titulo, String tipo, Sala sala, String monitor, ImageIcon imagen, double precio, String descripcion){
        
        Actividad nuevaActividadEspecial = new ActividadEspecial(titulo, tipo, sala, monitor, imagen, precio, descripcion);
        
        if(!actividades.contains(nuevaActividadEspecial)){
            
            actividades.add(nuevaActividadEspecial);
            this.guardarDatos();
            return true;
        }
        else{
            return false;
        }

    }
    
    public boolean modificarActividad(String titulo, String tipo, Sala sala, String monitor, ImageIcon imagen, double precio, String descripcion){
        return true;
    }
    
    public void guardarDatos() {
        
        try (FileOutputStream fos = new FileOutputStream("datos.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(this);
            
            System.out.println("Datos guardados con éxito.");

        } catch (IOException e) {
            
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public static Gimnasio cargarDatos() {
        
        File archivo = new File("datos.dat");

        if (!archivo.exists()) {

            System.out.println("No se encontró archivo de datos. Creando nuevo sistema...");

            return new Gimnasio(); 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

            return (Gimnasio) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Error al cargar datos, creando sistema nuevo: " + e.getMessage());

            return new Gimnasio();
        }
    }
    

    @Override
    public String toString() {
        return "Gimnasio{" + "socios=" + socios + ", administradores=" + administradores + ", actividades=" + actividades + ", salas=" + salas + ", reservas=" + reservas + ", usuarioLogeado=" + usuarioLogeado + '}';
    }
    
    
    
}
