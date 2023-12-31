package Controllers;

import Models.Conexion;
import Models.UserModel;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class UserControl {

    Conexion con = new Conexion();
    String LLAVEENCRIP = "app_visual";

    public void insertAdmin(String ced, String nom, String ape, String passw, String tipo) {
        try {
            con.conectar();
            String sql = "INSERT INTO USUARIOS(CED_USU, NOM_USU, APE_USU, CON_USU, TIP_USU) "
                    + "VALUES(?, ?, ?, ?, ?)";
            String passwencryp = encriptar(passw);
            
            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, ced);
            ps.setString(2, nom);
            ps.setString(3, ape);
            ps.setString(4, passwencryp);
            ps.setString(5, tipo);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE GUARDO");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insertWorker(String ced, String nom, String ape, String tipo) {
        try {
            con.conectar();
            String sql = "INSERT INTO USUARIOS(CED_USU, NOM_USU, APE_USU, CON_USU, TIP_USU) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, ced);
            ps.setString(2, nom);
            ps.setString(3, ape);
            ps.setString(5, tipo);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE GUARDO");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<UserModel> getID() {

        this.con.conectar();
        ArrayList<UserModel> listUsers = new ArrayList();
        String sql = "SELECT ID_USU, CED_USU FROM USUARIOS ORDER BY ID_USU ASC";
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID_USU");
                String ced = rs.getString("CED_USU");

                UserModel user = new UserModel(ced, id);
                listUsers.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return listUsers;
        }

    }
    
    public UserModel getUser(String id_user) {
        this.con.conectar();
        String sql = "SELECT CED_USU, NOM_USU, APE_USU, SUE_PAG_USU FROM USUARIOS "
                + "WHERE ID_USU ="+id_user+";";
        UserModel user =new UserModel();
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                user.setCedula(rs.getString("CED_USU"));
                user.setNombre( rs.getString("NOM_USU"));
                user.setApellido(rs.getString("APE_USU"));
                user.setSueldo_actual(rs.getDouble("SUE_PAG_USU"));  
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return user;
        }

    }
    
    public int getStateUser(String id_user){
    this.con.conectar();
    int state = 0;
    String sql = "SELECT EST_USU FROM USUARIOS WHERE CED_USU =" + id_user + ";";
    
    try {
        Statement psd = con.getCon().createStatement();
        ResultSet rs = psd.executeQuery(sql);

        while (rs.next()) {
            state = rs.getInt(1);
        }
        
        System.out.println(state);
        
        if (state == 3) {
            System.out.println("El trabajador ya no está disponible");
        }

    } catch (SQLException e) {
    } finally {
        return state;
    }
}

    
    public void updateUser (String cedula, String nombre, String apellido, String contrasenia)
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
                int actualizacion = ps.executeUpdate();
                if (actualizacion > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar. Verifica los datos e intenta nuevamente.");
                }
            } catch (SQLException ex) 
            {
                Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al actualizar. Consulta los registros del sistema para obtener más detalles.");
            }finally
            {
                con.desconectar();
            }
        }
    
    public void deleteUser (String cedula)
        {
            try 
            {
                con.conectar();
                String sql = "UPDATE USUARIOS SET EST_USU=3 WHERE ID_USU=?";
                PreparedStatement ps = con.getCon().prepareStatement(sql);
                ps.setString(1, cedula);
                int eliminacion = ps.executeUpdate();
                if (eliminacion > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar. Verifica los datos e intenta nuevamente.");
                }
            } catch (SQLException ex) 
            {
                Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al eliminar. Consulta los registros del sistema para obtener más detalles.");
            }finally
            {
                con.desconectar();
            }
        }
    
     
    public SecretKeySpec crearClave (String llave)
    {
        try 
        {
            byte[] cadena= llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena,16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR LA LLAVE CENTRAR DE LA CONTRASEÑA" +e);
            return null;
        }
    }

    public String encriptar(String textoAEncriptar)
    {
        try 
        {
            SecretKeySpec secretKeySpec = crearClave(LLAVEENCRIP);
            Cipher cypher = Cipher.getInstance("AES");
            cypher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            
            byte[] cadena = textoAEncriptar.getBytes("UTF-8");
            byte[] textoEncriptado = cypher.doFinal(cadena);
            return Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception e) {
            System.out.println("ERROR AL ENCRIPTAR LA CONTRASEÑA" +e);
            return "";
        }
    }
    
    public String desencriptar(String textoADesencriptar)
    {
        try 
        {
            SecretKeySpec secretKeySpec = crearClave(LLAVEENCRIP);
            Cipher cypher = Cipher.getInstance("AES");
            cypher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            
            byte[] cadena = Base64.getDecoder().decode(textoADesencriptar);
            byte[] desencriptacion = cypher.doFinal(cadena);
            String cadena_desencriptada = new String(desencriptacion);
            return cadena_desencriptada;
        } catch (Exception e) {
            System.out.println("ERROR AL ENCRIPTAR LA CONTRASEÑA" +e);
            return "";
        }
    }
}
