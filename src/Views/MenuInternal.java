/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import java.awt.Dimension;
import javax.swing.JDesktopPane;

/**
 *
 * @author henry
 */
public class MenuInternal extends javax.swing.JInternalFrame {

    JDesktopPane Escritorio;

    public MenuInternal(String name, JDesktopPane Escritorio) {
        initComponents();
        jlblTitle.setText("Bienvenido " + name);
        this.Escritorio = Escritorio;
        this.centrarVentana();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblTitle = new javax.swing.JLabel();
        jbtnData = new javax.swing.JButton();
        jbtnCapture = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Labella = new javax.swing.JLabel();
        jbtnRepo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jlblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 80));

        jbtnData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/basura.png"))); // NOI18N
        jbtnData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jbtnData.setContentAreaFilled(false);
        jbtnData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDataActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnData, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 230, 170));

        jbtnCapture.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnCapture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/agregar-usuario.png"))); // NOI18N
        jbtnCapture.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jbtnCapture.setContentAreaFilled(false);
        jbtnCapture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCaptureActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnCapture, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 230, 170));

        jbtnEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/editar.png"))); // NOI18N
        jbtnEdit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jbtnEdit.setContentAreaFilled(false);
        jbtnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 230, 170));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Que deseas hacer hoy?");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 440, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Selecciona una Opción");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 490, 80));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ELIMINAR");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 230, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REGISTRAR");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, -1));

        Labella.setBackground(new java.awt.Color(255, 255, 255));
        Labella.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        Labella.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labella.setText("REPORTES");
        Labella.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(Labella, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 230, -1));

        jbtnRepo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnRepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/editar.png"))); // NOI18N
        jbtnRepo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jbtnRepo.setContentAreaFilled(false);
        jbtnRepo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnRepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRepoActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnRepo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 230, 170));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EDITAR");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createUser() {

        RegisterInternal register = new RegisterInternal(Escritorio);
        Escritorio.add(register);
        register.setVisible(true);
    }

    private void centrarVentana() {
        Dimension desktopSize = Escritorio.getSize();
        Dimension jInternalFrameSize = this.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        this.setLocation(width, height);
    }

    private void jbtnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDataActionPerformed
        deleteUser();
    }//GEN-LAST:event_jbtnDataActionPerformed

    private void updateUser() {
        UpdateInternal update = new UpdateInternal(Escritorio);
        Escritorio.add(update);
        update.setVisible(true);
    }

    private void deleteUser() {
        DeleteInternal delete = new DeleteInternal(Escritorio);
        Escritorio.add(delete);
        delete.setVisible(true);
    }
    
    public void mostrarRegistros() {
        
        ReportesAdmin reportesFrame = new ReportesAdmin(Escritorio);
        Escritorio.add(reportesFrame);
        reportesFrame.setVisible(true);

    }

    private void jbtnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCaptureActionPerformed
        createUser();
    }//GEN-LAST:event_jbtnCaptureActionPerformed

    private void jbtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditActionPerformed
        updateUser();
    }//GEN-LAST:event_jbtnEditActionPerformed

    private void jbtnRepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRepoActionPerformed
        mostrarRegistros();
    }//GEN-LAST:event_jbtnRepoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Labella;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnCapture;
    private javax.swing.JButton jbtnData;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnRepo;
    private javax.swing.JLabel jlblTitle;
    // End of variables declaration//GEN-END:variables
}
