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
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gimnasio implements Serializable {
    
    private static Gimnasio instancia= null;
    private ArrayList<Socio> socios= new ArrayList();
    private ArrayList<Administrador> administradores= new ArrayList();
    private ArrayList<Actividad> actividades= new ArrayList();
    private ArrayList<Sala> salas= new ArrayList();
    private ArrayList<Reserva> reservas= new ArrayList();
    private Usuario usuarioLogeado;
    
    public Gimnasio() {
        // Se crea el administrador por defecto
        Administrador adminPorDefecto = new Administrador("admin@javafit.com", "admin");
        this.administradores.add(adminPorDefecto);
    }
    
    public static Gimnasio getInstancia() {
        if (instancia == null) {
            instancia = cargarDatos();
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
    
    public boolean crearActividad(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen){
        
        Actividad nuevaActividad = new Actividad(titulo, tipo, sala, horarios, monitor, imagen);
        
        if(!actividades.contains(nuevaActividad)){
            
            actividades.add(nuevaActividad);
            this.guardarDatos();
            return true;
        }
        else{
            return false;
        }

    }
    
    
    public boolean crearActividad(String titulo, String tipo, Sala sala, ArrayList horarios, String monitor, ImageIcon imagen, double precio, String descripcion){
        
        Actividad nuevaActividadEspecial = new ActividadEspecial(titulo, tipo, sala, horarios, monitor, imagen, precio, descripcion);
        
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
    
    public boolean modificarActividad(Actividad act, String nuevoTitulo, String nuevoTipo, Sala nuevaSala, String nuevoMonitor, ImageIcon nuevaImagen, double nuevoPrecio, String nuevaDesc) {
    
        if (act == null || !actividades.contains(act)) {
            return false;
        }
                act.setTitulo(nuevoTitulo);
                act.setTipo(nuevoTipo);
                act.setSala(nuevaSala);
                act.setMonitor(nuevoMonitor);
                act.setImagen(nuevaImagen);

                if (act instanceof ActividadEspecial) {
                    
                    ActividadEspecial esp = (ActividadEspecial) act;
                    esp.setPrecio(nuevoPrecio);
                    esp.setDescripcion(nuevaDesc);
                }

                guardarDatos(); 
                return true;
        }
    
    public ArrayList<Actividad> buscarActividades(String tipo, String monitor, String dia) {
        
        return actividades.stream()
                .filter(a -> (tipo == null || a.getTipo().equalsIgnoreCase(tipo)))
                .filter(a -> (monitor == null || a.getMonitor().equalsIgnoreCase(monitor)))
                .filter(a -> dia == null || a.getHorarios().stream()
                        .filter(h -> h.getDia().equals(dia))
                        .count()>0)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Socio> buscarSocios(String correo, Boolean vip) {
        return socios.stream()
                .filter(s -> correo == null || s.getCorreo().toLowerCase().equals(correo.toLowerCase()))
                .filter(s -> vip == null || s.isSocioVIP() == vip)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
    public ArrayList<Reserva> buscarReservas(Socio socio, LocalDate fecha) {
        Comparator<Reserva> fechaComp = new Comparator<Reserva>() {
            @Override
            public int compare(Reserva r1, Reserva r2) {
                return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        };

        return reservas.stream()
                .filter(r -> socio == null || r.getCliente().equals(socio))
                .filter(r -> fecha == null || !r.getFechaReserva().isBefore(fecha))
                .sorted(fechaComp)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
    public boolean registrarSocio(Socio s){
        if(buscarSocios(s.getCorreo(),null).isEmpty()){
            socios.add(s);
            guardarDatos();
            return true;  
        }
        else{
            return false;
        }
    }
    
    public Usuario login(String correo, String clave){
        Usuario user= socios.stream()
                .filter(s -> s.getCorreo().equalsIgnoreCase(correo) && s.getClave().equals(clave))
                .findFirst()
                .orElse(null);
       if (user==null){
            user = administradores.stream()
                .filter(a -> a.getCorreo().equalsIgnoreCase(correo) && a.getClave().equals(clave))
                .findFirst()
                .orElse(null);      
       }
       
       this.setUsuarioLogeado(user);
       
       return user;
                
    }
    
    public boolean reservar(Actividad act, Socio cliente, Horario turno){
        
        LocalDate fechaReserva= LocalDate.now();
        
        long reservasActuales= reservas.stream()
                .filter(r -> r.getActividad().equals(act) && r.getHorario().equals(turno))
                .count();
        
        if (reservasActuales < act.getSala().getAforo()) {
            Reserva nuevaReserva = new Reserva(act, cliente, turno, fechaReserva);
            nuevaReserva.setImporte(calcularImporte(cliente, act));
            reservas.add(nuevaReserva);
            guardarDatos();
                        
            return true;
        }
        
        return false;
        
    }
    
        public double calcularImporte(Socio s, Actividad act){
        
        double importe=0;
        if (act instanceof ActividadEspecial){
            importe= ((ActividadEspecial) act).getPrecio();
        }
        
        if (s.isSocioVIP()==true){
            importe= importe * 0.9;
        }
        
        return importe;
    }
    
    public boolean cancelarReserva(Reserva r){
        for(int i=0; i<reservas.size();i++){
            if (reservas.get(i).equals(r)){
                reservas.remove(i);
                guardarDatos();
                return true;
            }
        }
        return false;
    }
    
    public boolean modificarSocio(Socio s, String nuevoCorreo, String nuevaClave, String nuevoNombre, String nuevoTelefono, String nuevaDireccion, String nuevaTarjetaCredito, boolean nuevoSocioVIP){
        if (s == null || !socios.contains(s)) {
            return false;
        }
        
        s.setCorreo(nuevoCorreo);
        s.setClave(nuevaClave);
        s.setNombre(nuevoNombre);
        s.setTelefono(nuevoTelefono);
        s.setDireccion(nuevaDireccion);
        s.setTarjetaCredito(nuevaTarjetaCredito);
        s.setSocioVIP(nuevoSocioVIP);
        
        guardarDatos(); 
        return true;
        
    }
    
        public void generaFactura(Reserva r) throws IOException {
        DateTimeFormatter formatoCorto = DateTimeFormatter.ofPattern("dd/MM/yyyy");        
        String fn = r.getFechaReserva().format(formatoCorto);        
        String rutaFicheroFactura = "./Facturas/Factura(" + fn.replace('/', '_') + ").txt";
        double importe = r.getImporte();
        try {
            File dirFacturas = new File("./Facturas");

            if (!dirFacturas.exists()) {
                dirFacturas.mkdir();
            }

            FileWriter fw = new FileWriter(rutaFicheroFactura);
            try (PrintWriter salida = new PrintWriter(new BufferedWriter(fw))) {
                salida.println("-------------------------------- Factura de la Reserva --------------------------------");
                salida.println("\n");
                salida.println("Actividad: " + r.getActividad());
                salida.println("\n");
                salida.println("Cliente: " + r.getCliente());
                salida.println("\n");
                salida.println("Turno: " + r.getHorario().getTurno());
                salida.println("\n");
                salida.println("Fecha de realización de la reserva: " + r.getFechaReserva());
                salida.println("---------------------------------------------------------------------------------");
                salida.println("IMPORTE: " + importe);
                salida.println("\n");
                salida.println("-------------------------------------------------------------------------------");
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }//fin generaFactura
    
    
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
