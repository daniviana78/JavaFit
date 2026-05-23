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
 * Clase base que representa a un usuario general dentro del sistema JavaFit.
 * Almacena las credenciales básicas necesarias para la autenticación en la plataforma.
 * * @author Daniel Viana y Adrián Fernández
 * @version 1.0
 */
public class Usuario implements Serializable {
    
    /** Correo electrónico que actúa como identificador del usuario. */
    private String correo;
    
    /** Contraseña de acceso asociada a la cuenta del usuario. */
    private String clave;

    /**
     * Constructor para inicializar los datos de acceso de un usuario.
     * @param correo El correo electrónico del usuario.
     * @param clave La contraseña del usuario.
     */
    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    /**
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico actual.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param correo El nuevo correo electrónico a asignar.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña actual.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la contraseña del usuario.
     * @param clave La nueva contraseña a asignar.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Devuelve una cadena de texto con la información de las credenciales del usuario.
     * @return Cadena formateada con el estado de los atributos.
     */
    @Override
    public String toString() {
        return "Usuario{" + "correo= " + correo + ", clave= " + clave + '}';
    }

    
    
}
