/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.dto.Paciente;
import model.dao.Conexion;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgresql.PGProperty;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author caes2
 */
public class PacienteDao {

    private Connection coneccion;
    private Paciente paciente;
   
    
   public int create(Paciente Paciente){
       try{
           coneccion = Conexion.conectar();
           
           PreparedStatement ps = coneccion.prepareStatement("INSERT INTO Paciente(idPaciente,nombres,apellidos,fechaNacimiento,telefono,direccion,email) VALUES(?,?,?,?,?,?,?)");
           ps.setInt(1, Paciente.getIdPaciente());
           ps.setString(2, Paciente.getNombres());
           ps.setString(3, Paciente.getApellidos());
           ps.setDate(4, new Date(Paciente.getFechaNacimiento().getTime()));
           ps.setInt(5, Paciente.getTelefono());
           ps.setString(6, Paciente.getDireccion());
           ps.setString(7, Paciente.getEmail());
           return ps.executeUpdate();
       
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null,e);
           return 0;
       }
   } 
  

    public Paciente read(int idPaciente){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM Paciente WHERE idPaciente =?");
            ps.setInt(1, idPaciente);
            
            ResultSet resultado = ps.executeQuery();
            

            paciente = new Paciente();
           
            if(resultado.next()){
                paciente.setIdPaciente(resultado.getInt("idPaciente"));
                paciente.setNombres(resultado.getString("nombres"));
                paciente.setApellidos(resultado.getString("apellidos"));
                paciente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                paciente.setTelefono(resultado.getInt("telefono"));
                paciente.setDireccion(resultado.getString("direccion"));
                paciente.setEmail(resultado.getString("email"));
            }else{
                JOptionPane.showMessageDialog(null,"No existe este paciente");
                return null;
            }      
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
            
        }
        return paciente;
    }
    
    /*public boolean update(Paciente Paciente)throws SQLException{
	boolean rowUpdate = false;
        try{
            String sql = "UPDATE Paciente SET nombres=?, apellidos=?, fechaNacimiento=?, telefono=?, direccion=?, email=? WHERE idPaciente=?";
            coneccion = Conexion.conectar();
            PreparedStatement statement = (PreparedStatement) coneccion.prepareStatement(sql);
            
           statement.setString(1, Paciente.getNombres());
           statement.setString(2, Paciente.getApellidos());
           statement.setDate(3, (Date) Paciente.getFechaNacimiento());
           statement.setInt(4, Paciente.getTelefono());
           statement.setString(5, Paciente.getDireccion());
           statement.setString(6, Paciente.getEmail());
           statement.setInt(7, Paciente.getIdPaciente());
            
            rowUpdate = statement.executeUpdate() > 0;
            statement.close();
            Conexion.cerrarConexion();
	} catch (Exception e) {
            System.out.println("error");
        }
     	return rowUpdate;
    }*/
    
    
    public int update(Paciente Paciente){
       try{
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("UPDATE Paciente SET nombres=?, apellidos=?, fechaNacimiento=?, telefono=?, direccion=?, email=? WHERE idPaciente=?");
           
           
           ps.setString(1, Paciente.getNombres());
           ps.setString(2, Paciente.getApellidos());
           ps.setDate(3, (Date) Paciente.getFechaNacimiento());
           ps.setInt(4, Paciente.getTelefono());
           ps.setString(5, Paciente.getDireccion());
           ps.setString(6, Paciente.getEmail());
           ps.setInt(7, Paciente.getIdPaciente());
                   
                   
           return ps.executeUpdate();
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
             
    }
    
   public int delete(Paciente Paciente){
       try{
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("DELETE from Paciente WHERE idPaciente=?");
           ps.setInt(1, Paciente.getIdPaciente());
           return ps.executeUpdate();
                           
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
       }
   }
   
   public List<Paciente> readAll(){
       try{
           List<Paciente> lista = new ArrayList<>();
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM Paciente");
           ResultSet resultado = ps.executeQuery();
           
           paciente  =new Paciente();
           
           while (resultado.next()) {
               
                paciente.setIdPaciente(resultado.getInt("idPaciente"));
                paciente.setNombres(resultado.getString("nombres"));
                paciente.setApellidos(resultado.getString("apellidos"));
                paciente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                paciente.setTelefono(resultado.getInt("telefono"));
                paciente.setDireccion(resultado.getString("direccion"));
                paciente.setEmail(resultado.getString("email"));
                
                lista.add(paciente); 
           }
           
           return lista;
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
       }
   }
}
