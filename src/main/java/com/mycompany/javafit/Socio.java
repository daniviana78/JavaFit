/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafit;

/**
 *
 * @author Dani
 */
public class Socio extends Usuario {
    
    private String nombre;
    private String telefono;
    private String direccion;
    private String tarjetaCredito;
    private boolean socioVIP;

    public Socio(String correo, String clave, String nombre, String telefono, String direccion, String tarjetaCredito, boolean socioVIP) {
        super(correo, clave);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tarjetaCredito = tarjetaCredito;
        this.socioVIP = socioVIP;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }
    
    public boolean isSocioVIP() {
        return socioVIP;
    }

    public void setSocioVIP(boolean socioVIP) {
        this.socioVIP = socioVIP;
    }

    @Override
    public String toString() {
        return "Socio{" + "nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", tarjetaCredito=" + tarjetaCredito + ", socioVIP=" + socioVIP + '}';
    }

    
}
