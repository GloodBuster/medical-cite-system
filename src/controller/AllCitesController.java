package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import view.AllCitesView;
/**
 *
 * @author caes2
 */
public class AllCitesController implements ActionListener {
    
    private AllCitesView view;
    
    public AllCitesController(AllCitesView view){
        this.view = view;
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}