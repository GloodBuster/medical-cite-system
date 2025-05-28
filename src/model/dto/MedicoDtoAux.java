package model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author josev
 */
public class MedicoDtoAux implements Serializable {
    private int idMedico;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private int telefono;
    private String direccion;
    private String email;
    private Date fechaDeIngreso;
    private String especialidad;
    
    /**
     * Constructor con todos los parámetros
     * @param idMedico
     * @param nombres
     * @param apellidos
     * @param fechaNacimiento
     * @param telefono
     * @param direccion
     * @param email
     * @param fechaDeIngreso
     * @param especialidad
     */
    public MedicoDtoAux(int idMedico, String nombres, String apellidos, Date fechaNacimiento, 
                       int telefono, String direccion, String email, Date fechaDeIngreso, 
                       String especialidad) {
        this.idMedico = idMedico;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.fechaDeIngreso = fechaDeIngreso;
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
} 