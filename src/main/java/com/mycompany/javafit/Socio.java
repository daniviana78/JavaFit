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

/**
 * Clase que representa a un Socio en el sistema JavaFit.
 * Almacena los datos personales del cliente, su estado de suscripción (VIP)
 * y el histórico de las reservas que ha realizado en el gimnasio.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class Socio extends Usuario implements Serializable {
    
    /** Nombre completo del socio. */
    private String nombre;
    /** Número de teléfono de contacto del socio. */
    private String telefono;
    /** Dirección postal del domicilio del socio. */
    private String direccion;
    /** Número de la tarjeta de crédito vinculada para los pagos. */
    private String tarjetaCredito;
    /** Indicador de si el socio posee una suscripción de categoría VIP. */
    private boolean socioVIP;
    /** Listado dinámico (ArrayList) que almacena las reservas efectuadas por el socio. */
    private ArrayList<Reserva> reservas;
    

    /**
     * Constructor para inicializar todos los atributos personales y de cuenta de un socio.
     * @param correo El correo electrónico de acceso del socio.
     * @param clave La contraseña de acceso del socio.
     * @param nombre El nombre completo del socio.
     * @param telefono El teléfono de contacto del socio.
     * @param direccion La dirección del socio.
     * @param tarjetaCredito La tarjeta de crédito del socio.
     * @param socioVIP El estado de suscripción VIP (true o false).
     */
    public Socio(String correo, String clave, String nombre, String telefono, String direccion, String tarjetaCredito, boolean socioVIP) {
        super(correo, clave);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tarjetaCredito = tarjetaCredito;
        this.socioVIP = socioVIP;
        this.reservas = new ArrayList();

    }
    

    /**
     * Obtiene el nombre completo del socio.
     * @return El nombre del socio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del socio.
     * @param nombre El nuevo nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    /**
     * Obtiene el número de teléfono del socio.
     * @return El teléfono del socio.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del socio.
     * @param telefono El nuevo teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    /**
     * Obtiene la dirección del socio.
     * @return La dirección del socio.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del socio.
     * @param direccion La nueva dirección a asignar.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Obtiene el número de tarjeta de crédito del socio.
     * @return La tarjeta de crédito del socio.
     */
    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    /**
     * Establece el número de tarjeta de crédito del socio.
     * @param tarjetaCredito La nueva tarjeta de crédito a asignar.
     */
    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
    
    /**
     * Indica si el socio es de categoría VIP.
     * @return true si el socio es VIP; false en caso contrario.
     */
    public boolean isSocioVIP() {
        return socioVIP;
    }

    /**
     * Establece el estado de suscripción VIP del socio.
     * @param socioVIP El nuevo estado VIP a asignar.
     */
    public void setSocioVIP(boolean socioVIP) {
        this.socioVIP = socioVIP;
    }

    /**
     * Obtiene la lista de reservas asociadas al socio.
     * @return Un ArrayList con las reservas del socio.
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la lista de reservas asociadas al socio.
     * @param reservas El nuevo ArrayList de reservas.
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    

    /**
     * Devuelve una cadena de texto con la información personal y el tipo de suscripción del socio.
     * * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "Socio{ correo= " + super.getCorreo() + ", clave= " + super.getClave() + "nombre= " + nombre + ", telefono= " + telefono + ", direccion= " + direccion + ", tarjetaCredito= " + tarjetaCredito + ", socioVIP= " + socioVIP + '}';
    }

    
}