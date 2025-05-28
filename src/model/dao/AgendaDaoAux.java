package model.dao;

import java.sql.Connection;
import model.dto.AgendaDtoAux;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josev
 */
public class AgendaDaoAux {
    private Connection conectar;
    
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
                    resultSet.getInt("idAgenda"),
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