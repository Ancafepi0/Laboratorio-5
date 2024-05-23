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
public class ReservaDao {
        //Crear la conexión usando la clase Conexion;
    Conexion miConexion= new Conexion();
    
    Connection con;
    PreparedStatement pst; //para ejecutar las consultas
    ResultSet rs; //para obtener los resultados de la consulta 
    
    //Login del usuario
    
    public List buscar_reservas(int cedula_docente){
        List<Reserva> listaReservas = new ArrayList();
        String query_Busqueda = "SELECT * FROM reserva WHERE profesor="+cedula_docente;
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst= this.con.prepareStatement(query_Busqueda);
            rs= pst.executeQuery();
            
            while(rs.next()){
                Reserva una_reserva= new Reserva();
                
                una_reserva.setConsecutivo(rs.getInt("consecutivo"));
                una_reserva.setFecha_hora(rs.getTimestamp("fecha"));
                Equipo_Computo un_equipo = new Equipo_Computo();
                EquipoDao elequipo = new EquipoDao();
                un_equipo = elequipo.buscarEquipo(rs.getInt("equipo_computo"));
                una_reserva.setUnEquipo(un_equipo);
                ProfesorDao el_profesor = new ProfesorDao();
                Profesor un_profesor = new Profesor();
                un_profesor = el_profesor.buscar_profesor(rs.getInt("profesor"));
                una_reserva.setUnProfesor(un_profesor);
                
                
                listaReservas.add(una_reserva);
            }
            
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "Error al listar los datos: "+e);
        }
        
        return listaReservas;
    }
    
    //Registar persona
    public boolean insertar_reserva(Reserva una_reserva){
        String query= "INSERT INTO reserva( fecha, equipo_computo, profesor)"
                + "VALUES(?,?,?)";
        Timestamp fechaHora = new Timestamp(new Date().getTime());
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst= this.con.prepareStatement(query);
            pst.setTimestamp(1, fechaHora);
            pst.setInt(2,una_reserva.getUnEquipo().getNumero_computo());
            pst.setInt(3, una_reserva.getUnProfesor().getCedula_profe());
            pst.execute();
            
            return true;
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: "+e);
            return false;
        }
        
    }
    
    //Listar personas
    public List listartodas( ){
        List<Reserva> listaTodasReservas = new ArrayList();
        String query = "SELECT * FROM reserva ";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst= this.con.prepareStatement(query);
            rs= pst.executeQuery();

            
            while(rs.next()){
                Reserva la_reserva= new Reserva();
                
                la_reserva.setConsecutivo(rs.getInt("consecutivo"));
                la_reserva.setFecha_hora(rs.getTimestamp("fecha"));
                Equipo_Computo un_equipo = new Equipo_Computo();
                EquipoDao elequipo = new EquipoDao();
                un_equipo = elequipo.buscarEquipo(rs.getInt("equipo_computo"));
                la_reserva.setUnEquipo(un_equipo);
                ProfesorDao el_profesor = new ProfesorDao();
                Profesor un_profesor = new Profesor();
                un_profesor = el_profesor.buscar_profesor(rs.getInt("profesor"));
                la_reserva.setUnProfesor(un_profesor);
                listaTodasReservas.add(la_reserva);
            }
            
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "Error al listar los datos: "+e);
        }
        
        return listaTodasReservas;
    }
    public boolean eliminar_reserva(int num_reserva){
        String query= "DELETE FROM reserva WHERE id="+num_reserva;
        
        try{
            //Obtener la conexión
            this.con= this.miConexion.obtenerConexion();
            //Pasar la consulta
            pst = this.con.prepareStatement(query);
            System.out.println(pst);
            //Ejecutar la consulta
            pst.execute();
            
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos: "+e);
            return false;
        }

    }
    

}
