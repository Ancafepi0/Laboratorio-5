/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.EquipoDao;
import modelo.Equipo_Computo;
import modelo.Profesor;
import vista.Vista_Equipo_Computo;

/**
 *
 * @author UNIVALLE
 */
public class Controlador_equipo implements ActionListener{
    private EquipoDao equipodao;
    private Equipo_Computo equipo;
    private  Vista_Equipo_Computo vista_equipo;

    public Controlador_equipo() {
        vista_equipo= new Vista_Equipo_Computo();
        vista_equipo.setVisible(true);
        vista_equipo.jBT_AGREGAR_EQUIPO.addActionListener(this);
        vista_equipo.jBregresae.addActionListener(this);
        equipodao = new EquipoDao();
    }
    public void limpiar(){
        this.vista_equipo.jTF_CAPACIDAD_DISCO.setText("");
        this.vista_equipo.jTF_MARCA.setText("");
        this.vista_equipo.jTF_NUMERO_EQUIPO.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista_equipo.jBT_AGREGAR_EQUIPO){
            try{
                equipo = new Equipo_Computo();
                equipo.setNumero_computo(Integer.parseInt(this.vista_equipo.jTF_NUMERO_EQUIPO.getText()));
                equipo.setMarca(this.vista_equipo.jTF_MARCA.getText());
                equipo.setCapacidad_disco(Integer.parseInt(this.vista_equipo.jTF_CAPACIDAD_DISCO.getText()));
                
                //se valida que los campos tipo texto no esten vacíos
                if(!this.equipo.getMarca().equals("") 
                        && this.equipo.getCapacidad_disco()!= 0
                        && this.equipo.getNumero_computo()!= 0){
                    //se ejecuta la inserción en la base de datos
                    if(this.equipodao.insertarequipo(equipo)){
                        JOptionPane.showMessageDialog(this.vista_equipo, "Datos ingresados con éxito!!!");
                        limpiar();
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vista_equipo,"Todos los campos son obligatorios\nY ninguno debe ir en blanco");
                }                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vista_equipo,"Todos los campos son obligatorios\nY deben ser en formato numérico");
            }
        }
        if (e.getSource() == this.vista_equipo.jBregresae){
            this.vista_equipo.setVisible(false);
            limpiar();
        }
    }
    public EquipoDao getEquipodao() {
        return equipodao;
    }

    public void setEquipodao(EquipoDao equipodao) {
        this.equipodao = equipodao;
    }

    public Equipo_Computo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo_Computo equipo) {
        this.equipo = equipo;
    }

    public Vista_Equipo_Computo getVista_equipo() {
        return vista_equipo;
    }

    public void setVista_equipo(Vista_Equipo_Computo vista_equipo) {
        this.vista_equipo = vista_equipo;
    }

    
    
}
