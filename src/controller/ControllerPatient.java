/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import model.dao.PacienteDao;
import model.dto.Paciente;
import view.inf_Patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author caes2
 */
public class ControllerPatient implements ActionListener {
    
    private inf_Patient view;
    private Paciente paciente;
    private PacienteDao model;
    private DefaultTableModel modeloTable;
    
    public ControllerPatient(inf_Patient view){
        this.view = view;
        this.view.setVisible(true);
        
        this.model = new PacienteDao();
        modeloTable = (DefaultTableModel) this.view.tabla_Pacientes.getModel();
        
        this.view.BT_Guardar.addActionListener(this);
        this.view.BT_Buscar.addActionListener(this);
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Eliminar.addActionListener(this);
        this.view.BT_Ver_lista_paciente.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource().equals(this.view.BT_Guardar)){
            paciente = new Paciente();
            
            paciente.setIdPaciente(Integer.parseInt(this.view.idPaciente1.getText()));
            paciente.setNombres(view.Nombre.getText());
            paciente.setApellidos(view.pApellido.getText()+" "+view.sApellido1.getText());
            paciente.setFechaNacimiento(view.fechaDeNacimiento.getDate());
            paciente.setTelefono(Integer.parseInt(this.view.telefono.getText()));
            paciente.setDireccion(view.direccion.getText());
            paciente.setEmail(view.correo.getText()+"@"+view.com.getText());
        
            if(model.create(paciente)>0){
                JOptionPane.showMessageDialog(null, "Un nuevo paciente se registro ");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo registrar el paciente");
                
            }
                       
        }
        
            
        if(ae.getSource().equals(this.view.BT_Buscar)){
            paciente = model.read(Integer.parseInt(view.idPaciente1.getText()));
            
            if(paciente!=null){
                view.Nombre.setText(paciente.getNombres());
                String [] apellidos = paciente.getApellidos().split(" ");
                view.pApellido.setText(apellidos[0]);
                view.sApellido1.setText(apellidos[1]);
                view.fechaDeNacimiento.setDate(paciente.getFechaNacimiento());
                view.telefono.setText(String.valueOf(paciente.getTelefono()));
                view.direccion.setText(paciente.getDireccion());
                String [] correo = paciente.getEmail().split("@");
                view.correo.setText(correo[0]);
                view.com.setText(correo[1]);
                
            }else{
                JOptionPane.showMessageDialog(null,"El paciente no existe");
            }
        } 
            
        if(ae.getSource().equals(this.view.BT_Eliminar)){
            int respuesta = JOptionPane.showConfirmDialog(null,"Estas seguro de borrar la informacion?", "Confirmar",JOptionPane.YES_NO_OPTION);
        
            if(respuesta == JOptionPane.YES_OPTION){
                if(model.delete(paciente)>0){
                    JOptionPane.showMessageDialog(null,"El paciente fue eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al eliminar el paciente");
                }
            }
        
        }
        
        
         if(ae.getSource().equals(this.view.BT_Modificar)){
            int respuesta = JOptionPane.showConfirmDialog(null,"Estas seguro de modificar?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.YES_OPTION){
                if(model.update(paciente)>0){
                    JOptionPane.showMessageDialog(null,"El paciente fue modificado");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar el paciente");

                }
            }
        }
         
         if(ae.getSource().equals(this.view.BT_Ver_lista_paciente)){
           
            List<Paciente> listaPaciente = model.readAll();
            int filas = modeloTable.getRowCount();
            
            for(int i = 0; i < filas; i++ ){
            modeloTable.removeRow(0);
        }
            for(Paciente paciente : listaPaciente){
                Object[] fila= {paciente.getIdPaciente(),paciente.getNombres(),paciente.getApellidos(),paciente.getFechaNacimiento(),paciente.getTelefono(),paciente.getEmail(),paciente.getDireccion()};
                modeloTable.addRow(fila);
            }
            
        }
        
    }
    
}
