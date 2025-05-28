package model.dto;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author josev
 */
public class AgendaDtoAux implements Serializable {
    private int idAgenda;
    private int idMedico;
    private Date fecha;
    private Time horaInicio;
    private Time horaFinal;
    
    /**
     * Constructor con todos los parámetros
     * @param idAgenda
     * @param idMedico
     * @param fecha
     * @param horaInicio
     * @param horaFinal
     */
    public AgendaDtoAux(int idAgenda, int idMedico, Date fecha, Time horaInicio, Time horaFinal) {
        this.idAgenda = idAgenda;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }
} 