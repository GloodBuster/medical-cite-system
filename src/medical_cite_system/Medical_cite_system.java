/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medical_cite_system;

import controller.MainController;
import view.MainView;

/**
 *
 * @author caes2
 */
public class Medical_cite_system {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setTitle("Pantalla principal");
        MainController _ = new MainController(mainView);
        
    }
    
}
