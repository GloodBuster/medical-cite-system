/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.Infmedicorangofecha;

/**
 *
 * @author macse
 */
public class Controllerinfmedicorangofecha implements ActionListener {
    private Infmedicorangofecha view;
    
    /**
     *
     * @param view
     */
    public Controllerinfmedicorangofecha(Infmedicorangofecha view){
        this.view = view;
        this.view.setTitle("Por medico y rango de fechas");
        this.view.infmedbutOk.addActionListener(this);
        this.view.setVisible(true);
    
}
  @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.infmedbutOk)){
            JOptionPane.showMessageDialog(null,"En construccion backend");
        }
    }
    
}


