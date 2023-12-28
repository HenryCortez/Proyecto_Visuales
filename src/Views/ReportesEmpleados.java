
package Views;

import Models.Conexion;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReportesEmpleados extends javax.swing.JInternalFrame {
    JDesktopPane Escritorio;
    public ReportesEmpleados(JDesktopPane Escritorio, String cedula, String nombre, String apellido) {
        initComponents();
        this.Escritorio = Escritorio;
        System.out.println( cedula);  
        System.out.println( nombre + " " + apellido);  
        txtCedula.setText(cedula);
        txtNombre.setText(nombre + " " + apellido);
        System.out.println("HOLA PAPU");
        // Asumiendo que tienes campos para nombre y apellido o un campo para ambos
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jFormattedEdder1 = new Views.JFormattedEdder();
        xd = new javax.swing.JFormattedTextField();

        setClosable(true);
        setResizable(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CEDULA");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, 40));

        jButton1.setBackground(new java.awt.Color(51, 255, 102));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Entrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 200, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REPORTES EMPLEADOS");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 290, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NOMBRE");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FECHA");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, 40));

        txtCedula.setEditable(false);
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 240, 40));

        txtNombre.setEditable(false);
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 240, 40));

        jFormattedEdder1.setText("jFormattedEdder1");
        jPanel2.add(jFormattedEdder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 240, 40));

        xd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xdActionPerformed(evt);
            }
        });
        jPanel2.add(xd, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 250, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        
        // Obtener los datos del formulario
        String cedula = txtCedula.getText();
        String nombreCompleto = txtNombre.getText();
        String fecha = jFormattedEdder1.getText();

        // Preparar los parámetros
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("iduser", cedula);
        parametros.put("nombreC", nombreCompleto);
        parametros.put("fecha", fecha);

        // Compilar y llenar el reporte
     /*   JasperReport reporte = JasperCompileManager.compileReport("ruta/del/archivo/reportEmpleado.jrxml");
        JasperPrint print= JasperFillManager.fillReport(reporte, parametros, cn);

        // Mostrar el reporte
        JasperViewer.viewReport(print, false);*/
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    } 
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void xdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xdActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private Views.JFormattedEdder jFormattedEdder1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField xd;
    // End of variables declaration//GEN-END:variables
}
