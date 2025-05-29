package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import view.PatientHistoryView;
import model.dao.CitaDao;
import model.dao.PacienteDaoAux;
import model.dto.CitaDto;
import model.dto.PacienteDtoAux;
import java.util.List;

/**
 *
 * @author caes2
 */
public class PatientHistoryController implements ActionListener {
    
    private PatientHistoryView view;
    private CitaDao citaDao;
    private PacienteDaoAux pacienteDao;
    
    public PatientHistoryController(PatientHistoryView view){
        this.view = view;
        this.view.setTitle("Historial de un paciente");
        this.citaDao = new CitaDao();
        this.pacienteDao = new PacienteDaoAux();
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        this.view.searchButton.addActionListener(this);        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(this.view.searchButton)){
            try {
                String idPacienteStr = this.view.idPacienteField.getText().trim();
                if (idPacienteStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de paciente");
                    return;
                }
                
                int idPaciente = Integer.parseInt(idPacienteStr);
                
                // Verificar si el paciente existe
                PacienteDtoAux paciente = pacienteDao.read(idPaciente);
                if (paciente == null) {
                    JOptionPane.showMessageDialog(null, "El paciente con ID " + idPaciente + " no existe");
                    return;
                }
                
                List<CitaDto> citas = citaDao.buscarCitasPorPaciente(idPaciente);
                
                DefaultTableModel modelo = (DefaultTableModel) this.view.citaInfTabla.getModel();
                modelo.setRowCount(0); // Limpiar tabla
                
                for (CitaDto cita : citas) {
                    Object[] fila = {
                        cita.getIdCita(),
                        cita.getIdPaciente(),
                        cita.getIdMedico(),
                        cita.getIdRecepcionista(),
                        cita.getFechaCita(),
                        cita.getHoraCita(),
                        cita.getEstadoCita(),
                        cita.getMotivoConsulta(),
                        cita.getFechaAsignacion()
                    };
                    modelo.addRow(fila);
                }
                
                if (citas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron citas para el paciente " + paciente.getNombres() + " " + paciente.getApellidos());
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de paciente válido");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al buscar las citas: " + e.getMessage());
            }
        }
    }
}