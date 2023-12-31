package Views;

import Models.Conexion;
import Services.Clock;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * @author henry y otros jeje
 */
public class Desktop extends javax.swing.JFrame {

    public void getMedidas(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setSize((int) width, (int) (height*0.97)); 
        setLocationRelativeTo(null);
    }
    public Desktop() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        //getMedidas();
        runClock();
        
    }


    public void runClock(){
        Conexion con = new Conexion();
        Clock relog = new Clock(con.conectar(), jLabel1);
        Thread hiloReloj = new Thread(relog);
            hiloReloj.start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("background.jpg"));
        Image img = icon.getImage();
        Escritorio = new javax.swing.JDesktopPane() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        ;
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtnAdmin = new javax.swing.JButton();
        jbtnTrabajador = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escritorio");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jbtnAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jbtnAdmin.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jbtnAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAdmin.setText("ADMINISTRADOR");
        jbtnAdmin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jbtnAdmin.setContentAreaFilled(false);
        jbtnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminActionPerformed(evt);
            }
        });

        jbtnTrabajador.setBackground(new java.awt.Color(0, 0, 0));
        jbtnTrabajador.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jbtnTrabajador.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTrabajador.setText("EMPLEADO");
        jbtnTrabajador.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jbtnTrabajador.setContentAreaFilled(false);
        jbtnTrabajador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTrabajadorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 2, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REGISTRO PERSONAL:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jbtnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jbtnTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Escritorio.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Escritorio)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdminActionPerformed
        LoginInternal log = new LoginInternal(Escritorio);
        Escritorio.add(log);
        log.setVisible(true);
    }//GEN-LAST:event_jbtnAdminActionPerformed

    private void jbtnTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTrabajadorActionPerformed
        LoginEmployee log1 = new  LoginEmployee(Escritorio);
        Escritorio.add(log1);
        log1.setVisible(true);
    }//GEN-LAST:event_jbtnTrabajadorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Desktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Desktop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnAdmin;
    private javax.swing.JButton jbtnTrabajador;
    // End of variables declaration//GEN-END:variables
}
