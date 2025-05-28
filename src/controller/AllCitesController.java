package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import view.AllCitesView;
import model.dao.CitaDao;
import model.dto.CitaDto;
import java.util.List;
/**
 *
 * @author caes2
 */
public class AllCitesController implements ActionListener {
    
    private AllCitesView view;
    private CitaDao citaDao;
    
    public AllCitesController(AllCitesView view){
        this.view = view;
        this.citaDao = new CitaDao();
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        cargarTodasLasCitas();
    }
    
    private void cargarTodasLasCitas() {
        try {
            List<CitaDto> citas = citaDao.listaCitas();
            
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
                javax.swing.JOptionPane.showMessageDialog(null, "No hay citas registradas en el sistema");
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar las citas: " + e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}