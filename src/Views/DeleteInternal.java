package Views;

import Controllers.UserControl;
import Models.UserModel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santhiag0
 */
public class DeleteInternal extends javax.swing.JInternalFrame {

    UserControl usc = new UserControl();
    JDesktopPane Escritorio;

    public DeleteInternal(JDesktopPane Escritorio) {
        initComponents();
        this.Escritorio = Escritorio;
        this.centrarVentana();
        llenarTablaUsuarios(jtable);
        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtable.getSelectedRow()!= -1) {
                    jtxtCedula.setText(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
                    jtxtNombre.setText(jtable.getValueAt(jtable.getSelectedRow(), 1).toString());
                    jtxtApellido.setText(jtable.getValueAt(jtable.getSelectedRow(), 2).toString());
                }
            }
        });

    }
    
    public void clear(){
        jtxtCedula.setText("");
        jtxtApellido.setText("");
        jtxtNombre.setText("");
    }
    
    public void Deleliminar (){
        if (jtxtCedula!=null) {
            usc.deleteUser(jtxtCedula.getText());
            clear();
            llenarTablaUsuarios(jtable);
        }
    }

    public void llenarTablaUsuarios(JTable tabla) {
        ArrayList<UserModel> userList = usc.getTableUser();

        if (tabla.getModel() == null) {
            DefaultTableModel model = new DefaultTableModel();
            tabla.setModel(model);

            model.addColumn("Cédula");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Sueldo Actual");
        }

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        model.setRowCount(0);

        for (UserModel user : userList) {
            Object[] rowData = {user.getCedula(), user.getNombre(), user.getApellido(), user.getSueldo_actual()};
            model.addRow(rowData);
        }
    }
    
    private void centrarVentana() {
        Dimension desktopSize = Escritorio.getSize();
        Dimension jInternalFrameSize = this.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;
        this.setLocation(width, height);
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
        jLabel8 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jbtnSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();

        setClosable(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitle.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jlblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblTitle.setText("Eliminar Usuario");
        jPanel1.add(jlblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nombre");

        jtxtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtNombre.setForeground(new java.awt.Color(51, 51, 51));
        jtxtNombre.setEnabled(false);
        jtxtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Apellido");

        jtxtApellido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtApellido.setForeground(new java.awt.Color(51, 51, 51));
        jtxtApellido.setEnabled(false);
        jtxtApellido.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Cédula");

        jtxtCedula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtxtCedula.setForeground(new java.awt.Color(51, 51, 51));
        jtxtCedula.setEnabled(false);
        jtxtCedula.setSelectionColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );

        jbtnSave.setBackground(new java.awt.Color(0, 0, 0));
        jbtnSave.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jbtnSave.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSave.setText("Eliminar");
        jbtnSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jbtnSave.setBorderPainted(false);
        jbtnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CEDULA", "NOMBRE", "APELLIDO", "SUELDO"
            }
        ));
        jScrollPane2.setViewportView(jtable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        Deleliminar();
    }//GEN-LAST:event_jbtnSaveActionPerformed

//    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
//    + "sed do eiusmod tempor incididunt ut labore et dolore magna"
//    + " aliqua. Ut enim ad minim veniam, quis nostrud exercitation ul"
//    + "lamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute "
//    + "irure dolor in reprehenderit in voluptate velit esse cillum dolore e"
//    + "u fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident"
//    + ", sunt in culpa qui officia deserunt mollit anim id est laborum."
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JLabel jlblTitle;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtNombre;
    // End of variables declaration//GEN-END:variables
}
