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
public class EquipoDao {
    Conexion miConexion= new Conexion();
    
    Connection con;
    PreparedStatement pst; //para ejecutar las consultas
    ResultSet rs; //para obtener los resultados de la consulta
    
    //Variables para enviar datos entre interfaces
    public static int inventario =0;
    public static String marca="";
    public static int capacidad_disco=0;

    
    //Buscar del usuario
    public Equipo_Computo buscarEquipo(int numero_inventario_old ){
        String query = "SELECT * FROM computo WHERE numero_inventario = ?";
        Equipo_Computo equipo= new Equipo_Computo();
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            
            //se pasan los parametro ingresados por el usuario
            pst.setInt(1, numero_inventario_old);
            System.out.println("contenido del query:\n"+pst);
            rs= pst.executeQuery();
            
            if(rs.next()){
                equipo.setNumero_computo(rs.getInt("numero_inventario"));
                inventario= equipo.getNumero_computo();
                equipo.setCapacidad_disco(rs.getInt("capacidad_disco"));
                capacidad_disco= equipo.getCapacidad_disco();
                equipo.setMarca(rs.getString("marca"));
                marca= equipo.getMarca();

            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del equipo de computo: "+e);
        }
        
        return equipo;
    }
    
    //Registar persona
    public boolean insertarequipo(Equipo_Computo un_equipo){
        String query= "INSERT INTO computo(numero_inventario , marca, capacidad_disco)"
                + "VALUES(?,?,?)";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst= this.con.prepareStatement(query);
            pst.setInt(1, un_equipo.getNumero_computo());
            pst.setString(2, un_equipo.getMarca());
            pst.setInt(3, un_equipo.getCapacidad_disco());
            pst.execute();
            
            return true;
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: "+e);
            return false;
        }
        
    }
    public List listartodas( ){
        List<Equipo_Computo> listaCompu = new ArrayList();
        String query = "SELECT * FROM computo ";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst= this.con.prepareStatement(query);
            rs= pst.executeQuery();

            
            while(rs.next()){
                Equipo_Computo el_computo= new Equipo_Computo();
                
                el_computo.setNumero_computo(rs.getInt("numero_inventario"));
                el_computo.setMarca(rs.getString("marca"));
                el_computo.setCapacidad_disco(rs.getInt("capacidad_disco"));
                listaCompu.add(el_computo);

            }
            
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null, "Error al listar los datos: "+e);
        }
        
        return listaCompu;
    }
    
}
