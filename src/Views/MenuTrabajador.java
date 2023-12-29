/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.Conexion;
import Services.Clock;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas_j2dhfxw
 */
public class MenuTrabajador extends javax.swing.JInternalFrame {

   int idTrabajador;
  
     public MenuTrabajador(int id) {
        initComponents();
        idTrabajador = id;

        runClock();
        saludarUsuario(id);
        cargarJornada("Matutino", jtblMatutina);
        cargarJornada("Vespertino", jtblVespertina);
        cargarDatosUsuario();
        //cargarJornada("Vespertino");

        // Intentar maximizar el JInternalFrame
        /*try {
            setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
         */
        this.closable = true;
        this.resizable = true;
    }
     
     
     public void runClock() {
        Conexion con = new Conexion();
        Clock relog = new Clock(con.conectar(), jLabel1);
        Thread hiloReloj = new Thread(relog);
        hiloReloj.start();
    }

    private String obtenerCedula(int id) {
        try {
            Conexion cx = new Conexion();
            Connection cn = cx.conectar();
            String sql = "Select ced_usu from usuarios where id_usu = ? ";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            String nombre = "";
            while (rs.next()) {
                nombre = rs.getString("ced_usu");
            }
            return nombre;
        } catch (SQLException ex) {
            System.out.println("error: "+ ex);
        }
        return null;
    }

    public void saludarUsuario(int id) {
        try {
            Conexion cx = new Conexion();
            Connection cn = cx.conectar();
            String sql = "Select nom_usu ,ape_usu from usuarios where id_usu = ? ";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nom_usu");
                String apellido = rs.getString("ape_usu");
                jLabel2.setText("Bienvenido: " + nombre + " " + apellido);
            }

        } catch (SQLException ex) {
             System.out.println("error: "+ ex);
        }

    }

    private void cargarDatosUsuario() {
        Conexion cc = new Conexion();

        try (Connection cn = cc.conectar()) {
            String[] nombres = {"Cedula", "Entrada Jornada", "Salida Jornada"};
            DefaultTableModel modelo = new DefaultTableModel(nombres, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas no sean editables
                return false;
            }
        };
            jtblTablaUsuario.setModel(modelo);
            String sql = "SELECT ced_usu, sue_usu from usuarios where id_usu=? ";

            try (PreparedStatement st = cn.prepareStatement(sql)) {
                // Impresiones de depuración
                st.setInt(1, idTrabajador);

                try (ResultSet resultSet = st.executeQuery()) {
                    while (resultSet.next()) {
                        String cedula = resultSet.getString("ced_usu");

                        modelo.addRow(new Object[]{cedula, "8:00", "17:00"});
                    }
                }
            } catch (SQLException ex) {
                 System.out.println("error: "+ ex);
            }

        } catch (SQLException ex) {
             System.out.println("error: "+ ex);
        } // La conexión se cerrará automáticamente al salir del bloque try-with-resources
    }

    private void cargarJornada(String Horario, JTable tabla) {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String[] titulos = {"Cedula", "Nombres", "Hora Registro Ingreso", "Hora Registro Salida"};
        DefaultTableModel modelo2 = new DefaultTableModel(titulos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas no sean editables
                return false;
            }
        };
        tabla.setModel(modelo2);

        String sql = "SELECT u.ced_usu, u.nom_usu, u.ape_usu, i.fec_hor_ing, s.fec_hor_sal\n"
                + "FROM app_visual.usuarios u\n"
                + "LEFT JOIN app_visual.ingresos i ON u.ced_usu = i.id_usu\n"
                + "LEFT JOIN app_visual.salidas s ON u.ced_usu = s.id_usu_sal\n"
                + "WHERE u.id_usu = ?\n"
                + "AND DATE(i.fec_hor_ing) = DATE(s.fec_hor_sal)\n"
                + "AND i.HOR_ASI = ?\n"
                + "AND s.HOR_ASI = ?\n"
                + "AND DATE(i.fec_hor_ing) = CURRENT_DATE";

        try (PreparedStatement st = cn.prepareStatement(sql)) {
            // Impresiones de depuración
            System.out.println("idTrabajador: " + obtenerCedula(idTrabajador));
            System.out.println("Horario: " + Horario);

            st.setInt(1, idTrabajador);
            st.setString(2, Horario);
            st.setString(3, Horario);

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    int cedula = resultSet.getInt("ced_usu");
                    String nombre = resultSet.getString("nom_usu");
                    String apellido = resultSet.getString("ape_usu");
                    String ingreso = resultSet.getString("fec_hor_ing");
                    String salida = resultSet.getString("fec_hor_sal");

                    modelo2.addRow(new Object[]{cedula, nombre + " " + apellido, ingreso, salida});
                }
            }
        } catch (SQLException ex) {
             System.out.println("error: "+ ex);
        }
    }

   public void registros (){
       
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTablaUsuario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblVespertina = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblMatutina = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 3, 36)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 3, 24)); // NOI18N
        jLabel2.setText("jLabel2");

        jButton1.setText("Registrar");

        jButton2.setText("Ver reportes");

        jtblTablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblTablaUsuario);

        jtblVespertina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtblVespertina);

        jtblMatutina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jtblMatutina);

        jLabel3.setText("Jornada de registros");

        jLabel4.setText("Jornada Matutina");

        jLabel5.setText("Jornada Vespertina");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel3)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtblMatutina;
    private javax.swing.JTable jtblTablaUsuario;
    private javax.swing.JTable jtblVespertina;
    // End of variables declaration//GEN-END:variables
}
