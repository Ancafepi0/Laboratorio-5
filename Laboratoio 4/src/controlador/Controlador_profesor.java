
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Profesor;
import modelo.ProfesorDao;
import modelo.Reserva;
import modelo.ReservaDao;
import vista.Vista_Profesor;


public class Controlador_profesor implements ActionListener{
    private Vista_Profesor vista_profesor;
    private Profesor unprofesor;
    private ProfesorDao unprofesordao;
    private ReservaDao reservas;
    private List<Reserva> coleccionReservas = new ArrayList<>();
    private DefaultTableModel plantilla;
    public Controlador_profesor() {
        vista_profesor = new Vista_Profesor();
        vista_profesor.setVisible(true);    
        vista_profesor.jBT_AGREGAR_PROFESOR.addActionListener(this);
        vista_profesor.jBT_BUSCAR_RESEVA.addActionListener(this);
        vista_profesor.jBregresae.addActionListener(this);
        unprofesordao = new ProfesorDao();
        reservas = new ReservaDao();
        plantilla = (DefaultTableModel) this.vista_profesor.getjTable1().getModel();
    }
    public void limpiar(){
        vista_profesor.jTF_CEDULA.setText("");
        vista_profesor.jTF_NOMBRE.setText("");
        vista_profesor.jTF_APELLIDO.setText("");
        vista_profesor.jTF_PROFESION.setText("");
        vista_profesor.Jtf_cedula.setText("");
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista_profesor.jBT_AGREGAR_PROFESOR){
            
            try{
                unprofesor = new Profesor();
                unprofesor.setCedula_profe(Integer.parseInt(this.vista_profesor.jTF_CEDULA.getText()));
                unprofesor.setNombre_profe(this.vista_profesor.jTF_NOMBRE.getText());
                unprofesor.setApellido_profe(this.vista_profesor.jTF_APELLIDO.getText());
                unprofesor.setProfesion(this.vista_profesor.jTF_PROFESION.getText());
                
                //se valida que los campos tipo texto no esten vacíos
                if(!this.unprofesor.getNombre_profe().equals("") 
                        && !this.unprofesor.getApellido_profe().equals("")
                        && !this.unprofesor.getProfesion().equals("") 
                        && this.unprofesor.getCedula_profe() != 0){
                    //se ejecuta la inserción en la base de datos
                    if(this.unprofesordao.insertarprofesor(unprofesor)){
                        JOptionPane.showMessageDialog(this.vista_profesor, "Datos ingresados con éxito!!!");
                        limpiar();                        
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vista_profesor,"Todos los campos son obligatorios\nY ninguno debe ir en blanco");
                }                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vista_profesor,"Todos los campos son obligatorios\nY deben ser en formato numérico");
            }
        }
        if (e.getSource() == this.vista_profesor.jBT_BUSCAR_RESEVA){
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            plantilla.setRowCount(0);

            try{
                coleccionReservas= reservas.buscar_reservas(Integer.parseInt(this.vista_profesor.Jtf_cedula.getText()));     
            } catch(NullPointerException as){
                 JOptionPane.showMessageDialog(null, "No tiene reserva en la base de datos");
                 
               
            }
            if (coleccionReservas.size() == 0){
                JOptionPane.showMessageDialog(null, "No tiene reserva con esa cedula");
            }else{
               for (int i = 0; i < coleccionReservas.size(); i++) {
                String fechaHoraFormateada = formatter.format(coleccionReservas.get(i).getFecha_hora());
                plantilla.addRow(new Object[]{coleccionReservas.get(i).getConsecutivo(),
                fechaHoraFormateada,
                coleccionReservas.get(i).getUnEquipo().getNumero_computo(),
                coleccionReservas.get(i).getUnProfesor().getCedula_profe()});
                
                } 
            }
       
        }
        if (e.getSource() == this.vista_profesor.jBregresae){
            vista_profesor.setVisible(false);
            limpiar();
            plantilla.setRowCount(0);
        }
        
    }

    public Vista_Profesor getVista_profesor() {
        return vista_profesor;
    }

    public void setVista_profesor(Vista_Profesor vista_profesor) {
        this.vista_profesor = vista_profesor;
    }

    public Profesor getUnprofesor() {
        return unprofesor;
    }

    public void setUnprofesor(Profesor unprofesor) {
        this.unprofesor = unprofesor;
    }

    public ProfesorDao getUnprofesordao() {
        return unprofesordao;
    }

    public void setUnprofesordao(ProfesorDao unprofesordao) {
        this.unprofesordao = unprofesordao;
    }

    public ReservaDao getReservas() {
        return reservas;
    }

    public void setReservas(ReservaDao reservas) {
        this.reservas = reservas;
    }

    
}
