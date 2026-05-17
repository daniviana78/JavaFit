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

public class Horario implements Serializable{

    private String dia;
    private String turno;

    public Horario(String dia, String turno) {
        this.dia = dia;
        this.turno= turno;
    }
    

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    

    @Override
    public String toString() {
        return "Horario{" + "dia=" + dia + ", turno=" + turno + '}';
    }
    
}
