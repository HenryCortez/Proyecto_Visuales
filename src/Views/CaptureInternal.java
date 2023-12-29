/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Controllers.ArchivosControl;
import Controllers.UserControl;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
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

/**
 *
 * @author henry
 */
public class CaptureInternal extends javax.swing.JInternalFrame {

    UserControl usc = new UserControl();
    ArchivosControl arch = new ArchivosControl();
    private DaemonThread myTread = null;

    //JAVACV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("photos\\haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();

    //VARIABLES
    String firstName, lastName, contra, cedula, tipo;
    int numSamples = 25, sample = 1, id;

    public CaptureInternal(String ced, String nom, String ape, String contra, String tipo, int id) {
        initComponents();
        startCamera();
        this.cedula = ced;
        this.firstName = nom;
        this.lastName = ape;
        this.contra = contra;
        this.tipo = tipo;
        this.id = id;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblCounter = new javax.swing.JLabel();
        jbtnCapture = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFoto.setToolTipText("");
        jlblFoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jlblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 420, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblCounter.setBackground(new java.awt.Color(255, 255, 255));
        jlblCounter.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jlblCounter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCounter.setText("00");
        jlblCounter.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jlblCounter.setOpaque(true);
        jPanel2.add(jlblCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 110, 40));

        jbtnCapture.setBackground(new java.awt.Color(0, 0, 0));
        jbtnCapture.setFont(new java.awt.Font("Segoe UI Semilight", 2, 18)); // NOI18N
        jbtnCapture.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCapture.setText("Capturar");
        jbtnCapture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCaptureActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnCapture, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 110, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 420, 110));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Capturando  Imágenes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCaptureActionPerformed

    }//GEN-LAST:event_jbtnCaptureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
                                        BytePointer bytePointer = new BytePointer();
                                        opencv_imgcodecs.imencode(".jpg", face, bytePointer);

                                        // Obtener el array de bytes de la imagen
                                        byte[] imageBytes = bytePointer.getStringBytes();
                                        String cropped = "person." + cedula + "." + id + "." + sample + ".jpg";
                                        arch.insertFile(cropped, imageBytes);

                                        //System.out.println("Foto " + amostra + " capturada\n");
                                        jlblCounter.setText(String.valueOf(sample) + "/25");
                                        sample++;
                                        bytePointer = null;
                                    }
                                    if (sample > 25) {
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
        
        File[] files =  arch.getImages().toArray(new File[0]);
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
        String tempFileName = "tempClassifier.yml";
        lbph.save(tempFileName);

        // Leer el contenido del archivo temporal
        byte[] ymlData;
        try {
            ymlData = Files.readAllBytes(Path.of(tempFileName));
            String file_name = "clasifierLBPH.yml";
            String aux = arch.getFileName(file_name);
            if (!aux.equals("clasifierLBPH.yml")) {
                // Ahora 'ymlData' contiene los datos del archivo y puedes almacenarlo en la base de datos
                arch.insertFile(file_name, ymlData);
            } else {
                arch.updateFile(file_name, ymlData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Eliminar el archivo temporal
            new File(tempFileName).delete();
        }

    }

    private void saveUser() throws ParseException {
        String ced = this.cedula;
        String nom = this.firstName;
        String ape = this.lastName;
        String contra = this.contra;
        String tipo = this.tipo;
        if (contra == "") {
            usc.insertWorker(ced, nom, ape, tipo);
        } else {
            usc.insertAdmin(ced, nom, ape, contra, tipo);
        }
    }

    private void stopCamera() {
        myTread.runnable = false;
        webSource.release();
        this.dispose();
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
