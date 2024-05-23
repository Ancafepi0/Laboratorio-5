/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author UNIVALLE
 */
public class ProfesorDao {
//Crear la conexión usando la clase Conexion;
    Conexion miConexion= new Conexion();
    
    Connection con;
    PreparedStatement pst; //para ejecutar las consultas
    ResultSet rs; //para obtener los resultados de la consulta
    
    //Variables para enviar datos entre interfaces
    public static int cedula_profesor=0;
    public static String nombres_profesor="";
    public static String profesion ="";
    public static String apellidos_profesor="";

    
    //Login del usuario
    public Profesor buscar_profesor(int numero_cedula){
        String query = "SELECT * FROM profesor WHERE numero_cedula = ?";
        Profesor profesor= new Profesor();
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            
            //se pasan los parametro ingresados por el usuario
            pst.setInt(1, numero_cedula);
            System.out.println("contenido del query:\n"+pst);
            rs= pst.executeQuery();
            
            if(rs.next()){
                profesor.setCedula_profe(rs.getInt("numero_cedula"));
                cedula_profesor= profesor.getCedula_profe();
                profesor.setNombre_profe(rs.getString("nombres"));
                nombres_profesor= profesor.getNombre_profe();
                profesor.setApellido_profe(rs.getString("apellidos"));
                apellidos_profesor= profesor.getApellido_profe();
                profesor.setProfesion(rs.getString("profesion"));
                apellidos_profesor= profesor.getProfesion();
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del profesor: "+e);
        }
        
        return profesor;
    }
    
    //Registar persona
    public boolean insertarprofesor(Profesor unprofesor){
        String query= "INSERT INTO profesor(numero_cedula, nombres, apellidos, profesion)"
                + "VALUES(?,?,?,?)";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst= this.con.prepareStatement(query);
            pst.setInt(1, unprofesor.getCedula_profe());
            pst.setString(2, unprofesor.getNombre_profe());
            pst.setString(3, unprofesor.getApellido_profe());
            pst.setString(4, unprofesor.getProfesion());

            pst.execute();
            
            return true;
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: "+e);
            return false;
        }
        
    }
    public List listartodas( ){
        List<Profesor> listaProfesor = new ArrayList();
        String query = "SELECT * FROM profesor ";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst= this.con.prepareStatement(query);
            rs= pst.executeQuery();

            
            while(rs.next()){
                Profesor el_profesor= new Profesor();
                
                el_profesor.setCedula_profe(rs.getInt("numero_cedula"));
                el_profesor.setNombre_profe(rs.getString("nombres"));
                el_profesor.setApellido_profe(rs.getString("apellidos"));
                el_profesor.setProfesion(rs.getString("profesion"));
                listaProfesor.add(el_profesor);

            }
            
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "Error al listar los datos: "+e);
        }
        
        return listaProfesor;
    }

}
