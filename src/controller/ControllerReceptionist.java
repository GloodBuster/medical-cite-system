/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.inf_Receptionist;
import model.dao.RecepcionistaDao;
import model.dto.Recepcionista;


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
public class ControllerReceptionist implements ActionListener{
    
    private inf_Receptionist view;
    private Recepcionista recepcionista;
    private RecepcionistaDao model;
    private DefaultTableModel modeloTable;
    
    public ControllerReceptionist(inf_Receptionist view){
        this.view = view;
        this.view.setVisible(true);
        
        this.model = new RecepcionistaDao();
        modeloTable = (DefaultTableModel) this.view.Tabla_Info_Recepcionista.getModel();

        
        this.view.Bt_Guardar_Inf.addActionListener(this);
        this.view.Bt_Buscar.addActionListener(this);
        this.view.Bt_Modificar.addActionListener(this);
        this.view.Bt_Eliminar.addActionListener(this);
        this.view.Bt_Ver_lista_recepcionista.addActionListener(this);
    }
    
    
    
   @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.Bt_Guardar_Inf)){
            recepcionista = new Recepcionista();
            
            recepcionista.setIdRecepcionista(Integer.parseInt(this.view.idRecepcionista.getText()));
            recepcionista.setNombres(view.NombreRecepcionista.getText());
            recepcionista.setFechaDeIngreso(view.fechaDeIngreso.getDate());
                   
            if(model.create(recepcionista)>0){
                JOptionPane.showMessageDialog(null, "Un nuevo recepcionista se registro ");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo registrar el recepcionista");
                
            }
        }
        
            
        if(ae.getSource().equals(this.view.Bt_Buscar)){
            recepcionista = model.read(Integer.parseInt(view.idRecepcionista.getText()));
            
            if(recepcionista!=null){
                view.NombreRecepcionista.setText(recepcionista.getNombres());  
                view.fechaDeIngreso.setDate(recepcionista.getFechaDeIngreso());

            }else{
                JOptionPane.showMessageDialog(null,"El recepcionista no existe");
            }
        } 
            
        if(ae.getSource().equals(this.view.Bt_Eliminar)){
            int respuesta = JOptionPane.showConfirmDialog(null,"Estas seguro de borrar la informacion?", "Confirmar",JOptionPane.YES_NO_OPTION);
        
            if(respuesta == JOptionPane.YES_OPTION){
                if(model.delete(recepcionista)>0){
                    JOptionPane.showMessageDialog(null,"El recepcionista fue eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al eliminar el recepcionista");
                }
            }
        
        }
        
        
         if(ae.getSource().equals(this.view.Bt_Modificar)){
            int respuesta = JOptionPane.showConfirmDialog(null,"Estas seguro de modificar?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(respuesta==JOptionPane.YES_OPTION){
                if(model.update(recepcionista)>0){
                    JOptionPane.showMessageDialog(null,"El recepcionista fue modificado");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al modificar el recepcionista");

                }
            }
        }
         
         if(ae.getSource().equals(this.view.Bt_Ver_lista_recepcionista)){
           
            List<Recepcionista> listaRecepcionista = model.readAll();
            int filas = modeloTable.getRowCount();
            
            for(int i = 0; i < filas; i++ ){
            modeloTable.removeRow(0);
        }
            for(Recepcionista paciente : listaRecepcionista){
                Object[] fila= {recepcionista.getIdRecepcionista(),recepcionista.getNombres(),recepcionista.getFechaDeIngreso()};
                modeloTable.addRow(fila);
            }
            
        }
        
    }
    
}
