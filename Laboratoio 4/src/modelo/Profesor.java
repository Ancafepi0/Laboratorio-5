/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UNIVALLE
 */
public class Profesor {
    private int cedula_profe;
    private String nombre_profe;
    private String apellido_profe;
    private String profesion;
    
    
    public int getCedula_profe() {
        return cedula_profe;
    }

    public void setCedula_profe(int cedula_profe) {
        this.cedula_profe = cedula_profe;
    }

    public String getNombre_profe() {
        return nombre_profe;
    }

    public void setNombre_profe(String nombre_profe) {
        this.nombre_profe = nombre_profe;
    }

    public String getApellido_profe() {
        return apellido_profe;
    }

    public void setApellido_profe(String apellido_profe) {
        this.apellido_profe = apellido_profe;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
}
