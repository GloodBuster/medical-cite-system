package model.dao;

import java.sql.Connection;
import model.dto.Medico;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josev
 */
public class MedicoDaoAux {
    private Connection conectar;
    
    public Medico read(int idMedico) throws SQLException {
        Medico medico = null;
        try {
            String sql = "SELECT * FROM medico WHERE idMedico =?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            statement.setInt(1, idMedico);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                medico = new Medico(
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
    
    public boolean create(Medico medico) throws SQLException {
        boolean rowCreate = false;
        try {
            String sql = "INSERT INTO medico (idMedico, nombres, apellidos, fechaNacimiento, telefono, direccion, email, fechaDeIngreso, especialidad) VALUES(?,?,?,?,?,?,?,?,?)";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            
            statement.setInt(1, medico.getIdMedico());
            statement.setString(2, medico.getNombres());
            statement.setString(3, medico.getApellidos());
            statement.setDate(4, new java.sql.Date(medico.getFechaNacimiento().getTime()));
            statement.setInt(5, medico.getTelefono());
            statement.setString(6, medico.getDireccion());
            statement.setString(7, medico.getEmail());
            statement.setDate(8, new java.sql.Date(medico.getFechaDeIngreso().getTime()));
            statement.setString(9, medico.getEspecialidad());
            
            rowCreate = statement.executeUpdate() > 0;
            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return rowCreate;
    }
    
    public boolean update(Medico medico) throws SQLException {
        boolean rowUpdate = false;
        try {
            String sql = "UPDATE medico SET nombres=?, apellidos=?, fechaNacimiento=?, telefono=?, direccion=?, email=?, fechaDeIngreso=?, especialidad=? WHERE idMedico=?";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            
            statement.setString(1, medico.getNombres());
            statement.setString(2, medico.getApellidos());
            statement.setDate(3, new java.sql.Date(medico.getFechaNacimiento().getTime()));
            statement.setInt(4, medico.getTelefono());
            statement.setString(5, medico.getDireccion());
            statement.setString(6, medico.getEmail());
            statement.setDate(7, new java.sql.Date(medico.getFechaDeIngreso().getTime()));
            statement.setString(8, medico.getEspecialidad());
            statement.setInt(9, medico.getIdMedico());
            
            rowUpdate = statement.executeUpdate() > 0;
            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return rowUpdate;
    }
    
    public List<Medico> listarMedicos() throws SQLException {
        List<Medico> listaMedicos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM medico";
            
            conectar = Conexion.conectar();
            
            PreparedStatement statement = (PreparedStatement) conectar.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Medico medico = new Medico(
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
                listaMedicos.add(medico);
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
        }
        return listaMedicos;
    }
} 