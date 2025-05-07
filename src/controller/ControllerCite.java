package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.CiteView;
import javax.swing.WindowConstants;
/**
 *
 * @author caes2
 */
public class ControllerCite implements ActionListener {
    
    private CiteView view;
    
    public ControllerCite(CiteView view){
        this.view = view;
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        this.view.BT_Buscar.addActionListener(this);        
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Guardar.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.BT_Buscar)){
            JOptionPane.showMessageDialog(null, "En construcción backend");
        } else if(ae.getSource().equals(this.view.BT_Guardar)){
            JOptionPane.showMessageDialog(null, "En construcción backend");
        } else if(ae.getSource().equals(this.view.BT_Modificar)){
            JOptionPane.showMessageDialog(null, "En construcción backend");
        }
    }
    
}