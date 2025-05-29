/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.dto.Recepcionista;
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
public class RecepcionistaDao {

    private Connection coneccion;
    private Recepcionista recepcionista;
   
    
   public int create(Recepcionista Recepcionista){
       try{
           coneccion = Conexion.conectar();
           
           PreparedStatement ps = coneccion.prepareStatement("INSERT INTO Recepcionista(idRecepcionista,nombres,fechaDeIngreso) VALUES(?,?,?)");
           ps.setInt(1, Recepcionista.getIdRecepcionista());
           ps.setString(2, Recepcionista.getNombres());
           ps.setDate(3, new Date(Recepcionista.getFechaDeIngreso().getTime()));
           
           return ps.executeUpdate();
       
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null,e);
           return 0;
       }
   } 
  

    public Recepcionista read(int idRecepcionista){
        try{
            coneccion = Conexion.conectar();
            PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM Recepcionista WHERE idRecepcionista =?");
            ps.setInt(1, idRecepcionista);
            
            ResultSet resultado = ps.executeQuery();
            

            recepcionista = new Recepcionista();
           
            if(resultado.next()){
                recepcionista.setIdRecepcionista(resultado.getInt("idRecepcionista"));
                recepcionista.setNombres(resultado.getString("nombres"));
                recepcionista.setFechaDeIngreso(resultado.getDate("fechaDeIngreso"));
                }else{
                JOptionPane.showMessageDialog(null,"No existe este recepcionista");
                return null;
            }      
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
            
        }
        return recepcionista;
    }
    
    public boolean update(Recepcionista Recepcionista)throws SQLException{
	boolean rowUpdate = false;
        try{
            String sql = "UPDATE Recepcionista SET nombres=?,  fechaDeIngreso=? WHERE idRecepcionista=?";
            coneccion = Conexion.conectar();
            PreparedStatement statement = (PreparedStatement) coneccion.prepareStatement(sql);
            
             statement.setString(1, recepcionista.getNombres());
           statement.setDate(2, new Date( recepcionista.getFechaDeIngreso().getTime()));
           statement.setInt(3, recepcionista.getIdRecepcionista());
            
            rowUpdate = statement.executeUpdate() > 0;
            statement.close();
            Conexion.cerrarConexion();
	} catch (Exception e) {
            System.out.println("error"+e.getMessage());
        }
     	return rowUpdate;
    }
    
    
    
    
   /* public int update(Recepcionista Recepcionista){
       try{
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("UPDATE Recepcionista SET nombres=?,  fechaDeIngreso=? WHERE idRecepcionista=?");
           
           ps.setString(1, recepcionista.getNombres());
           ps.setDate(2, new Date( recepcionista.getFechaDeIngreso().getTime()));
           ps.setInt(3, recepcionista.getIdRecepcionista());
                        
           return ps.executeUpdate();
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
             
    }*/
    
   public int delete(Recepcionista Recepcionista){
       try{
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("DELETE from Recepcionista WHERE idRecepcionista=?");
           ps.setInt(1, recepcionista.getIdRecepcionista());
           return ps.executeUpdate();
                           
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return 0;
       }
   }
   
   public List<Recepcionista> readAll(){
       try{
           List<Recepcionista> lista = new ArrayList<>();
           coneccion = Conexion.conectar();
           PreparedStatement ps = coneccion.prepareStatement("SELECT * FROM Recepcionista");
           ResultSet resultado = ps.executeQuery();
           
           
           
           while (resultado.next()) {
                Recepcionista  rec=new Recepcionista();
                rec.setIdRecepcionista(resultado.getInt("idRecepcionista"));
                rec.setNombres(resultado.getString("nombres"));
                rec.setFechaDeIngreso(resultado.getDate("fechaDeIngreso"));
              
                lista.add(rec); 
           }
           
           return lista;
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
       }
   }
}
