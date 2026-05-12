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
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gimnasio implements Serializable {
    
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
    
    public boolean borrarActividad(String titulo){
                
        for(int i=0;i<actividades.size();i++){
            Actividad act= actividades.get(i);
            String pruebaTitulo= act.getTitulo();
            
            if (titulo.equals(pruebaTitulo)){
                actividades.remove(act);
                guardarDatos();
                return true;
            }

        }
        return false;
    }
    
    public boolean modificarActividad(String tituloOriginal, String nuevoTipo, Sala nuevaSala, String nuevoMonitor, ImageIcon nuevaImagen, double nuevoPrecio, String nuevaDesc) {
    
        for (int i = 0; i < actividades.size(); i++) {
            Actividad act = actividades.get(i);

            if (act.getTitulo().equals(tituloOriginal)) {
                
                act.setTipo(nuevoTipo);
                act.setSala(nuevaSala);
                act.setMonitor(nuevoMonitor);
                act.setImagen(nuevaImagen);

                if (act instanceof ActividadEspecial) {
                    
                    ActividadEspecial esp = (ActividadEspecial) act;
                    esp.setPrecio(nuevoPrecio);
                    esp.setDescripcion(nuevaDesc);
                }

                guardarDatos(); // Guardamos el cambio en el archivo
                return true;
            }
        }
        return false;
}
    
    public ArrayList<Actividad> consultarActividades(){
        return new ArrayList<>(actividades);
        
    }
    
    public ArrayList<Actividad> consultarActividadPorTitulo(String tipo) {
        ArrayList<Actividad> actividadesConsultadas= new ArrayList();
        
            for (int i=0; i<actividades.size();i++){
                if(actividades.get(i).getTipo().equals(tipo))
                    actividadesConsultadas.add(actividades.get(i));
            }
            return actividadesConsultadas;
            
    }
    
    public ArrayList<Socio> consultarSocios(){
        return new ArrayList<>(socios);
        
    }
    
    public ArrayList<Socio> consultarSocios(boolean socioVIP){
        ArrayList<Socio> sociosConsultados= new ArrayList();
        
            for (int i=0; i<socios.size();i++){
                if(socios.get(i).isSocioVIP()==socioVIP)
                    sociosConsultados.add(socios.get(i));
            }
            return sociosConsultados;
            
    }
    
    public ArrayList<Reserva> consultarReservas(){
        ArrayList<Reserva> reservasConsultadas= new ArrayList();
        
            reservas.stream()
                    .forEach(r -> {
                
                    reservasConsultadas.add(r);
                });
                        
            Comparator<Reserva> fechaComp= new Comparator<Reserva>() {
                @Override
                public int compare(Reserva r1, Reserva r2) {
                    return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        };
            Collections.sort(reservasConsultadas, fechaComp);
            return reservasConsultadas;
    }    
    public ArrayList<Reserva> consultarReservas(LocalDate fecha){
        ArrayList<Reserva> reservasConsultadas= new ArrayList();
        
            reservas.stream()
                    .forEach(r -> {
                LocalDate fechaReserva= r.getFechaReserva();
                
                if(fecha==null || !fechaReserva.isBefore(fecha)){
                    reservasConsultadas.add(r);
                }
                        
            });
            
            Comparator<Reserva> fechaComp= new Comparator<Reserva>() {
                @Override
                public int compare(Reserva r1, Reserva r2) {
                    return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        };
            Collections.sort(reservasConsultadas, fechaComp);
            return reservasConsultadas;
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
