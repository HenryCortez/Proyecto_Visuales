
package Views;

import Controllers.UserControl;
import Models.Conexion;
import Models.UserModel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class Recognizer extends javax.swing.JFrame {

    UserControl usc = new UserControl();
    private DaemonThread myTread = null;

    //JAVACV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("photos\\haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
    RectVector detectedFaces = new RectVector();

    //VARIABLES
    String root, firstName, lastName, telefono, cedula, fec_nac;
    int id;

    //utils
    Conexion con = new Conexion();
  
    public Recognizer() {
        initComponents();
        recognizer.read("photos\\clasifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblDir = new javax.swing.JLabel();
        jlblCedula = new javax.swing.JLabel();
        jlblName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recognizer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recognizer");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 330, 40));

        jlblFoto.setForeground(new java.awt.Color(0, 0, 0));
        jlblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFoto.setToolTipText("");
        jlblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jlblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 400, 370));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblDir.setBackground(new java.awt.Color(51, 153, 255));
        jlblDir.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblDir.setForeground(new java.awt.Color(255, 255, 255));
        jlblDir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblDir.setText("FIRST NAME - LASTNAME");
        jlblDir.setOpaque(true);
        jPanel2.add(jlblDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 400, 40));

        jlblCedula.setBackground(new java.awt.Color(51, 153, 255));
        jlblCedula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblCedula.setForeground(new java.awt.Color(255, 255, 255));
        jlblCedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCedula.setText("FIRST NAME - LASTNAME");
        jlblCedula.setOpaque(true);
        jPanel2.add(jlblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 40));

        jlblName.setBackground(new java.awt.Color(51, 153, 255));
        jlblName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlblName.setForeground(new java.awt.Color(255, 255, 255));
        jlblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblName.setText("FIRST NAME - LASTNAME");
        jlblName.setOpaque(true);
        jPanel2.add(jlblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 420, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(456, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stopCamera();
    }//GEN-LAST:event_formWindowClosing

   
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
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlblCedula;
    private javax.swing.JLabel jlblDir;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JLabel jlblName;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String name;
                                name = firstName;

                                if (prediction == -1) {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    id = 0;
                                    jlblCedula.setText("desconocido");
                                    jlblName.setText("");
                                    jlblDir.setText("");
                                } else {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    System.out.println(confidence.get(0));
                                    id = prediction;
                                    System.out.println("persona reconocida como: " + id);
                                    rec();
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            Image scaledImage = buff.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_SMOOTH);
                            // Dibuja la imagen escalada en el JLabel
                            Graphics g = jlblFoto.getGraphics();
                            try {
                                if (g.drawImage(scaledImage, 0, 0, jlblFoto.getWidth(), jlblFoto.getHeight(), null)) {
                                    if (!runnable) {
                                        System.out.println("Guarda la foto");
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
    
    private void rec(){
        new Thread() {
            @Override
            public void run() {
                try {
                    UserModel usu = usc.getUser(String.valueOf(id));
                    jlblCedula.setText(usu.getCedula());
                    jlblName.setText("Bienbenido "+usu.getNombre()+" "+ usu.getApellido() );
                    jlblDir.setText(usu.getFec_nac() +" - "+ usu.getTelefono());
                } catch (Exception e) {
                }
          
            }
        }.start();
                
    }
    
    private void stopCamera() {
        myTread.runnable = false;
        webSource.release();
        dispose();
    }

    private void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myTread = new DaemonThread();
                Thread t = new Thread(myTread);
                t.setDaemon(true);
                myTread.runnable = true;
                t.start();
            }
        }.start();
    }

}
