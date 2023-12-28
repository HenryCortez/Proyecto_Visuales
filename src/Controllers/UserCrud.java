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
    //1.- Primer metodo que me permita insertar un usuario
        public void insertUser (String cedula, String nombre, String apellido, String contrasenia)
        {
            try 
            {
                con.conectar();
                String sql= "INSERT INTO USUARIOS (CED_USU,NOM_USU, APE_USU, CON_USU) VALUES (?,?,?,?);";
                PreparedStatement ps = con.getCon().prepareStatement(sql);
                ps.setString(1, cedula);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, contrasenia);
                //Controlamos el numero de respuesta para poder mandar mensjaes de errores
                int insercion = ps.executeUpdate();
                if(insercion > 0)
                {
                    JOptionPane.showMessageDialog(null, "Usuario creado correactemente");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error al crear. Verifica los datos e intenta nuevamente.");
                }
            } catch (SQLException ex) 
                {
                    Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al guardar. Consulta los registros del sistema para obtener más detalles.");
                } finally
                    {
                        con.desconectar();
                    }
        }
        
    //2.- Segundo metodo que me permita atualizar un usuario   
        public void UpdateUser (String cedula, String nombre, String apellido, String contrasenia)
        {
            try 
            {
                con.conectar();
                String sql = "UPDATE USUARIOS SET NOM_USU=?, APE_USU=?, CON_USU=? WHERE ID_USU=?";
                PreparedStatement ps = con.getCon().prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, contrasenia);
                ps.setString(4, cedula);
                //Controlamos el numero de respuesta para poder mandar mensjaes de errores
                int actualizacion = ps.executeUpdate();
                if (actualizacion > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar. Verifica los datos e intenta nuevamente.");
                }
            } catch (SQLException ex) 
            {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al actualizar. Consulta los registros del sistema para obtener más detalles.");
            }finally
            {
                con.desconectar();
            }
        }
    
}