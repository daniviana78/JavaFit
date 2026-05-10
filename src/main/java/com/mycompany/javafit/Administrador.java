/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafit;

/**
 *
 * @author Dani
 */
public class Administrador extends Usuario{

    public Administrador(String correo, String clave) {
        super(correo, clave);
    }

    @Override
    public String toString() {
        return "Administrador{" + '}';
    }
    
    
}
