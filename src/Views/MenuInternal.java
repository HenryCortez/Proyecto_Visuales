/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import javax.swing.JDesktopPane;

/**
 *
 * @author henry
 */
public class MenuInternal extends javax.swing.JInternalFrame {

    JDesktopPane Escritorio;

    public MenuInternal(String name, JDesktopPane Escritorio) {
        initComponents();
        jlblTitle.setText("Welcome " + name);
        this.Escritorio = Escritorio;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblTitle = new javax.swing.JLabel();
        jbtnData = new javax.swing.JButton();
        jbtnCapture = new javax.swing.JButton();
        jbtnRecognition = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblTitle.setForeground(new java.awt.Color(0, 0, 0));
        jlblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jlblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 730, 80));

        jbtnData.setBackground(new java.awt.Color(255, 204, 0));
        jbtnData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnData.setForeground(new java.awt.Color(0, 0, 0));
        jbtnData.setText("Data");
        jbtnData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDataActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnData, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 210, 250));

        jbtnCapture.setBackground(new java.awt.Color(0, 255, 255));
        jbtnCapture.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnCapture.setForeground(new java.awt.Color(0, 0, 0));
        jbtnCapture.setText("Capture");
        jbtnCapture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCaptureActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnCapture, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, 250));

        jbtnRecognition.setBackground(new java.awt.Color(204, 255, 0));
        jbtnRecognition.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnRecognition.setForeground(new java.awt.Color(0, 0, 0));
        jbtnRecognition.setText("Recognize");
        jbtnRecognition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnRecognition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRecognitionActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnRecognition, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 220, 250));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("What do you want?   Select one Option");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 490, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createUser() {

        RegisterInternal register = new RegisterInternal(Escritorio);
        Escritorio.add(register);
        register.setVisible(true);
    }
    private void jbtnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDataActionPerformed

    }//GEN-LAST:event_jbtnDataActionPerformed

    private void recognizeUser() {
        RecognizerInternal recognizer = new RecognizerInternal();
        Escritorio.add(recognizer);
        recognizer.setVisible(true);
    }
    private void jbtnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCaptureActionPerformed
        createUser();
    }//GEN-LAST:event_jbtnCaptureActionPerformed

    private void jbtnRecognitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRecognitionActionPerformed
        recognizeUser();
    }//GEN-LAST:event_jbtnRecognitionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnCapture;
    private javax.swing.JButton jbtnData;
    private javax.swing.JButton jbtnRecognition;
    private javax.swing.JLabel jlblTitle;
    // End of variables declaration//GEN-END:variables
}
