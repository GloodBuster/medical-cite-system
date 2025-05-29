/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import model.dao.AgendaDaoAux;
import model.dao.MedicoDaoAux;
import model.dto.AgendaDtoAux;
import model.dto.Medico;
import view.Agenda_Medico;

/**
 *
 * @author josev
 */
public class ControllerAgenda_Medico implements ActionListener {
    private Agenda_Medico view;
    private AgendaDaoAux agendaDao;
    private MedicoDaoAux medicoDao;
    private SimpleDateFormat dateFormat;
    private int idMedico;
    
    public ControllerAgenda_Medico(Agenda_Medico view, int idMedico) {
        this.view = view;
        this.view.setTitle("Agenda");
        this.agendaDao = new AgendaDaoAux();
        this.medicoDao = new MedicoDaoAux();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        // Verificar si el médico existe
        try {
            Medico medico = medicoDao.read(idMedico);
            if (medico == null) {
                JOptionPane.showMessageDialog(null, "El médico con ID " + idMedico + " no existe");
                view.dispose();
                return;
            }
            
            // Si el médico existe, continuar con la inicialización
            this.idMedico = idMedico;
            
            // Configurar la vista
            view.jDateChooser1.setDateFormatString("yyyy-MM-dd");
            
            // Configurar los ComboBox de horas
            configurarComboBoxHoras();
            
            // Configurar la tabla
            DefaultTableModel model = (DefaultTableModel) view.jTable1.getModel();
            model.setRowCount(0);
            
            // Cargar la agenda del médico (todas las fechas para la vista)
            List<AgendaDtoAux> agendas = agendaDao.listarAgendasPorMedico(idMedico);
            for (AgendaDtoAux agenda : agendas) {
                Object[] row = {
                    agenda.getIdMedico(),
                    dateFormat.format(agenda.getFecha()),
                    agenda.getHoraInicio(),
                    agenda.getHoraFinal()
                };
                model.addRow(row);
            }
            
            // Agregar listeners
            this.view.BT_guardar.addActionListener(this);
            this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.view.setVisible(true);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar el médico: " + ex.getMessage());
            view.dispose();
        }
    }
    
    private void configurarComboBoxHoras() {
        // Crear arrays con las horas del día
        String[] horas = new String[24];
        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d:00", i);
        }
        
        // Configurar los ComboBox
        view.horaInicioCombo.removeAllItems();
        view.horaFinalCombo.removeAllItems();
        
        for (String hora : horas) {
            view.horaInicioCombo.addItem(hora);
            view.horaFinalCombo.addItem(hora);
        }
    }
    
    private boolean validarHorario(String horaInicio, String horaFinal) {
        // Convertir las horas a minutos para facilitar la comparación
        String[] inicio = horaInicio.split(":");
        String[] fin = horaFinal.split(":");
        
        int minutosInicio = Integer.parseInt(inicio[0]) * 60;
        int minutosFinal = Integer.parseInt(fin[0]) * 60;
        
        return minutosFinal > minutosInicio;
    }
    
    private boolean validarFecha(Date fecha) {
        // Obtener la fecha actual sin la hora
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fechaActual = cal.getTime();
        
        // Obtener la fecha seleccionada sin la hora
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fechaSeleccionada = cal.getTime();
        
        // La fecha seleccionada debe ser igual o posterior a la fecha actual
        return !fechaSeleccionada.before(fechaActual);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.BT_guardar)) {
            try {
                Date fecha = view.jDateChooser1.getDate();
                if (fecha == null) {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha");
                    return;
                }
                
                // Validar que la fecha sea igual o posterior a hoy
                if (!validarFecha(fecha)) {
                    JOptionPane.showMessageDialog(null, "La fecha seleccionada debe ser igual o posterior a hoy");
                    return;
                }
                
                String horaInicio = (String) view.horaInicioCombo.getSelectedItem();
                String horaFinal = (String) view.horaFinalCombo.getSelectedItem();
                
                if (!validarHorario(horaInicio, horaFinal)) {
                    JOptionPane.showMessageDialog(null, "La hora final debe ser posterior a la hora inicial");
                    return;
                }
                
                // Crear y guardar la agenda
                AgendaDtoAux agenda = new AgendaDtoAux(
                    this.idMedico,
                    fecha,
                    Time.valueOf(horaInicio + ":00"),
                    Time.valueOf(horaFinal + ":00")
                );
                
                if (agendaDao.create(agenda)) {
                    JOptionPane.showMessageDialog(null, "Horario guardado exitosamente");
                    // Recargar la tabla
                    DefaultTableModel model = (DefaultTableModel) view.jTable1.getModel();
                    model.setRowCount(0);
                    List<AgendaDtoAux> agendas = agendaDao.listarAgendasPorMedico(idMedico);
                    for (AgendaDtoAux a : agendas) {
                        Object[] row = {
                            a.getIdMedico(),
                            dateFormat.format(a.getFecha()),
                            a.getHoraInicio(),
                            a.getHoraFinal()
                        };
                        model.addRow(row);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el horario");
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }
}
