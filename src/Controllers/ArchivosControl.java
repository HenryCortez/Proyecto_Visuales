package Controllers;

import Models.Conexion;
import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArchivosControl {

    Conexion con = new Conexion();

    public void insertFile(String file_name, byte[] file_content) {
        try {
            con.conectar();
            String sql = "INSERT INTO archivos (file_name, file_content) VALUES (?, ?)";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setString(1, file_name);
            ps.setBytes(2, file_content);
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("se guardo");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateFile(String file_name, byte[] file_content) {
        try {
            con.conectar();
            String sql = "UPDATE archivos SET file_content = ? WHERE file_name = ?";

            PreparedStatement ps = con.getCon().prepareStatement(sql);
            ps.setBytes(1, file_content);
            ps.setString(2, file_name);

            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "SE ACTUALIZO");
            }
            con.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String getFileName(String file_name) {
        this.con.conectar();
        String sql = "SELECT file_name FROM archivos WHERE file_name ='" + file_name + "';";
        String name = "";
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("file_name");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return name;
        }

    }

    public byte[] getFileContent(String file_name) {
        this.con.conectar();
        String sql = "SELECT file_content FROM archivos WHERE file_name ='" + file_name + "';";
        byte[] content = null;
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                content = rs.getBytes("file_content");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return content;
        }

    }

    public ArrayList<File> getImages() {
        this.con.conectar();
        String sql = "SELECT file_name, file_content FROM archivos WHERE file_name LIKE '%.jpg'";
        ArrayList<File> fileList = new ArrayList<>();
        try {
            Statement psd = con.getCon().createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("file_content");
                File tempFile = File.createTempFile(rs.getString("file_name"), ".jpg");
                tempFile.deleteOnExit();
                try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                    outputStream.write(imageBytes);
                }

                // Añadir el archivo a la lista
                fileList.add(tempFile);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            return fileList;
        }

    }
}
