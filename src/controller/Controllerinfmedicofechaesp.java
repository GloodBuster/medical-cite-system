/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.infmedicofechaesp;

/**
 *
 * @author macse
 */
public class Controllerinfmedicofechaesp implements ActionListener {
    private infmedicofechaesp view;
    
    public Controllerinfmedicofechaesp(infmedicofechaesp view){
        this.view = view;
        this.view.infmedfechbutOk.addActionListener(this);
        this.view.setVisible(true);
    
}
  @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.infmedfechbutOk)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }
    }
    
}
