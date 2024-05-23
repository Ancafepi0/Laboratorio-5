/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author UNIVALLE
 */
public class Reserva {
    private int consecutivo;
    private Timestamp fecha_hora;
    private Profesor unProfesor;
    private Equipo_Computo unEquipo;

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }


    public Profesor getUnProfesor() {
        return unProfesor;
    }

    public void setUnProfesor(Profesor unProfesor) {
        this.unProfesor = unProfesor;
    }

    public Equipo_Computo getUnEquipo() {
        return unEquipo;
    }

    public void setUnEquipo(Equipo_Computo unEquipo) {
        this.unEquipo = unEquipo;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }
    
}
