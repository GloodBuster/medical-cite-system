/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.inf_Medico;
import view.Agenda_Medico;

/**
 *
 * @author caes2
 */
public class ControllerMedico implements ActionListener {
    
    private inf_Medico view;
    
    public ControllerMedico(inf_Medico view){
        this.view = view;
        this.view.setVisible(true);
        
        //Boton de agenda para visualizar el panel de la agenda del medico      
        this.view.BT_Agenda.addActionListener(this);
        //botones sin ninguna accion (backend)
        this.view.BT_Guardar.addActionListener(this);
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Buscar.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //para ver el panel de la agenda
        if(ae.getSource().equals(this.view.BT_Agenda)){
                        ControllerAgenda_Medico ControllerAgenda_Medico = new ControllerAgenda_Medico(new Agenda_Medico());
        }
        
        //Informacion
         if(ae.getSource().equals(this.view.BT_Guardar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.BT_Modificar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.BT_Buscar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }
    }
    
}
