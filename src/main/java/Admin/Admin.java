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


     
    public void AddUser(String username, String Passwd, String Role){
        int NewUserID = this.FullUserList.size() + 1;
        List<String> tmpUserList = new ArrayList<>();
        tmpUserList.add(String.valueOf(NewUserID));
        tmpUserList.add(username);
        tmpUserList.add(Passwd);
        tmpUserList.add(Role);
        for(List<String> user : this.FullUserList){
            if(username.equalsIgnoreCase(user.get(1)))
                JOptionPane.showMessageDialog(
                null,
                "Username already taken!",
                "Please input another username!",
                JOptionPane.WARNING_MESSAGE
                );
                return;
        }
        this.FullUserList.add(tmpUserList);
                this.ReloadUsers();
            System.out.println("User was successfully added!"
                    + "Current number of users:" + this.FullUserList.size());

    };
     
    public void DeleteUser(String username){
        
        
        this.ReloadUsers();
        System.out.println("User was successfully deleted!"
                + "Current number of users:" + this.FullUserList.size());
    }
    
    public void UpdateUser(String username, String Passwd, String Role){
        
        
        
        this.ReloadUsers();
    }
}
