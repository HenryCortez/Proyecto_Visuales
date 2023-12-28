/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author hamil
 */
public class UserCrud {
    Conexion con = new Conexion();
    
        public void insertUser (String cedula, String nombre, String apellido, String contrasenia){
            try {
                con.conectar();
                String sql= "INSERT INTO USUARIOS (CED_USU,NOM_USU, APE_USU, CON_USU) VALUES (?,?,?,?);";
                PreparedStatement ps = con.getCon().prepareStatement(sql);
                ps.setString(1, cedula);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, contrasenia);
                int insercion = ps.executeUpdate();
                if(insercion > 0)
                {
                    JOptionPane.showMessageDialog(null, "Usuario creado correactemente");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error al crear. Verifica los datos e intenta nuevamente.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al guardar. Consulta los registros del sistema para obtener más detalles.");
            } finally
                {
                    con.desconectar();
                }
        }
}
