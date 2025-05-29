package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.CiteView;
import javax.swing.WindowConstants;
import model.dao.MedicoDaoAux;
import model.dao.PacienteDaoAux;
import model.dao.RecepcionistaDaoAux;
import model.dao.AgendaDaoAux;
import model.dao.CitaDao;
import model.dto.Medico;
import model.dto.PacienteDtoAux;
import model.dto.RecepcionistaDtoAux;
import model.dto.AgendaDtoAux;
import model.dto.CitaDto;
import java.util.List;
import java.util.Date;
import java.sql.Time;
import javax.swing.DefaultComboBoxModel;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caes2
 */
public class ControllerCite implements ActionListener {
    
    private CiteView view;
    private MedicoDaoAux medicoDao;
    private PacienteDaoAux pacienteDao;
    private RecepcionistaDaoAux recepcionistaDao;
    private AgendaDaoAux agendaDao;
    private CitaDao citaDao;
    private SimpleDateFormat dateFormat;
    private List<AgendaDtoAux> agendasActuales;
    private DefaultTableModel tableModel;
    
    // Variables para mantener el registro de personas comprobadas
    private Medico medicoComprobado;
    private PacienteDtoAux pacienteComprobado;
    private RecepcionistaDtoAux recepcionistaComprobado;
    
    public ControllerCite(CiteView view){
        this.view = view;
        this.medicoDao = new MedicoDaoAux();
        this.pacienteDao = new PacienteDaoAux();
        this.recepcionistaDao = new RecepcionistaDaoAux();
        this.agendaDao = new AgendaDaoAux();
        this.citaDao = new CitaDao();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.agendasActuales = new ArrayList<>();
        
        // Inicializar el modelo de la tabla
        this.tableModel = (DefaultTableModel) this.view.jTable1.getModel();
        this.tableModel.setRowCount(1); // Asegurar que solo hay una fila
        
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.setVisible(true);
        this.view.BT_Buscar.addActionListener(this);        
        this.view.BT_Modificar.addActionListener(this);
        this.view.BT_Guardar.addActionListener(this);
        this.view.checkDoctor.addActionListener(this);        
        this.view.checkPatient.addActionListener(this);
        this.view.checkReceptionist.addActionListener(this);
        this.view.dateField.addActionListener(this);
    }
    
    private void actualizarHorasDisponibles(Date fecha) {
        DefaultComboBoxModel<String> horasModel = new DefaultComboBoxModel<>();
        
        for (AgendaDtoAux agenda : agendasActuales) {
            if (agenda.getFecha().equals(fecha)) {
                Time horaInicio = agenda.getHoraInicio();
                Time horaFinal = agenda.getHoraFinal();
                
                Calendar calInicio = Calendar.getInstance();
                calInicio.setTime(horaInicio);
                
                Calendar calFinal = Calendar.getInstance();
                calFinal.setTime(horaFinal);
                
                while (calInicio.before(calFinal)) {
                    horasModel.addElement(String.format("%02d:00", calInicio.get(Calendar.HOUR_OF_DAY)));
                    calInicio.add(Calendar.HOUR, 1);
                }
            }
        }
        
        this.view.hourField.setModel(horasModel);
    }
    
