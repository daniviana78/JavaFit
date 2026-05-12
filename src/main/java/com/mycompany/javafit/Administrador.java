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

public class Administrador extends Usuario implements Serializable{

    public Administrador(String correo, String clave) {
        super(correo, clave);
    }

    @Override
    public String toString() {
        return "Administrador{" + '}';
    }
    
    
}
