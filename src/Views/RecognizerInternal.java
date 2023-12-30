package Views;

import Controllers.ArchivosControl;
import Controllers.AsistenciaControl;
import Controllers.UserControl;
import Models.UserModel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
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
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class RecognizerInternal extends javax.swing.JInternalFrame {

    ArchivosControl arch = new ArchivosControl();
    AsistenciaControl asis = new AsistenciaControl();
    UserControl usc = new UserControl();
    private DaemonThread myTread = null;
    JDesktopPane Escritorio;

    //JAVACV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("photos\\haarcascade_frontalface_alt.xml");
    BytePointer mem = new BytePointer();
    FaceRecognizer recognizer = LBPHFaceRecognizer.create();

    //VARIABLES
    String cedula;
    int id;
    Map<Integer, Integer> conteoIds = new HashMap<>();
    ;
    final double UMBRAL = 7;
    final int MAX_INTENTOS = 10;

    public RecognizerInternal(JDesktopPane Escritorio) {
        initComponents();
        this.Escritorio = Escritorio;
        this.centrarVentana();
        readClassifier();
        recognizer.setThreshold(62);
        startCamera();
        this.Escritorio = Escritorio;

    }

    private int encontrarIdMasFrecuente() {
        return conteoIds.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }
    
    public boolean encontrarUsuario (int idReconocido) throws InterruptedException {
    conteoIds.put(idReconocido, conteoIds.getOrDefault(idReconocido, 0) + 1);

    int totalIntentos = conteoIds.values().stream().mapToInt(Integer::intValue).sum();
    if (totalIntentos >= MAX_INTENTOS) {

        int idMasFrecuente = encontrarIdMasFrecuente();

        if (conteoIds.get(idMasFrecuente) >= UMBRAL) {
            System.out.println("Identificación confirmada para ID: " + idMasFrecuente);
            UserModel usu = usc.getUser(String.valueOf(idMasFrecuente));
            cedula = usu.getCedula();
            try {
                // Muestra el cuadro principal de Trabajador"
              MenuTrabajador menu = new MenuTrabajador(idMasFrecuente);
              Escritorio.add(menu);
              menu.setVisible(true);
                
           
            } catch (Exception e) {
            } finally {
                stopCamera();
            }
        } else {
            System.out.println("Identificación no concluyente.");
            conteoIds.clear();
            return false;
        }
        conteoIds.clear();
        return true;
    }
    return false;
}
    
   //metodo para asistencia y ver registros//
    /* public boolean reconocer(int idReconocido) throws InterruptedException {
    conteoIds.put(idReconocido, conteoIds.getOrDefault(idReconocido, 0) + 1);

        int totalIntentos = conteoIds.values().stream().mapToInt(Integer::intValue).sum();
        if (totalIntentos >= MAX_INTENTOS) {

            int idMasFrecuente = encontrarIdMasFrecuente();

        if (conteoIds.get(idMasFrecuente) >= UMBRAL) {
            System.out.println("Identificación confirmada para ID: " + idMasFrecuente);
            UserModel usu = usc.getUser(String.valueOf(idMasFrecuente));
            int estadoUsuario = usc.getStateUser(String.valueOf(idMasFrecuente));

            // Agregar lógica basada en el estado del usuario
            if (estadoUsuario == 3) {
                System.out.println("El trabajador ya no está disponible");
                conteoIds.clear();
                return -1; // Trabajador no disponible
            }

            try {
                // Muestra el cuadro de diálogo para elegir entre "Registros" y "Asistencias"
                String[] opciones = {"Registros", "Asistencias"};
                int seleccion = JOptionPane.showOptionDialog(
                        RecognizerInternal.this,
                        "¿Qué acción deseas realizar?",
                        "Selecciona una opción",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                // Verifica la opción seleccionada
                if (seleccion == 0) {
                    String cedula = usu.getCedula();
                    String nombre = usu.getNombre();
                    String apellido = usu.getApellido();

                    // Abrir ReportesEmpleados con los datos
                    ReportesEmpleados reportesFrame = new ReportesEmpleados(Escritorio, cedula, nombre, apellido);
                    Escritorio.add(reportesFrame);
                    reportesFrame.setVisible(true);

                } else if (seleccion == 1) {
                    // Asistencias (código existente)
                    int resultadoIngreso = asis.insertIngreso(cedula);
                    if (resultadoIngreso == 0) {
                        JOptionPane.showMessageDialog(null, "Ya ingresó");
                    } else if (resultadoIngreso == -1) {
                        JOptionPane.showMessageDialog(null, "No puede acceder en este momento");
                    }

                    int resultadoSalida = asis.insertSalida(cedula);
                    if (resultadoSalida == 0) {
                        JOptionPane.showMessageDialog(null, "Ya salió");
                    } else if (resultadoSalida == -1) {
                        JOptionPane.showMessageDialog(null, "No puede acceder en este momento");
                    }

                    stopCamera();
                }
            } catch (Exception e) {
                // Manejar la excepción
            } finally {
                stopCamera();
            }
            conteoIds.clear();
            return 0; // Identificación no concluyente
        }
        conteoIds.clear();
        return 1; // Identificación exitosa
    }
    return -2; // No se ha alcanzado el límite de intentos
}
    */
    public void readClassifier() {
        File tempFile = null;
        FileOutputStream fos = null;
        byte[] classifierBytes = arch.getFileContent("clasifierLBPH.yml");
        try {
            // Crear un archivo temporal para los bytes del clasificador
            tempFile = File.createTempFile("classifier", ".yml");

            // Escribir los bytes del clasificador en el archivo temporal
            fos = new FileOutputStream(tempFile);
            fos.write(classifierBytes);

            // Leer el clasificador desde el archivo temporal
            recognizer.read(tempFile.getAbsolutePath());

            // Aquí, el clasificador está listo para usarse
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el FileOutputStream si es necesario
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Borrar el archivo temporal
            if (tempFile != null) {
                tempFile.delete();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jlblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlblDir = new javax.swing.JLabel();
        jlblCedula = new javax.swing.JLabel();
        jlblName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblFoto.setToolTipText("");
        jlblFoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jlblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 400, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblDir.setBackground(new java.awt.Color(0, 0, 0));
        jlblDir.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jlblDir.setForeground(new java.awt.Color(255, 255, 255));
        jlblDir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblDir.setText("------------------------");
        jlblDir.setOpaque(true);
        jPanel2.add(jlblDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 400, 40));

        jlblCedula.setBackground(new java.awt.Color(0, 0, 0));
        jlblCedula.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jlblCedula.setForeground(new java.awt.Color(255, 255, 255));
        jlblCedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCedula.setText("-------------------------");
        jlblCedula.setOpaque(true);
        jPanel2.add(jlblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 40));

        jlblName.setBackground(new java.awt.Color(0, 0, 0));
        jlblName.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jlblName.setForeground(new java.awt.Color(255, 255, 255));
        jlblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblName.setText("-------------------------");
        jlblName.setOpaque(true);
        jPanel2.add(jlblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 420, 160));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 2, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reconocedor de Rostros");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 400, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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

                                if (prediction == -1) {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    id = 0;
                                    jlblCedula.setText("desconocido");
                                    jlblName.setText("");
                                    jlblDir.setText("");
                                } else {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    id = prediction;
                                    rec();
                                    encontrarUsuario(id);
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
                                        System.out.println("Se termino");
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

    private void rec() {

        new Thread() {
            @Override
            public void run() {
                try {
                    UserModel usu = usc.getUser(String.valueOf(id));
                    jlblCedula.setText(usu.getCedula());
                    jlblDir.setText(usu.getNombre() + " " + usu.getApellido());
                } catch (Exception e) {
                }
            }
        }.start();

    }

    private void stopCamera() throws InterruptedException {

        myTread.runnable = false;
        if (webSource != null) {
            webSource.release(); // Detener la cámara
        }
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