    private boolean validarCampos() {
        // Verificar que los campos de ID no estén vacíos
        if (this.view.idPacienteField.getText().trim().isEmpty() ||
            this.view.idMedicoField.getText().trim().isEmpty() ||
            this.view.idRecepcionistaField.getText().trim().isEmpty() ||
            this.view.idCitaField.getText().trim().isEmpty() ||
            this.view.motivoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
            return false;
        }

        // Verificar que se haya seleccionado una fecha y hora
        if (this.view.dateField.getSelectedItem() == null || 
            this.view.hourField.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha y hora");
            return false;
        }

        // Verificar que todas las personas hayan sido comprobadas
        if (medicoComprobado == null) {
            JOptionPane.showMessageDialog(null, "Debe comprobar la existencia del médico");
            return false;
        }
        if (pacienteComprobado == null) {
            JOptionPane.showMessageDialog(null, "Debe comprobar la existencia del paciente");
            return false;
        }
        if (recepcionistaComprobado == null) {
            JOptionPane.showMessageDialog(null, "Debe comprobar la existencia del recepcionista");
            return false;
        }

        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            //Comprobar doctor
            if(ae.getSource().equals(this.view.checkDoctor)){
                int idMedico = Integer.parseInt(this.view.idMedicoField.getText());
                Medico medico = medicoDao.read(idMedico);
                
                if(medico != null) {
                    agendasActuales = agendaDao.listarAgendasFuturasPorMedico(idMedico);
                    DefaultComboBoxModel<String> fechasModel = new DefaultComboBoxModel<>();
                    
                    for(AgendaDtoAux agenda : agendasActuales) {
                        fechasModel.addElement(dateFormat.format(agenda.getFecha()));
                    }
                    
                    this.view.dateField.setModel(fechasModel);
                    if (fechasModel.getSize() > 0) {
                        actualizarHorasDisponibles(dateFormat.parse(fechasModel.getElementAt(0)));
                    }
                    
                    // Actualizar tabla y guardar médico comprobado
                    tableModel.setValueAt(medico.getIdMedico(), 0, 0);
                    tableModel.setValueAt(medico.getNombres(), 0, 1);
                    tableModel.setValueAt(medico.getApellidos(), 0, 2);
                    medicoComprobado = medico;
                    
                    JOptionPane.showMessageDialog(null, "Médico encontrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Médico no encontrado");
                    // Limpiar tabla y médico comprobado
                    tableModel.setValueAt(null, 0, 0);
                    tableModel.setValueAt(null, 0, 1);
                    tableModel.setValueAt(null, 0, 2);
                    medicoComprobado = null;
                }
            } 
            //Comprobar paciente
            else if(ae.getSource().equals(this.view.checkPatient)){
                int idPaciente = Integer.parseInt(this.view.idPacienteField.getText());
                PacienteDtoAux paciente = pacienteDao.read(idPaciente);
                
                if(paciente != null) {
                    // Actualizar tabla y guardar paciente comprobado
                    tableModel.setValueAt(paciente.getIdPaciente(), 0, 0);
                    tableModel.setValueAt(paciente.getNombres(), 0, 1);
                    tableModel.setValueAt(paciente.getApellidos(), 0, 2);
                    pacienteComprobado = paciente;
                    
                    JOptionPane.showMessageDialog(null, "Paciente encontrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Paciente no encontrado");
                    // Limpiar tabla y paciente comprobado
                    tableModel.setValueAt(null, 0, 0);
                    tableModel.setValueAt(null, 0, 1);
                    tableModel.setValueAt(null, 0, 2);
                    pacienteComprobado = null;
                }
            }
            //Comprobar recepcionista
            else if(ae.getSource().equals(this.view.checkReceptionist)){
                int idRecepcionista = Integer.parseInt(this.view.idRecepcionistaField.getText());
                RecepcionistaDtoAux recepcionista = recepcionistaDao.read(idRecepcionista);
                
                if(recepcionista != null) {
                    // Actualizar tabla y guardar recepcionista comprobado
                    tableModel.setValueAt(recepcionista.getIdRecepcionista(), 0, 0);
                    tableModel.setValueAt(recepcionista.getNombres(), 0, 1);
                    tableModel.setValueAt("", 0, 2); // Los recepcionistas no tienen apellidos
                    recepcionistaComprobado = recepcionista;
                    
                    JOptionPane.showMessageDialog(null, "Recepcionista encontrado");
                } else {
                    JOptionPane.showMessageDialog(null, "Recepcionista no encontrado");
                    // Limpiar tabla y recepcionista comprobado
                    tableModel.setValueAt(null, 0, 0);
                    tableModel.setValueAt(null, 0, 1);
                    tableModel.setValueAt(null, 0, 2);
                    recepcionistaComprobado = null;
                }
            }
            //Buscar cita
            else if(ae.getSource().equals(this.view.BT_Buscar)){
                int idCita = Integer.parseInt(this.view.idCitaField.getText());
                CitaDto cita = citaDao.read(idCita);
                
                if(cita != null) {
                    this.view.idPacienteField.setText(String.valueOf(cita.getIdPaciente()));
                    this.view.idMedicoField.setText(String.valueOf(cita.getIdMedico()));
                    this.view.idRecepcionistaField.setText(String.valueOf(cita.getIdRecepcionista()));
                    this.view.motivoField.setText(cita.getMotivoConsulta());
                    this.view.stateField.setSelectedItem(cita.getEstadoCita());
                    
                    // Actualizar fechas y horas disponibles
                    agendasActuales = agendaDao.listarAgendasFuturasPorMedico(cita.getIdMedico());
                    DefaultComboBoxModel<String> fechasModel = new DefaultComboBoxModel<>();
                    
                    for(AgendaDtoAux agenda : agendasActuales) {
                        fechasModel.addElement(dateFormat.format(agenda.getFecha()));
                    }
                    
                    this.view.dateField.setModel(fechasModel);
                    this.view.dateField.setSelectedItem(dateFormat.format(cita.getFechaCita()));
                    actualizarHorasDisponibles(cita.getFechaCita());
                    this.view.hourField.setSelectedItem(String.format("%02d:00", cita.getHoraCita().toLocalTime().getHour()));
                    
                    // Comprobar y asignar todas las personas involucradas
                    Medico medico = medicoDao.read(cita.getIdMedico());
                    PacienteDtoAux paciente = pacienteDao.read(cita.getIdPaciente());
                    RecepcionistaDtoAux recepcionista = recepcionistaDao.read(cita.getIdRecepcionista());
                    
                    if (medico != null && paciente != null && recepcionista != null) {
                        // Asignar médico comprobado
                        medicoComprobado = medico;
                        tableModel.setValueAt(medico.getIdMedico(), 0, 0);
                        tableModel.setValueAt(medico.getNombres(), 0, 1);
                        tableModel.setValueAt(medico.getApellidos(), 0, 2);
                        
                        // Asignar paciente comprobado
                        pacienteComprobado = paciente;
                        
                        // Asignar recepcionista comprobado
                        recepcionistaComprobado = recepcionista;
                        
                        JOptionPane.showMessageDialog(null, "Cita encontrada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: No se pudieron encontrar todos los datos de la cita");
                        // Limpiar variables si hay algún error
                        medicoComprobado = null;
                        pacienteComprobado = null;
                        recepcionistaComprobado = null;
                        tableModel.setValueAt(null, 0, 0);
                        tableModel.setValueAt(null, 0, 1);
                        tableModel.setValueAt(null, 0, 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cita no encontrada");
                    // Limpiar variables si no se encuentra la cita
                    medicoComprobado = null;
                    pacienteComprobado = null;
                    recepcionistaComprobado = null;
                    tableModel.setValueAt(null, 0, 0);
                    tableModel.setValueAt(null, 0, 1);
                    tableModel.setValueAt(null, 0, 2);
                }
            } 
            //Guardar cita
            else if(ae.getSource().equals(this.view.BT_Guardar)){
                if (!validarCampos()) {
                    return;
                }
                
                CitaDto cita = new CitaDto(
                    Integer.parseInt(this.view.idCitaField.getText()),
                    dateFormat.parse(this.view.dateField.getSelectedItem().toString()),
                    Time.valueOf(this.view.hourField.getSelectedItem().toString() + ":00"),
                    this.view.stateField.getSelectedItem().toString(),
                    this.view.motivoField.getText(),
                    new Date(),
                    Integer.parseInt(this.view.idPacienteField.getText()),
                    Integer.parseInt(this.view.idMedicoField.getText()),
                    Integer.parseInt(this.view.idRecepcionistaField.getText())
                );
                
                if(citaDao.create(cita)) {
                    JOptionPane.showMessageDialog(null, "Cita creada exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear la cita");
                }
            } 
            //Modificar cita
            else if(ae.getSource().equals(this.view.BT_Modificar)){
                if (!validarCampos()) {
                    return;
                }
                
                CitaDto cita = new CitaDto(
                    Integer.parseInt(this.view.idCitaField.getText()),
                    dateFormat.parse(this.view.dateField.getSelectedItem().toString()),
                    Time.valueOf(this.view.hourField.getSelectedItem().toString() + ":00"),
                    this.view.stateField.getSelectedItem().toString(),
                    this.view.motivoField.getText(),
                    new Date(),
                    Integer.parseInt(this.view.idPacienteField.getText()),
                    Integer.parseInt(this.view.idMedicoField.getText()),
                    Integer.parseInt(this.view.idRecepcionistaField.getText())
                );
                
                if(citaDao.update(cita)) {
                    JOptionPane.showMessageDialog(null, "Cita modificada exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar la cita");
                }
            }
            //Cambio de fecha
            else if(ae.getSource().equals(this.view.dateField)){
                if(this.view.dateField.getSelectedItem() != null) {
                    Date fechaSeleccionada = dateFormat.parse(this.view.dateField.getSelectedItem().toString());
                    actualizarHorasDisponibles(fechaSeleccionada);
                }
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de fecha: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}