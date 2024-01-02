/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Controllers.UserControl;
import Models.UserModel;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author henry
 */
public class RegisterInternal extends javax.swing.JInternalFrame {

    UserControl usc = new UserControl();
    JDesktopPane Escritorio;

    public RegisterInternal(JDesktopPane Escritorio) {
        initComponents();
        this.Escritorio = Escritorio;
        desactivar();
        this.centrarVentana();
        jtxtCedula.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignora el evento de teclado si no es un dígito
                }
            }
        });
        jtxtNombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE ) {
                    e.consume(); // Ignora el evento de teclado si no es una letra
                }
            }
        });
        jtxtApellido.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignora el evento de teclado si no es una letra
                }
            }
        });
    }

    private void desactivar() {
        this.jpswConfirmado.setText("");
        this.jpswContra.setText("");
        this.jpswConfirmado.setEnabled(false);
        this.jpswContra.setEnabled(false);
    }

    private void activar() {
        this.jpswConfirmado.setText("");
        this.jpswContra.setText("");
        this.jpswConfirmado.setEnabled(true);
        this.jpswContra.setEnabled(true);
    }

    private boolean checkPassword() {
        return String.valueOf(jpswContra.getPassword()).equals(String.valueOf(jpswConfirmado.getPassword()));
    }

    private void saveUser() throws ParseException {
        ArrayList<UserModel> temporal = this.usc.getID();
        if (!checkPassword()) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return;
        }

        for (UserModel user : temporal) {
            if (this.jtxtCedula.getText().equals(user.getCedula())) {
                JOptionPane.showMessageDialog(null, "Usuario ya resgistrado en la base de datos");
                JOptionPane.showMessageDialog(null, "Usuario ya resgistrado en la base de datos");
                return;
            }
        }
        int id = temporal.size();
        if (temporal.isEmpty()) {
            CaptureInternal cap = new CaptureInternal(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(),
                    String.valueOf(jpswContra.getPassword()), String.valueOf(jcbxTipos.getSelectedItem()), 1);
            Escritorio.add(cap);
            cap.setVisible(true);
        } else if (id == temporal.get(id - 1).getID()) {
            CaptureInternal cap = new CaptureInternal(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(),
                    String.valueOf(jpswContra.getPassword()), String.valueOf(jcbxTipos.getSelectedItem()), id + 1);
            Escritorio.add(cap);
            cap.setVisible(true);
        } else {
            System.out.println(temporal.get(id - 1).getID() + "aaa");
            CaptureInternal cap = new CaptureInternal(jtxtCedula.getText(), jtxtNombre.getText(), jtxtApellido.getText(),
                    String.valueOf(jpswContra.getPassword()), String.valueOf(jcbxTipos.getSelectedItem()), temporal.get(id - 1).getID() + 1);
            Escritorio.add(cap);
            cap.setVisible(true);
        }

    }

    private void centrarVentana() {
        Dimension desktopSize = Escritorio.getSize();
        Dimension jInternalFrameSize = this.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        this.setLocation(width, height);
    }

    public boolean checkId(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 1 || provincia > 24) {
                return false;
            }

            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (tercerDigito > 5) {
                return false;
            }

            int suma = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Integer.parseInt(cedula.substring(i, i + 1));
                if (i % 2 == 0) {
                    digito = digito * 2;
                    if (digito > 9) {
                        digito -= 9;
                    }
                }
                suma += digito;
            }

            int ultimoDigito = (suma % 10) == 0 ? 0 : 10 - (suma % 10);
            return ultimoDigito == Integer.parseInt(cedula.substring(9));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlblTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtApellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jcbxTipos = new javax.swing.JComboBox<>();
        jpswContra = new javax.swing.JPasswordField();
        jpswConfirmado = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jbtnSave = new javax.swing.JButton();

        setClosable(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitle.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jlblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitle.setText("Formulario Usuario");
        jPanel1.add(jlblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nombre");

        jtxtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtNombre.setForeground(new java.awt.Color(51, 51, 51));
        jtxtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Apellido");

        jtxtApellido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtApellido.setForeground(new java.awt.Color(51, 51, 51));
        jtxtApellido.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Tipo Usuario");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Contraseña");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Cédula");

        jtxtCedula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtCedula.setForeground(new java.awt.Color(51, 51, 51));
        jtxtCedula.setSelectionColor(new java.awt.Color(0, 0, 0));
        jtxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCedulaActionPerformed(evt);
            }
        });

        jcbxTipos.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jcbxTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----", "Trabajador", "Administrador" }));
        jcbxTipos.setToolTipText("");
        jcbxTipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbxTiposItemStateChanged(evt);
            }
        });

        jpswContra.setSelectionColor(new java.awt.Color(0, 0, 0));

        jpswConfirmado.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Confirmar Contraseña");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jpswConfirmado)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpswContra, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpswContra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpswConfirmado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbtnSave.setBackground(new java.awt.Color(0, 0, 0));
        jbtnSave.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jbtnSave.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSave.setText("Guardar");
        jbtnSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jbtnSave.setBorderPainted(false);
        jbtnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbxTiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbxTiposItemStateChanged
        if (this.jcbxTipos.getSelectedItem().toString().equals("Trabajador")) {
            desactivar();
        } else if (this.jcbxTipos.getSelectedItem().toString().equals("Administrador")) {
            activar();
        } else {
            desactivar();
        }
    }//GEN-LAST:event_jcbxTiposItemStateChanged

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        try {
            if (this.jtxtCedula.getText().equals("") || this.jtxtNombre.getText().equals("") || this.jtxtApellido.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "NO SE PERMITEN CAMPOS PRIMORDIALES EN NULO");
            } else if (this.jcbxTipos.getSelectedItem().toString().equals("-----")) {
                JOptionPane.showMessageDialog(null, "SELECCIONE UN ROL PARA INGRESAR A LA BASE DE DATOS");
            } else if (!checkId(jtxtCedula.getText())) {
                JOptionPane.showMessageDialog(null, "Número de cédula no válido");
            } else {
                saveUser();
            }
        } catch (ParseException ex) {
            Logger.getLogger(RegisterInternal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jtxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCedulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JComboBox<String> jcbxTipos;
    private javax.swing.JLabel jlblTitle;
    private javax.swing.JPasswordField jpswConfirmado;
    private javax.swing.JPasswordField jpswContra;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtNombre;
    // End of variables declaration//GEN-END:variables
}
