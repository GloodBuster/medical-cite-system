package model.dao;

import java.sql.Connection;
import model.dto.CitaDto;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 *
 * @author josev
 */
public class CitaDao {
    private Connection conectar;
    
    public boolean create(CitaDto cita) throws SQLException {
        boolean rowCreate = false;
        try{
            String sql = "INSERT INTO cita (idCita, fechaCita, horaCita, estadoCita,"
                    + "motivoConsulta, fechaAsignacion, idPaciente," +
"                     idMedico, idRecepcionista) VALUES(?,?,?,?,?,?,?,?,?);";
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            
            statement.setInt(1, cita.getIdCita());
            statement.setDate(2, new Date(cita.getFechaCita().getTime()));
            statement.setTime(3, new Time(cita.getHoraCita().getTime()));
            statement.setString(4, cita.getEstadoCita());
            statement.setString(5, cita.getMotivoConsulta());
            statement.setDate(6, new Date(new java.util.Date().getTime()));
            statement.setInt(7, cita.getIdPaciente());
            statement.setInt(8, cita.getIdMedico());
            statement.setInt(9, cita.getIdRecepcionista());
            
            rowCreate = statement.executeUpdate() > 0;
            statement.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            Conexion.cerrarConexion();
        }
        return rowCreate;
    }
    
    public CitaDto read(int idCita) throws SQLException{
        CitaDto cita = null;
        try{
            String sql = "SELECT * FROM cita WHERE idCita = ?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idCita);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                cita = new CitaDto(resultSet.getInt("idCita"), resultSet.getDate("fechaCita"), resultSet.getTime("horaCita"),
                resultSet.getString("estadoCita"), resultSet.getString("motivoConsulta"), resultSet.getDate("fechaAsignacion"),
                resultSet.getInt("idPaciente"), resultSet.getInt("idMedico"), resultSet.getInt("idRecepcionista"));
            }
            resultSet.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return cita;
    }
    
    public List<CitaDto> listaCitas() throws SQLException {
        List<CitaDto> listaCitas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cita;";
            conectar = Conexion.conectar();
            
            Statement statement = (Statement) conectar.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                CitaDto cita = new CitaDto(resultSet.getInt("idCita"), resultSet.getDate("fechaCita"), resultSet.getTime("horaCita"),
                resultSet.getString("estadoCita"), resultSet.getString("motivoConsulta"), resultSet.getDate("fechaAsignacion"),
                resultSet.getInt("idPaciente"), resultSet.getInt("idMedico"), resultSet.getInt("idRecepcionista"));
                listaCitas.add(cita);
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return listaCitas;
    }
    
    public boolean update(CitaDto cita) throws SQLException {
        boolean rowUpdate = false;
        try {
            String sql = "UPDATE cita SET fechaCita=?, horaCita=?, estadoCita=?, "
                    + "motivoConsulta=?, idPaciente=?, idMedico=?, idRecepcionista=? "
                    + "WHERE idCita=?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            
            statement.setDate(1, new Date(cita.getFechaCita().getTime()));
            statement.setTime(2, new Time(cita.getHoraCita().getTime()));
            statement.setString(3, cita.getEstadoCita());
            statement.setString(4, cita.getMotivoConsulta());
            statement.setInt(5, cita.getIdPaciente());
            statement.setInt(6, cita.getIdMedico());
            statement.setInt(7, cita.getIdRecepcionista());
            statement.setInt(8, cita.getIdCita());
            
            rowUpdate = statement.executeUpdate() > 0;
            statement.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return rowUpdate;
    }
    
    public List<CitaDto> buscarCitasPorPaciente(int idPaciente) throws SQLException {
        List<CitaDto> listaCitas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cita WHERE idPaciente = ?";
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idPaciente);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                CitaDto cita = new CitaDto(
                    resultSet.getInt("idCita"), 
                    resultSet.getDate("fechaCita"), 
                    resultSet.getTime("horaCita"),
                    resultSet.getString("estadoCita"), 
                    resultSet.getString("motivoConsulta"), 
                    resultSet.getDate("fechaAsignacion"),
                    resultSet.getInt("idPaciente"), 
                    resultSet.getInt("idMedico"), 
                    resultSet.getInt("idRecepcionista")
                );
                listaCitas.add(cita);
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return listaCitas;
    }
} 