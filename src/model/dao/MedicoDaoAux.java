package model.dao;

import java.sql.Connection;
import model.dto.MedicoDtoAux;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author josev
 */
public class MedicoDaoAux {
    private Connection conectar;
    
    public MedicoDtoAux read(int idMedico) throws SQLException {
        MedicoDtoAux medico = null;
        try {
            String sql = "SELECT * FROM medico WHERE idMedico =?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idMedico);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                medico = new MedicoDtoAux(
                    resultSet.getInt("idMedico"),
                    resultSet.getString("nombres"),
                    resultSet.getString("apellidos"),
                    resultSet.getDate("fechaNacimiento"),
                    resultSet.getInt("telefono"),
                    resultSet.getString("direccion"),
                    resultSet.getString("email"),
                    resultSet.getDate("fechaDeIngreso"),
                    resultSet.getString("especialidad")
                );
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return medico;
    }
} 