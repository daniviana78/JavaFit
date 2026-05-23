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
 * Clase que representa a un usuario con rol de Administrador en el sistema JavaFit.
 * Hereda de la clase Usuario y gestiona las credenciales de acceso para las 
 * tareas de administración.
 * * @author Dani
 * @version 1.0
 */
public class Administrador extends Usuario implements Serializable{

    /**
     * Constructor para inicializar un administrador con sus credenciales de acceso.
     * @param correo El correo electrónico identificativo del administrador.
     * @param clave La contraseña de acceso al sistema.
     */
    public Administrador(String correo, String clave) {
        super(correo, clave);
    }

    /**
     * Devuelve una cadena de texto representativa de la clase Administrador.
     * @return Cadena formateada con la identificación de la clase.
     */
    @Override
    public String toString() {
        return "Administrador{ correo= " + super.getCorreo() + ", clave= " + super.getClave() + '}';
    }
    
    
}