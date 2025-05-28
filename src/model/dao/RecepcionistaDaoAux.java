package model.dao;

import java.sql.Connection;
import model.dto.RecepcionistaDtoAux;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author josev
 */
public class RecepcionistaDaoAux {
    private Connection conectar;
    
    public RecepcionistaDtoAux read(int idRecepcionista) throws SQLException {
        RecepcionistaDtoAux recepcionista = null;
        try {
            String sql = "SELECT * FROM recepcionista WHERE idRecepcionista =?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idRecepcionista);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                recepcionista = new RecepcionistaDtoAux(
                    resultSet.getInt("idRecepcionista"),
                    resultSet.getString("nombres"),
                    resultSet.getDate("fechaDeIngreso")
                );
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return recepcionista;
    }
} 