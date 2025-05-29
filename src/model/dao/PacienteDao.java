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
    private Paciente Paciente;
   
    
   public int create(Paciente Paciente){
       try{
           coneccion = Conexion.conectar();
           
           PreparedStatement ps = coneccion.prepareStatement("INSERT INTO Paciente(idPaciente,nombres,apellidos,fechaNacimiento,telefono,direccion,email) VALUES(?,?,?,?,?,?,?)");
           ps.setInt(1, Paciente.getIdPaciente());
           ps.setString(2, Paciente.getNombres());
           ps.setString(3, Paciente.getApellidos());
           ps.setDate(4, (Date) Paciente.getFechaNacimiento());
           ps.setInt(5, Paciente.getTelefono());
           ps.setString(6, Paciente.getDireccion());
           ps.setString(7, Paciente.getEmail());
           return ps.executeUpdate();
       
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null,e);
           return 0;
       }
   }    
}
