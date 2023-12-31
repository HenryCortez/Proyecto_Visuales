/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.UserControl;
import Models.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.AEADBadTagException;

/**
 *
 * @author Usuario
 */
public class testing {
    public static void main(String[] args) {
    UserControl sc = new UserControl();
    UserModel user = sc.getUser("1804822748");  // Cambiado a UserModel en lugar de ResultSet
    if (user != null) {
        System.out.println(user.getCedula());
    } else {
        System.out.println("No se encontraron resultados para la consulta.");
    }
}

}
