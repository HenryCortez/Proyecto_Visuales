package Controllers;

import Models.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class AsistenciaControl {
    Conexion con = new Conexion();
    
    public void insertInsgreso(String id_usu, Timestamp fec_hor, String horario){
        try {
            con.conectar();
            String sql = "INSERT INTO INGRESOS(ID_USU, FEC_HOR_ING, HOR_ASI) "
                    + "VALUES(?, ?, ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, id_usu);
            ps.setTimestamp(2, fec_hor);
            ps.setString(3, horario);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE REGISTRO SU INGRESO");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insertSalida(String id_usu, Timestamp fec_hor, String horario){
        try {
            con.conectar();
            String sql = "INSERT INTO SALIDAS(ID_USU_SAL, FEC_HOR_SAL, HOR_ASI) "
                    + "VALUES(?, ?, ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, id_usu);
            ps.setTimestamp(2, fec_hor);
            ps.setString(3, horario);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE REGISTRO SU SALIDA");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
