package Admin;
import UserLogin.User;

import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class Admin extends User {
    User tmpuser = new User();
    String Userfilepath = tmpuser.getLoginFilePath();
    List<List<String>> UserList = tmpuser.getFullUserList();


     
    public void AddUser(String username, String Passwd, String Role){
        int NewUserID = UserList.size() + 1;
        List<String> tmpUserList = new ArrayList<>();
        tmpUserList.add(String.valueOf(NewUserID));
        tmpUserList.add(username);
        tmpUserList.add(Passwd);
        tmpUserList.add(Role);
        for(List<String> user : UserList){
            if(username.equalsIgnoreCase(user.get(1))){
                JOptionPane.showMessageDialog(
                null,
                "Username already taken!",
                "Please input another username!",
                JOptionPane.WARNING_MESSAGE
                );
                return;
        }
            else if (UserList == null) {
                JOptionPane.showMessageDialog(
                null,
                "User List is not loaded properly!"
                );
            }
        }
        UserList.add(tmpUserList);
        tmpuser.updateTextFile(UserList, Userfilepath);
        this.reloadUsers();
        System.out.println("User was successfully added! "
                    + "Current number of users: " + UserList.size());

    };
     
    public void DeleteUser(String username){
        for(int i = 0; i < UserList.size(); i++){
            String selectedUser = UserList.get(i).get(i);
            if (selectedUser.equalsIgnoreCase(username)){
                UserList.remove(i);
                
            }
            
        };
        
        this.reloadUsers();
        System.out.println("User was successfully deleted!"
                + "Current number of users:" + UserList.size());
    }
    
    public void UpdateUser(String username, String Passwd, String Role){
        
        
        this.updateTextFile(UserList, Userfilepath);
        this.reloadUsers();
    }
}
