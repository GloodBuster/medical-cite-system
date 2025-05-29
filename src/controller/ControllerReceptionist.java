/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import view.inf_Receptionist;

/**
 *
 * @author caes2
 */
public class ControllerReceptionist implements ActionListener{
    
    private inf_Receptionist view;
    
    public ControllerReceptionist(inf_Receptionist view){
        this.view = view;
        this.view.setVisible(true);
        
        this.view.Bt_Guardar_Inf.addActionListener(this);
        this.view.Bt_Modificar.addActionListener(this);
        this.view.Bt_Buscar.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Informacion
         if(ae.getSource().equals(this.view.Bt_Guardar_Inf)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.Bt_Modificar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.Bt_Buscar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }

    }
    
}
