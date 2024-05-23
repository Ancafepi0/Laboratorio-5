/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author UNIVALLE
 */
public class Conexion {
    
    private String bdlaboratorio="bdlaboratorio";
    private String user="root";
    private String password="";
    private String url="jdbc:mysql://localhost:3306/"+bdlaboratorio;
    
    Connection conexion= null;
    
    public Connection obtenerConexion(){
        try{
            //Obtener el valor del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener conexión DB
            conexion = DriverManager.getConnection(this.url, this.user, this.password);
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido una excepcion SQLException: "+e.getMessage());
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido una excepcion ClassNotFoundException: "+e.getMessage());
        }
        return conexion;
    }
}
