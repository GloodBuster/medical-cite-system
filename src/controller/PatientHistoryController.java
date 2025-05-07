package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.PatientHistoryView;
/**
 *
 * @author caes2
 */
public class PatientHistoryController implements ActionListener {
    
    private PatientHistoryView view;
    
    public PatientHistoryController(PatientHistoryView view){
        this.view = view;
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        this.view.searchButton.addActionListener(this);        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.searchButton)){
            JOptionPane.showMessageDialog(null, "En construcción backend");
        }
    }
    
}