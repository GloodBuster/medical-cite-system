/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AllCitesView;
import view.CiteView;
import view.MainView;
import view.PatientHistoryView;
import view.inf_Medico;
import view.inf_Patient;
import view.inf_Receptionist;

/**
 *
 * @author caes2
 */
public class MainController implements ActionListener{
    private MainView view;
    
    public MainController(MainView view){
        //para hacer visible el panel
        this.view = view;             
        this.view.setVisible(true);
        //opciones de los datos (paciente,medico,recepcionista) 
        this.view.OP_Paciente.addActionListener(this);
        this.view.OP_Medico.addActionListener(this);
        this.view.OP_Recepcionista.addActionListener(this);
        this.view.OP_Citas.addActionListener(this);
        this.view.Consultar_historial_del_paciente.addActionListener(this);
        this.view.OP_ConCitas_todosLosPacientes.addActionListener(this);
    }
        //aca se ingresa el codigo para generar las acciones 
    @Override
    public void actionPerformed(ActionEvent ae) {
        //esta accion al momento de escoger una de las opciones (Datos del paciente, medico, recepcionista)se abre la ventana
        //de la opcion escogida
        if(ae.getSource().equals(this.view.OP_Paciente)){
            //llamamos el controlador del la ventana del paciente o inf_Patient y la hacemos visible
            ControllerPatient ControllerPatient = new ControllerPatient(new inf_Patient());
        }else if(ae.getSource().equals(this.view.OP_Medico)){
            ControllerMedico ControllerMedico = new ControllerMedico(new inf_Medico());
        }else if(ae.getSource().equals(this.view.OP_Recepcionista)){
            ControllerReceptionist ControllerReceptionist = new ControllerReceptionist(new inf_Receptionist());
        }else if(ae.getSource().equals(this.view.OP_Citas)){
            ControllerCite _ = new ControllerCite(new CiteView());
        }else if(ae.getSource().equals(this.view.Consultar_historial_del_paciente)){
            PatientHistoryController _ = new PatientHistoryController(new PatientHistoryView());
        }else if(ae.getSource().equals(this.view.OP_ConCitas_todosLosPacientes)){
            AllCitesController _ = new AllCitesController(new AllCitesView());
        }
            
    }
    
}
