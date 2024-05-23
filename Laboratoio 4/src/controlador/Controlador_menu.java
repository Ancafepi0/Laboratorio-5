
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo_Computo;
import modelo.Profesor;
import vista.Vista_Menu;


public class Controlador_menu implements ActionListener{
    private Controlador_equipo control_equipo;
    private Controlador_profesor control_profesor;
    private Controlador_reserva control_reserva;
    private Vista_Menu menu_vista;
    private List <Profesor> coleccion_profesor = new ArrayList <>();
    private List <Equipo_Computo> coleccion_computo = new ArrayList <>();
    public Controlador_menu() {
        menu_vista= new Vista_Menu();
        control_equipo = new Controlador_equipo();
        control_profesor = new Controlador_profesor();
        control_reserva= new Controlador_reserva();
        menu_vista.setVisible(true);
        control_equipo.getVista_equipo().setVisible(false);
        control_profesor.getVista_profesor().setVisible(false);
        control_reserva.getVista_reserva().setVisible(false);
        menu_vista.jbt_equipo.addActionListener(this);
        menu_vista.jbt_profesor.addActionListener(this);
        menu_vista.jbt_reservas.addActionListener(this);
    }
            
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menu_vista.jbt_reservas){
            control_reserva.getVista_reserva().setVisible(true);
            control_reserva.getVista_reserva().jcbProfesor.removeAllItems();
            control_reserva.getVista_reserva().jcbEquipo.removeAllItems();

            coleccion_profesor = this.control_profesor.getUnprofesordao().listartodas();
            for (int i = 0; i < coleccion_profesor.size(); i++) {
                control_reserva.getVista_reserva().jcbProfesor.addItem(Integer.toString(coleccion_profesor.get(i).getCedula_profe()));
            }
            coleccion_computo = this.control_equipo.getEquipodao().listartodas();
            for (int i = 0; i < coleccion_computo.size(); i++) {
                control_reserva.getVista_reserva().jcbEquipo.addItem(Integer.toString(coleccion_computo.get(i).getNumero_computo()));
            }
            
        }
        if (e.getSource() == this.menu_vista.jbt_equipo){
            control_equipo.getVista_equipo().setVisible(true);
           
        }
        if (e.getSource() == this.menu_vista.jbt_profesor){
            control_profesor.getVista_profesor().setVisible(true);         
        }
        
    }

    public Controlador_equipo getControl_equipo() {
        return control_equipo;
    }

    public void setControl_equipo(Controlador_equipo control_equipo) {
        this.control_equipo = control_equipo;
    }

    public Controlador_profesor getControl_profesor() {
        return control_profesor;
    }

    public void setControl_profesor(Controlador_profesor control_profesor) {
        this.control_profesor = control_profesor;
    }

    public Controlador_reserva getControl_reserva() {
        return control_reserva;
    }

    public void setControl_reserva(Controlador_reserva control_reserva) {
        this.control_reserva = control_reserva;
    }

    public Vista_Menu getMenu_vista() {
        return menu_vista;
    }

    public void setMenu_vista(Vista_Menu menu_vista) {
        this.menu_vista = menu_vista;
    }
    
}
