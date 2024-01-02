package Views;
import java.sql.ResultSet;
import Controllers.UserControl;
import Models.Conexion;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportesAdmin extends javax.swing.JInternalFrame {
    String idUser;
    String nomUser;
    String mesTexto = "Enero";
    String mesNum = "01";
    String fechaF;
    String anio;
    
    JDesktopPane Escritorio;
    public ReportesAdmin(JDesktopPane Escritorio) {
        this.Escritorio = Escritorio;
        initComponents();
        llenarComboBoxAnio();
        this.cargarTabla();
        this.cargarCombo();
        jtbUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtbUsers.getSelectedRow() != -1) {
                   idUser = jtbUsers.getValueAt(jtbUsers.getSelectedRow(), 0).toString();
                   nomUser = jtbUsers.getValueAt(jtbUsers.getSelectedRow(), 1).toString() + " " +jtbUsers.getValueAt(jtbUsers.getSelectedRow(), 2).toString();
                }
            }
        });

    }
        private void centrarVentana() {
        Dimension desktopSize = Escritorio.getSize();
        Dimension jInternalFrameSize = this.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        this.setLocation(width, height);
    }
    //reporte de multas
    public void reporteAtrasos(){
    if (this.idUser != null) {
    try {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        this.anio = this.jcbAnio.getSelectedItem().toString();
        this.fechaF = this.anio+"-"+ this.mesNum;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nom_user", this.nomUser);
        parametros.put("mes", this.mesTexto);
        parametros.put("anio", this.anio);
        parametros.put("fechaF", this.fechaF);
        parametros.put("id_user", this.idUser);
        JasperReport reporte = JasperCompileManager.compileReport("src/Views/reportAdminRetrasos.jrxml");
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, cn);

        // Mostrar el reporte en un InternalFrame
        JInternalFrame internalFrame = new JInternalFrame("Informe de multas", true, true, true, true);
        internalFrame.setSize(600, 400);

        // Agregar el visor del informe al InternalFrame
        JasperViewer viewer = new JasperViewer(print, false);
        internalFrame.getContentPane().add(viewer.getContentPane());

        // Agregar el InternalFrame al JDesktopPane
        Escritorio.add(internalFrame);
        internalFrame.setVisible(true);
    } catch (JRException ex) {
        System.out.println(ex);
    }
}
    }
        //reporte de sueldos
    public void reporteSueldos(){
    if (this.idUser != null) {
    try {
        
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        this.anio = this.jcbAnio.getSelectedItem().toString();
        this.fechaF = this.anio+"-"+ this.mesNum;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nomC", this.nomUser);
        parametros.put("id_user", this.idUser);
        parametros.put("mesT", this.mesTexto);
        parametros.put("anio", this.anio);
        parametros.put("fechaC", this.fechaF);
        System.out.println(this.nomUser + " " + this.idUser+ this.fechaF);
        JasperReport reporte = JasperCompileManager.compileReport("src/Views/reportAdminSueldos.jrxml");
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, cn);

        // Mostrar el reporte en un InternalFrame
        JInternalFrame internalFrame = new JInternalFrame("Informe de multas", true, true, true, true);
        internalFrame.setSize(600, 400);

        // Agregar el visor del informe al InternalFrame
        JasperViewer viewer = new JasperViewer(print, false);
        internalFrame.getContentPane().add(viewer.getContentPane());

        // Agregar el InternalFrame al JDesktopPane

        Escritorio.add(internalFrame);
        internalFrame.setVisible(true);
    } catch (JRException ex) {
        System.out.println(ex);
    }
    }
    }
    
    public void cargarTabla(){
        try {
            UserControl controller = new UserControl();
            String[] columnas = {"Cédula", "Nombre", "Apellido"};
            
            DefaultTableModel model = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // No permitir edición de celdas
                    return false;
                }
            };
            ResultSet rs = controller.getUsers();
            while (rs.next()) {
                String cedula = rs.getString("CED_USU");
                String nombre = rs.getString("NOM_USU");
                String apellido = rs.getString("APE_USU");
                model.addRow(new Object[]{cedula, nombre, apellido});
            }
            
            this.jtbUsers.setModel(model);
            this.jtbUsers.getTableHeader().setReorderingAllowed(false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void cargarCombo(){
    String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel(meses);
    this.jcbMeses.setModel(modeloCombo);

    }
    
    private void llenarComboBoxAnio() {
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        String[] anios = new String[15]; 

        for (int i = 0; i < 15; i++) {
            anios[i] = String.valueOf(anioActual - i);
        }

        DefaultComboBoxModel modeloComboAnio = new DefaultComboBoxModel(anios);
        jcbAnio.setModel(modeloComboAnio);
    }
    
    private boolean camposReporteAtrasosLlenos() {
    return (idUser != null && jcbAnio.getSelectedItem() != null && jcbMeses.getSelectedItem() != null);
}

    private boolean camposReporteSueldosLlenos() {
    return (idUser != null && jcbAnio.getSelectedItem() != null && jcbMeses.getSelectedItem() != null);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbUsers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jbtRetrasos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbMeses = new javax.swing.JComboBox<>();
        jcbAnio = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbUsers.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtbUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbUsers);
        if (jtbUsers.getColumnModel().getColumnCount() > 0) {
            jtbUsers.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 90));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Ingrese el mes:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 100, -1));

        jbtRetrasos.setText("Atrasos");
        jbtRetrasos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRetrasosActionPerformed(evt);
            }
        });
        jPanel1.add(jbtRetrasos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 140, 70));

        jButton1.setText("Sueldos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 150, 70));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Seleccione el empleado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Ingrese el año:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, -1));

        jcbMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMesesActionPerformed(evt);
            }
        });
        jPanel1.add(jcbMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 150, -1));

        jcbAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAnioActionPerformed(evt);
            }
        });
        jPanel1.add(jcbAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtRetrasosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRetrasosActionPerformed
    if (camposReporteAtrasosLlenos()) {
        this.reporteAtrasos();
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos necesarios.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jbtRetrasosActionPerformed


    
    private void jcbMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMesesActionPerformed
    this.mesTexto = String.valueOf(this.jcbMeses.getSelectedItem());
    this.mesNum = String.valueOf(this.jcbMeses.getSelectedIndex()+1);
        if (this.mesNum.length()==1) {
            this.mesNum = "0"+this.mesNum;
        }
    }//GEN-LAST:event_jcbMesesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (camposReporteSueldosLlenos()) {
        this.reporteSueldos();
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos necesarios.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcbAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbAnioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtRetrasos;
    private javax.swing.JComboBox<String> jcbAnio;
    private javax.swing.JComboBox<String> jcbMeses;
    private javax.swing.JTable jtbUsers;
    // End of variables declaration//GEN-END:variables
}
