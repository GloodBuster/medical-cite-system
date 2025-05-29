package model.dao;

import java.sql.Connection;
import model.dto.AgendaDtoAux;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author josev
 */
public class AgendaDaoAux {
    private Connection conectar;
    
    public boolean create(AgendaDtoAux agenda) throws SQLException {
        boolean rowCreate = false;
        try {
            String sql = "INSERT INTO agenda (idMedico, fecha, horaInicio, horaFinal) VALUES(?,?,?,?)";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, agenda.getIdMedico());
            statement.setDate(2, new java.sql.Date(agenda.getFecha().getTime()));
            statement.setTime(3, agenda.getHoraInicio());
            statement.setTime(4, agenda.getHoraFinal());
            
            rowCreate = statement.executeUpdate() > 0;
            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return rowCreate;
    }
    
    public List<AgendaDtoAux> listarAgendasPorMedico(int idMedico) throws SQLException {
        List<AgendaDtoAux> listaAgendas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM agenda WHERE idMedico = ?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idMedico);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                AgendaDtoAux agenda = new AgendaDtoAux(
                    resultSet.getInt("idMedico"),
                    resultSet.getDate("fecha"),
                    resultSet.getTime("horaInicio"),
                    resultSet.getTime("horaFinal")
                );
                listaAgendas.add(agenda);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return listaAgendas;
    }
    
    public List<AgendaDtoAux> listarAgendasFuturasPorMedico(int idMedico) throws SQLException {
        List<AgendaDtoAux> listaAgendas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM agenda WHERE idMedico = ? AND fecha >= ?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idMedico);
            
            // Obtener la fecha actual y convertirla a java.sql.Date
            Date fechaActual = new Date();
            java.sql.Date sqlFechaActual = new java.sql.Date(fechaActual.getTime());
            statement.setDate(2, sqlFechaActual);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                AgendaDtoAux agenda = new AgendaDtoAux(
                    resultSet.getInt("idMedico"),
                    resultSet.getDate("fecha"),
                    resultSet.getTime("horaInicio"),
                    resultSet.getTime("horaFinal")
                );
                listaAgendas.add(agenda);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return listaAgendas;
    }
} 