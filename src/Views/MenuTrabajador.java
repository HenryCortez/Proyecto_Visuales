
package Views;

import Controllers.AsistenciaControl;
import Controllers.UserControl;
import Models.Conexion;
import Services.Clock;

import java.sql.*;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MenuTrabajador extends javax.swing.JInternalFrame {

    int idTrabajador;
    String nombre;
    String apellido;
    String cedula;
    JDesktopPane Escritorio;
    AsistenciaControl asis = new AsistenciaControl();

    public MenuTrabajador(int id, JDesktopPane Escritorio, String nombre, String apellido, String cedula) {
        initComponents();
        idTrabajador = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.Escritorio = Escritorio;
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
       
        Clock relog = new Clock(jLabel1);
        Thread hiloReloj = new Thread(relog);
        hiloReloj.start();
    }

    private String obtenerCedula(int id) {
        try {
            Conexion cx = new Conexion();
            Connection cn = cx.conectar();
            String sql = "Select ced_usu from usuarios where id_usu = ? ".toLowerCase();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            String nombre = "";
            while (rs.next()) {
                nombre = rs.getString("ced_usu");
            }
            return nombre;
        } catch (SQLException ex) {
            System.out.println("error: " + ex);
        }
        return null;
    }

    public void saludarUsuario(int id) {
        try {
            Conexion cx = new Conexion();
            Connection cn = cx.conectar();
            String sql = "Select nom_usu ,ape_usu from usuarios where id_usu = ? ".toLowerCase();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nom_usu");
                String apellido = rs.getString("ape_usu");
                jLabel2.setText("Bienvenido: " + nombre + " " + apellido);
            }

        } catch (SQLException ex) {
            System.out.println("error: " + ex);
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
            String sql = "SELECT ced_usu, sue_usu from usuarios where id_usu=? ".toLowerCase();

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
                System.out.println("error: " + ex);
            }

        } catch (SQLException ex) {
            System.out.println("error: " + ex);
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
                + "FROM usuarios u\n"
                + "LEFT JOIN ingresos i ON u.ced_usu = i.id_usu\n"
                + "LEFT JOIN salidas s ON u.ced_usu = s.id_usu_sal\n"
                + "WHERE u.id_usu = ?\n"
                + "AND DATE(i.fec_hor_ing) = DATE(s.fec_hor_sal)\n"
                + "AND i.HOR_ASI = ?\n"
                + "AND s.HOR_ASI = ?\n"
                + "AND DATE(i.fec_hor_ing) = CURRENT_DATE";

        try (PreparedStatement st = cn.prepareStatement(sql.toLowerCase())) {
            st.setInt(1, idTrabajador);
            st.setString(2, Horario);
            st.setString(3, Horario);

            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    cedula = resultSet.getString("ced_usu");
                    nombre = resultSet.getString("nom_usu");
                    apellido = resultSet.getString("ape_usu");
                    String ingreso = resultSet.getString("fec_hor_ing");
                    String salida = resultSet.getString("fec_hor_sal");

                    modelo2.addRow(new Object[]{cedula, nombre + " " + apellido, ingreso, salida});
                }
            }
        } catch (SQLException ex) {
            System.out.println("error: " + ex);
        }
    }

    public void mostrarRegistros() {

        ReportesEmpleados reportesFrame = new ReportesEmpleados(Escritorio, cedula, nombre, apellido);
        System.out.println(cedula + nombre + apellido);
        Escritorio.add(reportesFrame);
        reportesFrame.setVisible(true);

    }

    public void registrarAsistencia() {
        UserControl est = new UserControl();
        int userState = est.getStateUser(cedula);

        if (userState == 0) {
            // Usuario no ha ingresado, registra entrada
            int resultadoIngreso = asis.insertIngreso(cedula);
            if (resultadoIngreso == 0) {
                JOptionPane.showMessageDialog(null, "Ya ingresó");
            } else if (resultadoIngreso == -1) {
                JOptionPane.showMessageDialog(null, "No puede acceder en este momento");
            }
            return;
        } else if (userState == 1) {
            // Usuario ya ingresó, registra salida
            int resultadoSalida = asis.insertSalida(cedula);
            if (resultadoSalida == 0) {
                JOptionPane.showMessageDialog(null, "Ya salió");
            } else if (resultadoSalida == -1) {
                JOptionPane.showMessageDialog(null, "No puede acceder en este momento");
            }
            return;
        } else if (userState == 3) {
            JOptionPane.showMessageDialog(null, "El trabajador ya no está disponible");
            return;
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("---");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 3, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("----");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Ver reportes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Jornada de registros");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Jornada Matutina");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Jornada Vespertina");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(360, 360, 360))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(351, 351, 351)))
                    .addGap(24, 24, 24)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(237, 237, 237))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(314, 314, 314)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrarRegistros();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarAsistencia();
        cargarJornada("Matutino", jtblMatutina);
        cargarJornada("Vespertino", jtblVespertina);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtblMatutina;
    private javax.swing.JTable jtblTablaUsuario;
    private javax.swing.JTable jtblVespertina;
    // End of variables declaration//GEN-END:variables
}
