
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.EquipoDao;
import modelo.ProfesorDao;
import modelo.Reserva;
import modelo.ReservaDao;
import vista.Vista_Reserva;

/**
 *
 * @author UNIVALLE
 */
public class Controlador_reserva implements ActionListener{
    private ReservaDao reservadao;
    private Reserva una_reserva;
    private Vista_Reserva vista_reserva;
    private ProfesorDao profeDao;
    private EquipoDao equipoDao;
    private List <Reserva> coleccion_reserva = new ArrayList <>();
    private DefaultTableModel plantilla;

    public Controlador_reserva() {
        vista_reserva = new Vista_Reserva();
        vista_reserva.setVisible(true);
        vista_reserva.jBT_RESERVAR.addActionListener(this);
        vista_reserva.jBregresae.addActionListener(this);
        vista_reserva.jbt_listar.addActionListener(this);
        plantilla = (DefaultTableModel) this.vista_reserva.getjTable1().getModel();
        reservadao = new ReservaDao();
        profeDao= new ProfesorDao();
        equipoDao = new EquipoDao();
    }
    public void limpiar(){
        this.vista_reserva.jcbProfesor.setSelectedIndex(1);
        this.vista_reserva.jcbEquipo.setSelectedIndex(1);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista_reserva.jBT_RESERVAR){
            try{
                una_reserva = new Reserva();
                String equipo_seleccionado = (String) this.vista_reserva.jcbEquipo.getSelectedItem();
                una_reserva.setUnEquipo(this.equipoDao.buscarEquipo(Integer.parseInt(equipo_seleccionado)));
                String profesor_seleccionado = (String)this.vista_reserva.jcbProfesor.getSelectedItem();
                una_reserva.setUnProfesor(this.profeDao.buscar_profesor(Integer.parseInt(profesor_seleccionado)));

                this.reservadao.insertar_reserva(una_reserva);
                JOptionPane.showMessageDialog(this.vista_reserva, "Datos ingresados con éxito!!!");
                limpiar();                        
                    
              
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vista_reserva,"Todos los campos son obligatorios\nY deben ser en formato numérico");
            }
        }
        if (e.getSource() == this.vista_reserva.jbt_listar){
            this.coleccion_reserva=this.reservadao.listartodas();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            plantilla.setRowCount(0);
            for (int i = 0; i < coleccion_reserva.size(); i++) {
                String fechaHoraFormateada = formatter.format(coleccion_reserva.get(i).getFecha_hora());
                plantilla.addRow(new Object[]{coleccion_reserva.get(i).getConsecutivo(),
                fechaHoraFormateada,
                coleccion_reserva.get(i).getUnEquipo().getNumero_computo(),
                coleccion_reserva.get(i).getUnProfesor().getCedula_profe()});
                
                }
        }
        if (e.getSource() == this.vista_reserva.jBregresae){
            this.vista_reserva.setVisible(false);
            limpiar ();
            
        }
    }

    public ReservaDao getReservadao() {
        return reservadao;
    }

    public void setReservadao(ReservaDao reservadao) {
        this.reservadao = reservadao;
    }

    public Reserva getUna_reserva() {
        return una_reserva;
    }

    public void setUna_reserva(Reserva una_reserva) {
        this.una_reserva = una_reserva;
    }

    public Vista_Reserva getVista_reserva() {
        return vista_reserva;
    }

    public void setVista_reserva(Vista_Reserva vista_reserva) {
        this.vista_reserva = vista_reserva;
    }
    
}
