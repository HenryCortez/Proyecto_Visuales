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

            PreparedStatement ps = con.getCon().prepareStatement(sql.toLowerCase());
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
                    + "VALUES(?, ?, ?, ?, ?)".toLowerCase();

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
        String sql = "SELECT ID_USU, CED_USU FROM USUARIOS ORDER BY ID_USU ASC".toLowerCase();
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
            System.out.println("ERROR GET ID" + e);
        } finally {
            con.desconectar();
            return listUsers;
        }

    }

    public String getAdminSecret(String cedula) {
        this.con.conectar();
        String sql = "SELECT CON_USU FROM USUARIOS WHERE CED_USU = ".toLowerCase() + cedula + ";";
        String adminCredentials = "";

        try {
            PreparedStatement psd = con.getCon().prepareStatement(sql);
            ResultSet rs = psd.executeQuery();
            if (rs.next()) {
                adminCredentials = rs.getString("CON_USU".toLowerCase());
            }
            adminCredentials = this.desencriptar(adminCredentials);
            if (adminCredentials.equals("")) {
                con.desconectar();
                return "-------------------------------------";
            } else {
                con.desconectar();
                return adminCredentials;
            }

        } catch (SQLException e) {
            // Manejar o registrar la excepción adecuadamente

            System.out.println("Error al obtener las credenciales del usuario: " + e.getMessage());
            con.desconectar();
            return null;
        }
    }

    public String getAdminName(String cedula) {
        this.con.conectar();
        String sql = "SELECT NOM_USU FROM USUARIOS WHERE CED_USU = ".toLowerCase() + cedula + ";";
        String name = "";

        try {
            PreparedStatement psd = con.getCon().prepareStatement(sql);
            ResultSet rs = psd.executeQuery();
            if (rs.next()) {
                name = rs.getString("NOM_USU".toLowerCase());
            }
            con.desconectar();
            return name;
        } catch (SQLException e) {
            // Manejar o registrar la excepción adecuadamente
            con.desconectar();
            System.out.println("Error al obtener el nombre del Administrador: " + e.getMessage());
            return name;
        }
    }

    public UserModel getUser(String id_user) {
        String sql = "SELECT CED_USU, NOM_USU, APE_USU, SUE_PAG_USU FROM USUARIOS WHERE ID_USU = ?;";
        UserModel user = new UserModel();

        this.con.conectar();
        try (PreparedStatement psd = con.getCon().prepareStatement(sql.toLowerCase())) {
            psd.setString(1, id_user); // Uso de PreparedStatement para prevenir inyección SQL

            try (ResultSet rs = psd.executeQuery()) {
                while (rs.next()) {
                    user.setCedula(rs.getString("CED_USU"));
                    user.setNombre(rs.getString("NOM_USU"));
                    user.setApellido(rs.getString("APE_USU"));
                    user.setSueldo_actual(rs.getDouble("SUE_PAG_USU"));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR GET elgetuser" + e);
        } finally {
            con.desconectar();
        }

        return user;
    }

    public int getStateUser(String id_user) {
        this.con.conectar();
        int state = 0;
        String sql = "SELECT EST_USU FROM USUARIOS WHERE CED_USU =".toLowerCase() + id_user + ";";

        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                state = rs.getInt(1);
            }

            if (state == 3) {
                System.out.println("El trabajador ya no está disponible");
            }

        } catch (SQLException e) {
        } finally {
            con.desconectar();
            return state;
        }
    }

    public void updateUser(String cedula, String nombre, String apellido, String contrasenia) {
        try {
            con.conectar();
            String sql;
            PreparedStatement ps;
            if (contrasenia.equals("")) {
                sql = "UPDATE USUARIOS SET NOM_USU=?, APE_USU=? WHERE CED_USU=?".toLowerCase();
                ps = con.getCon().prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, cedula);
            } else {
                sql = "UPDATE USUARIOS SET NOM_USU=?, APE_USU=?, CON_USU=? WHERE CED_USU=?".toLowerCase();
                ps = con.getCon().prepareStatement(sql);
                String passwencryp = encriptar(contrasenia);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, passwencryp);
                ps.setString(4, cedula);
            }

            int actualizacion = ps.executeUpdate();
            if (actualizacion > 0) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar. Verifica los datos e intenta nuevamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al actualizar. Consulta los registros del sistema para obtener más detalles." + ex);
        } finally {
            con.desconectar();
        }
    }

    public void deleteUser(String cedula) {
        try {
            con.conectar();
            String sql = "UPDATE USUARIOS SET EST_USU=3 WHERE CED_USU=?".toLowerCase();
            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, cedula);
            System.out.println(sql);
            int eliminacion = ps.executeUpdate();
            if (eliminacion > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar. Verifica los datos e intenta nuevamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar. Consulta los registros del sistema para obtener más detalles.");
        } finally {
            con.desconectar();
        }
    }

    public void deleteTrue(String cedula) {
        try {
            con.conectar();
            String sql = "DELETE USUARIOS WHERE ID_USU=?".toLowerCase();
            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, cedula);
            int eliminacion = ps.executeUpdate();
            if (eliminacion > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar. Verifica los datos e intenta nuevamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar. Consulta los registros del sistema para obtener más detalles.");
        } finally {
            con.desconectar();
        }
    }

    public SecretKeySpec crearClave(String llave) {
        try {
            byte[] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR LA LLAVE CENTRAR DE LA CONTRASEÑA" + e);
            return null;
        }
    }

    public String encriptar(String textoAEncriptar) {
        try {
            SecretKeySpec secretKeySpec = crearClave(LLAVEENCRIP);
            Cipher cypher = Cipher.getInstance("AES");
            cypher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] cadena = textoAEncriptar.getBytes("UTF-8");
            byte[] textoEncriptado = cypher.doFinal(cadena);
            return Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception e) {
            System.out.println("ERROR AL ENCRIPTAR LA CONTRASEÑA" + e);
            return "";
        }
    }

    public String desencriptar(String textoADesencriptar) {
        try {
            SecretKeySpec secretKeySpec = crearClave(LLAVEENCRIP);
            Cipher cypher = Cipher.getInstance("AES");
            cypher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] cadena = Base64.getDecoder().decode(textoADesencriptar);
            byte[] desencriptacion = cypher.doFinal(cadena);
            String cadena_desencriptada = new String(desencriptacion);
            return cadena_desencriptada;
        } catch (Exception e) {
            System.out.println("ERROR AL DESENCRIPTAR LA CONTRASEÑA   " + e);
            return textoADesencriptar;
        }
    }

    public ArrayList<UserModel> getTableUser() {
        this.con.conectar();
        ArrayList<UserModel> userList = new ArrayList<>();
        String sql = "SELECT CED_USU, NOM_USU, APE_USU, SUE_PAG_USU, TIP_USU FROM USUARIOS WHERE NOT EST_USU=3;".toLowerCase();
        try {
            Statement statement = con.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setCedula(resultSet.getString("CED_USU"));
                user.setNombre(resultSet.getString("NOM_USU"));
                user.setApellido(resultSet.getString("APE_USU"));
                user.setSueldo_actual(resultSet.getDouble("SUE_PAG_USU"));
                user.setTipo(resultSet.getString("TIP_USU"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("ERROR getTable" + e);
        } finally {
            con.desconectar();
            return userList;
        }
    }

    public ResultSet getUsers() {

        ResultSet users = null;
        try {

            con.conectar();
            String sql = "select * from usuarios".toLowerCase();
            Statement stmt = con.getCon().createStatement();
            users = stmt.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println("aa" + ex);
        }
        con.desconectar();
        return users;
    }

}
