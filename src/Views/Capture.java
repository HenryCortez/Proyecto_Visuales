package Views;

import Controllers.UserControl;
import Models.Conexion;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;

import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class Capture extends javax.swing.JFrame {

    UserControl usc = new UserControl();
    private Capture.DaemonThread myTread = null;

    //JAVACV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("photos\\haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    //VARIABLES
    String root, firstName, lastName, telefono, cedula, fec_nac;
    int numSamples = 25, sample = 1, id;

    //utils
    Conexion con = new Conexion();

    public Capture(String ced, String nom, String ape, String fec_nac, String tel, int id) {
        initComponents();
        startCamera();
        this.cedula = ced;
        this.firstName = nom;
        this.lastName = ape;
        this.fec_nac = fec_nac;
        this.telefono = tel;
        this.id = id;
    }

    private Capture() {
        initComponents();
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblCounter = new javax.swing.JLabel();
        jbtnCapture = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Capture");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Toma 25 fotos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 330, 40));

        jlblFoto.setForeground(new java.awt.Color(0, 0, 0));
        jlblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFoto.setToolTipText("");
        jlblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jlblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 400, 370));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblCounter.setBackground(new java.awt.Color(102, 102, 255));
        jlblCounter.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jlblCounter.setForeground(new java.awt.Color(0, 0, 0));
        jlblCounter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCounter.setText("00");
        jlblCounter.setOpaque(true);
        jPanel2.add(jlblCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 110, 40));

        jbtnCapture.setBackground(new java.awt.Color(42, 204, 106));
        jbtnCapture.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnCapture.setForeground(new java.awt.Color(0, 0, 0));
        jbtnCapture.setText("Capture");
        jbtnCapture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCapture.setOpaque(true);
        jbtnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCaptureActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnCapture, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 110, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 420, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 620));

        setSize(new java.awt.Dimension(451, 623));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCaptureActionPerformed

    }//GEN-LAST:event_jbtnCaptureActionPerformed

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
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capture().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnCapture;
    private javax.swing.JLabel jlblCounter;
    private javax.swing.JLabel jlblFoto;
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

                            Mat imageColor = new Mat(); //imagem colorida
                            imageColor = cameraImage;

                            Mat imageGray = new Mat(); //imagem pb
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);
//                            flip(cameraImage, cameraImage, +1);

                            RectVector detectedFaces = new RectVector(); //face detectada
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { //repetição pra encontrar as faces
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);

                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (jbtnCapture.getModel().isPressed()) { //quando apertar o botão saveButton

                                    if (sample <= numSamples) {
//                                        salva a imagem cortada [160,160]
//                                        nome do arquivo: idpessoa + a contagem de fotos. ex: person.10(id).6(sexta foto).jpg
                                        String cropped = "photos\\samples\\person." + firstName + "."+id+"."+sample + ".jpg";
                                        imwrite(cropped, face);

                                        //System.out.println("Foto " + amostra + " capturada\n");
                                        jlblCounter.setText(String.valueOf(sample) + "/25");
                                        sample++;
                                    }
                                    if (sample >25) {
                                        generate();//se a contagem for maior que 25, termina de tirar a foto, gera o arquivo
                                        saveUser(); //inserte los datos en la base

                                        System.out.println("File Generated");
                                        stopCamera(); // e fecha a camera
                                    }

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
                                        System.out.println("No acabo de tomar fotos");
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                                
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private void generate() {
        File directory = new File("photos\\samples");
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png");
            }
        };
        File[] files = directory.listFiles(filter); // solo nuestro filtro
        MatVector photos = new MatVector(files.length);
        Mat labels = new Mat(files.length, 1, CV_32SC1);
        IntBuffer labelsBuffer = labels.createBuffer();

        int counter = 0;
        for (File image : files) {
            Mat photo = imread(image.getAbsolutePath(), IMREAD_GRAYSCALE);
            int idPerson = Integer.parseInt(image.getName().split("\\.")[2]);
            opencv_imgproc.resize(photo, photo, new Size(160, 160));

            photos.put(counter, photo);
            labelsBuffer.put(counter, idPerson);
            counter++;
        }
        FaceRecognizer lbph = LBPHFaceRecognizer.create();
        lbph.train(photos, labels);
        lbph.save("photos\\clasifierLBPH.yml");
    }

    private void saveUser() throws ParseException {
        String ced = this.cedula;
        String nom = this.firstName;
        String ape = this.lastName;
        String fec = this.fec_nac;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fechaUtil = formatoFecha.parse(fec); // Convierte a java.util.Date
        System.out.println(fechaUtil);
        // Ahora convierte a java.sql.Date
        Date fecsql = new Date(fechaUtil.getTime());
        String tel = this.telefono;
        System.out.println(fecsql);
        usc.insertUser(ced, nom, ape, fecsql, tel);
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
