/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.inf_Patient;

/**
 *
 * @author caes2
 */
public class ControllerPatient implements ActionListener {
    
    private inf_Patient view;
    
    public ControllerPatient(inf_Patient view){
        this.view = view;
        this.view.BT_Guardar.addActionListener(this);
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Buscar.addActionListener(this);
        this.view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.BT_Guardar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.BT_Modificar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }else if(ae.getSource().equals(this.view.BT_Buscar)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }
        
    }
    
}
