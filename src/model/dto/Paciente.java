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
public class Paciente implements Serializable{
    private int idPaciente;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private int telefono;
    private String direccion;
    private String email;

    
    
    
    public Paciente(int idPaciente, String nombres, String apellidos, Date fechaNacimiento, int telefono, String direccion, String email) {
        this(idPaciente, nombres, apellidos, fechaNacimiento, telefono, direccion, email);
    }

    public Paciente(int idPaciente, String nombres, String apellidos, Date fechaNacimiento, int telefono, String direccion, String email) {
        this.idPaciente = idPaciente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

   

    

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    
}
