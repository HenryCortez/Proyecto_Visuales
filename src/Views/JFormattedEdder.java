/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JFormattedEdder extends JFormattedTextField {
    
    public JFormattedEdder() {
        super();
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DateDocumentFilter());
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validateDate();
            }
        });
    }

    private void validateDate() {
        String text = getText();
        if (text.length() != 10) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la fecha en el formato correcto (yyyy-MM-dd).");
        }
    }

    private static class DateDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if (!string.matches("[0-9]")) {
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.insert(offset, string);

            if (sb.length() > 10) { // Limita la longitud a 10 caracteres (yyyy-MM-dd)
                return;
            }

            if ((sb.length() == 4 || sb.length() == 7) && !string.equals("-")) {
                string += "-";
            }

            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if (!text.matches("[0-9-]+")) {
                return;
            }

            if ((offset == 4 || offset == 7) && text.equals("-")) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                insertString(fb, offset, text, attrs);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fecha");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JFormattedEdder());
            frame.pack();
            frame.setVisible(true);
        });
    }
}