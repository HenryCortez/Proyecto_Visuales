package Controllers;

import Models.Conexion;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AsistenciaControl {

    Conexion con = new Conexion();

    public boolean insertInsgreso(String id_usu) {
        UserControl est = new UserControl();
        if (est.getStateUser(id_usu)){
            System.out.println("Ya ingreso");
            return false;
        }
        try {
            
            
            String horario = this.compareTime();
            System.out.println(horario);
            con.conectar();
            String sql = "INSERT INTO INGRESOS(ID_USU, FEC_HOR_ING, HOR_ASI) "
                    + "VALUES(?, NOW(), ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, id_usu);
            ps.setString(2, horario);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE REGISTRO SU INGRESO");
            }
            con.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hora no permitida para el ingreso");
            return false;
        }
    }

    public boolean insertSalida(String id_usu) {
        UserControl est = new UserControl();
        if (!est.getStateUser(id_usu)){
            System.out.println("Ya salio");
            return false;
        }
        String horario = getHorario(id_usu);
        try {
            con.conectar();
            String sql = "INSERT INTO SALIDAS(ID_USU_SAL, FEC_HOR_SAL, HOR_ASI) "
                    + "VALUES(?, NOW(),?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, id_usu);
            ps.setString(2, horario);
            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE REGISTRO SU SALIDA");
            }
            con.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public String getHorario(String id_usu){
        this.con.conectar();
        String horario = null;
        String sql = "SELECT HOR_ASI FROM INGRESOS WHERE ID_USU = '"+id_usu+"' ORDER BY ID_ASI DESC LIMIT 1;";
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                horario = rs.getString("HOR_ASI");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.desconectar();
            return horario;
        }
    }

    public Timestamp getNow() {
        this.con.conectar();
        String sql = "SELECT time(NOW());";
        Timestamp time = null;
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                time = rs.getTimestamp(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.desconectar();
            return time;
        }
    }

    public boolean isTimeGreaterThan(Timestamp timestamp, LocalTime targetTime) {
        // Convertir el Timestamp a LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Obtener solo la parte de la hora de LocalDateTime
        LocalTime time = localDateTime.toLocalTime();

        // Comparar la hora
        return time.isAfter(targetTime);
    }
    
    public boolean isTimeLessThan(Timestamp timestamp, LocalTime targetTime) {
        // Convertir el Timestamp a LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Obtener solo la parte de la hora de LocalDateTime
        LocalTime time = localDateTime.toLocalTime();

        // Comparar la hora
        return time.isBefore(targetTime);
    }

    public String compareTime() {
        Timestamp now = getNow();
        // Definir las horas objetivo
        LocalTime eightAM = LocalTime.of(7, 45);
        LocalTime onePM = LocalTime.of(13, 0);
        LocalTime twoPM = LocalTime.of(13, 45);
        LocalTime fivePM = LocalTime.of(17, 0);
       
        if (now != null && isTimeGreaterThan(now, eightAM) && isTimeLessThan(now, onePM)) {
            return "Matutino";
        }else if (now != null && isTimeGreaterThan(now, twoPM) && isTimeLessThan(now, fivePM)) {
            return "Vespertino";
        }
        return null;
    }
  
}
