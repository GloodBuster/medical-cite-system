package model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author josev
 */
public class RecepcionistaDtoAux implements Serializable {
    private int idRecepcionista;
    private String nombres;
    private Date fechaDeIngreso;
    
    /**
     * Constructor con todos los parámetros
     * @param idRecepcionista
     * @param nombres
     * @param fechaDeIngreso
     */
    public RecepcionistaDtoAux(int idRecepcionista, String nombres, Date fechaDeIngreso) {
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