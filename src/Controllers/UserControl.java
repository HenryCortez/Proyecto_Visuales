package Controllers;

import Models.Conexion;
import Models.UserModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserControl {

    Conexion con = new Conexion();

    public void insertUser(String ced, String nom, String ape, Date fec, String tel) {
        try {
            con.conectar();
            String sql = "INSERT INTO USUARIOS(CED_USU, NOM_USU, APE_USU, FEC_NAC, TEL_USU) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, ced);
            ps.setString(2, nom);
            ps.setString(3, ape);
            ps.setDate(4, fec);
            ps.setString(5, tel);
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
        String sql = "SELECT ID, CED_USU FROM USUARIOS ORDER BY ID ASC";
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
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
        String sql = "SELECT CED_USU, NOM_USU, APE_USU, FEC_NAC, TEL_USU FROM USUARIOS "
                + "WHERE ID ="+id_user+";";
        UserModel user =new UserModel();
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                user.setCedula(rs.getString("CED_USU"));
                user.setNombre( rs.getString("NOM_USU"));
                user.setApellido(rs.getString("APE_USU"));
                user.setFec_nac(String.valueOf(rs.getDate("FEC_NAC")));
                user.setTelefono(rs.getString("TEL_USU"));
                
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return user;
        }

    }

}
