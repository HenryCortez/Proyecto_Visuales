/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;

/**
 *
 * @author henry
 */
public class Clock implements Runnable{
    Conexion conex = new Conexion();
    private final Connection con;
    JLabel clock;
    public Clock( JLabel clock) {
        
       this.con = conex.conectar();
       this.clock = clock;
    }
    
    @Override
    public void run() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Obtener la hora de la base de datos
                Timestamp horaDB = obtenerHoraDB();
                
                String horaFormateada = formatearHora(horaDB, timeFormat);
                // Imprimir la hora
                clock.setText(horaFormateada);
               
                // Esperar un segundo
                TimeUnit.SECONDS.sleep(1);
                
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    private Timestamp obtenerHoraDB() throws SQLException {
        // Asumiendo que tienes una tabla que almacena la hora que necesitas
        String sql = "SELECT time(Now()) as hora;"; // Ajusta esta consulta a tus necesidades
        try (PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                
                return resultSet.getTimestamp("hora");
            } else {
                throw new SQLException("No se pudo obtener la hora de la base de datos.");
            }
          
        }
    }
    private String formatearHora(Timestamp timestamp, SimpleDateFormat format) {
        return format.format(timestamp);
    }
   
    
}
