/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import view.inf_Medico;
import model.dao.MedicoDaoAux;
import model.dto.Medico;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import view.Agenda_Medico;
import controller.ControllerAgenda_Medico;

/**
 *
 * @author caes2
 */
public class ControllerMedico implements ActionListener {
    
    private inf_Medico view;
    private MedicoDaoAux medicoDao;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat;
    
    public ControllerMedico(inf_Medico view) {
        this.view = view;
        this.medicoDao = new MedicoDaoAux();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        // Inicializar el modelo de la tabla
        this.tableModel = (DefaultTableModel) this.view.tabla_inf_medico.getModel();
        
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        
        // Agregar listeners a los botones
        this.view.BT_Guardar.addActionListener(this);
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Buscar.addActionListener(this);
        this.view.BT_Agenda.addActionListener(this);
        
        // Cargar la lista de médicos al iniciar
        cargarMedicos();
    }
    
    private void cargarMedicos() {
        try {
            List<Medico> medicos = medicoDao.listarMedicos();
            tableModel.setRowCount(0); // Limpiar tabla
            
            for (Medico medico : medicos) {
                Object[] fila = {
                    medico.getIdMedico(),
                    medico.getNombres(),
                    medico.getApellidos(),
                    medico.getEspecialidad()
                };
                tableModel.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los médicos: " + e.getMessage());
        }
    }
    
    private boolean validarCampos() {
        if (view.idMedico.getText().trim().isEmpty() ||
            view.NombreMedico.getText().trim().isEmpty() ||
            view.pApellido_Medico.getText().trim().isEmpty() ||
            view.sApellido_Medico.getText().trim().isEmpty() ||
            view.telefono.getText().trim().isEmpty() ||
            view.especialidad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            return false;
        }
        return true;
    }
    
    private Medico obtenerMedicoDeCampos() throws ParseException {
        return new Medico(
            Integer.parseInt(view.idMedico.getText()),
            view.NombreMedico.getText(),
            view.pApellido_Medico.getText() + " " + view.sApellido_Medico.getText(),
            dateFormat.parse("1990-01-01"), // Fecha de nacimiento por defecto
            Integer.parseInt(view.telefono.getText()),
            "", // Dirección por defecto
            "", // Email por defecto
            new Date(), // Fecha de ingreso actual
            view.especialidad.getSelectedItem().toString()
        );
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource().equals(view.BT_Guardar)) {
                if (!validarCampos()) {
                    return;
                }
                
                Medico medico = obtenerMedicoDeCampos();
                
                if (medicoDao.create(medico)) {
                    JOptionPane.showMessageDialog(null, "Médico registrado exitosamente");
                    cargarMedicos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el médico");
                }
            }
            else if (ae.getSource().equals(view.BT_Modificar)) {
                if (!validarCampos()) {
                    return;
                }
                
                Medico medico = obtenerMedicoDeCampos();
                
                if (medicoDao.update(medico)) {
                    JOptionPane.showMessageDialog(null, "Médico modificado exitosamente");
                    cargarMedicos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar el médico");
                }
            }
            else if (ae.getSource().equals(view.BT_Buscar)) {
                String idMedicoStr = view.idMedico.getText().trim();
                if (idMedicoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de médico");
                    return;
                }
                
                int idMedico = Integer.parseInt(idMedicoStr);
                Medico medico = medicoDao.read(idMedico);
                
                if (medico != null) {
                    // Separar apellidos
                    String[] apellidos = medico.getApellidos().split(" ", 2);
                    String primerApellido = apellidos[0];
                    String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";
                    
                    view.NombreMedico.setText(medico.getNombres());
                    view.pApellido_Medico.setText(primerApellido);
                    view.sApellido_Medico.setText(segundoApellido);
                    view.telefono.setText(String.valueOf(medico.getTelefono()));
                    view.especialidad.setSelectedItem(medico.getEspecialidad());
                    
                    JOptionPane.showMessageDialog(null, "Médico encontrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Médico no encontrado");
                    limpiarCampos();
                }
            }
            else if (ae.getSource().equals(view.BT_Agenda)) {
                String idMedicoStr = view.idMedico.getText().trim();
                if (idMedicoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de médico");
                    return;
                }
                
                int idMedico = Integer.parseInt(idMedicoStr);
                Medico medico = medicoDao.read(idMedico);
                
                if (medico != null) {
                    Agenda_Medico agendaView = new Agenda_Medico();
                    ControllerAgenda_Medico _ = new ControllerAgenda_Medico(agendaView, idMedico);
                } else {
                    JOptionPane.showMessageDialog(null, "Médico no encontrado");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos para ID y teléfono");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de fecha: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    private void limpiarCampos() {
        view.idMedico.setText("");
        view.NombreMedico.setText("");
        view.pApellido_Medico.setText("");
        view.sApellido_Medico.setText("");
        view.telefono.setText("");
        view.especialidad.setSelectedIndex(0);
    }
}
