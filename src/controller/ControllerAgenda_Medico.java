/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.Agenda_Medico;

/**
 *
 * @author caes2
 */
public class ControllerAgenda_Medico implements ActionListener{
    
    private Agenda_Medico view;
    
    public ControllerAgenda_Medico(Agenda_Medico view){
        this.view = view;
        this.view.setVisible(true);
        
        
        //botones sin ninguna accion (backend)
        this.view.BT_guardar.addActionListener(this);
        this.view.BT_editar.addActionListener(this);
       
        
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
            
        //Informacion
         if(ae.getSource().equals(this.view.BT_guardar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.BT_editar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }
    }
    
}
