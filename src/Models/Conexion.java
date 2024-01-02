/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author henry
 */
public class Conexion {
    private Connection con;
    
    public Connection conectar(){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://viaduct.proxy.rlwy.net:13119/railway", "root", "gE4h51HaD-5AE1B-CEh1G2gEBF-5HEeb");
        } catch (SQLException e) {
            System.out.println("No se conecto"+e);
            return null;
        }
        return this.con;
    }
    
    public void desconectar(){
        try {
            this.con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getCon() {
        return con;
    }
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conectar();
    }
    
}
