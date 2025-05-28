/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import model.dto.PacienteDtoAux;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author josev
 */
public class PacienteDaoAux {
    private Connection conectar;
    
    public PacienteDtoAux read(int idPaciente) throws SQLException {
        PacienteDtoAux paciente = null;
        try {
            String sql = "SELECT * FROM paciente WHERE idPaciente =?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idPaciente);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                paciente = new PacienteDtoAux(
                    resultSet.getInt("idPaciente"),
                    resultSet.getString("nombres"),
                    resultSet.getString("apellidos"),
                    resultSet.getDate("fechaNacimiento"),
                    resultSet.getInt("telefono"),
                    resultSet.getString("direccion"),
                    resultSet.getString("email")
                );
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return paciente;
    }
}
