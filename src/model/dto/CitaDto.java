package model.dto;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author josev
 */
public class CitaDto implements Serializable {
    private int idCita;
    private Date fechaCita;
    private Time horaCita;
    private String estadoCita;
    private String motivoConsulta;
    private Date fechaAsignacion;
    private int idPaciente;
    private int idMedico;
    private int idRecepcionista;
    
    /**
     * Constructor con todos los parámetros
     * @param idCita
     * @param fechaCita
     * @param horaCita
     * @param estadoCita
     * @param motivoConsulta
     * @param fechaAsignacion
     * @param idPaciente
     * @param idMedico
     * @param idRecepcionista
     */
    public CitaDto(int idCita, Date fechaCita, Time horaCita, String estadoCita, 
                     String motivoConsulta, Date fechaAsignacion, int idPaciente, 
                     int idMedico, int idRecepcionista) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.estadoCita = estadoCita;
        this.motivoConsulta = motivoConsulta;
        this.fechaAsignacion = fechaAsignacion;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idRecepcionista = idRecepcionista;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Time horaCita) {
        this.horaCita = horaCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }
} 