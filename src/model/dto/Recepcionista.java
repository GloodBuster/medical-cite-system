/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author caes2
 */
public class Recepcionista implements Serializable{
    private int idRecepcionista;
    private String nombres;
    private Date fechaDeIngreso;

    public Recepcionista(int idRecepcionista, String nombres, Date fechaDeIngreso) {
        this.idRecepcionista = idRecepcionista;
        this.nombres = nombres;
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }
    
    
}
