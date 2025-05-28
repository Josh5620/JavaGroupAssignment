/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author Dylan
 */
public class SuperAdmin extends Admin {
    public SuperAdmin(){
        this.currentRole = "SA";
    }
    
    @Override
    public void AddUser(String username, String Passwd, String Role) {
        // Inherit and reuse Admin validation
        if (username == null || username.trim().isEmpty()
                || Passwd == null || Passwd.trim().isEmpty()
                || Role == null || Role.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "All fields must be filled out!",
                    "Adding Process Cancelled!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<List<String>> userList = getUserList();
        int NewUserID = userList.size() + 1;
        List<String> tmpUserList = new ArrayList<>();
        tmpUserList.add(String.valueOf(NewUserID));
        tmpUserList.add(username);
        tmpUserList.add(Passwd);
        tmpUserList.add(Role);

        for (List<String> user : userList) {
            if (username.equalsIgnoreCase(user.get(1))) {
                JOptionPane.showMessageDialog(
                        null,
                        "Username already taken!",
                        "Please input another username!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        logUserAction("added", username , Role);
        userList.add(tmpUserList);
        getTmpUser().updateTextFile(userList, getUserFilepath());

        System.out.println("User was successfully added! "
                + "Current number of users: " + userList.size());
    }
}
