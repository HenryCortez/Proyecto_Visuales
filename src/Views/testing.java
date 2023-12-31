/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.UserControl;
import Models.UserModel;
import java.util.ArrayList;
import javax.crypto.AEADBadTagException;

/**
 *
 * @author Usuario
 */
public class testing {
    public static void main(String[] args) {
        UserControl sc = new UserControl();
            ArrayList<UserModel> userList = sc.getTableUser();
            for (UserModel user : userList) {
                System.out.println(user.getCedula());
            }
    }
}
