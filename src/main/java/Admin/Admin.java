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
    private User tmpuser = new User();
    private final String Userfilepath = tmpuser.getLoginFilePath();
    public List<List<String>> UserList = tmpuser.getFullUserList();
    protected String currentRole = "Admin";
    
    public String getCurrentRole(){
        return currentRole;
    }
    
    public boolean validateUserFields(String username, String password, String role){
        return!(username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                role == null || role.trim().isEmpty());
    }


     
    public void AddUser(String username, String Passwd, String Role){
        if(!validateUserFields(username, Passwd, Role)){
            JOptionPane.showMessageDialog(
            null,
                    "All fields must be filled out!", 
                    "Adding Process Cancelled!",
                    JOptionPane.WARNING_MESSAGE);
            return;
                    }
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
            else if (Role.equalsIgnoreCase("Admin") && !this.getCurrentRole().equals("SA")){
                JOptionPane.showMessageDialog(
                null,
                "Only Super Admin can add Admins!",
                "Permission Denied!",
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
        System.out.println("User was successfully added! "
                    + "Current number of users: " + UserList.size());

    };
     
    public void DeleteUser(String username){
        boolean userfound = false;
        if (username == null|| username.trim().isEmpty()){
            JOptionPane.showMessageDialog(
                null,
                "Please enter a username!",
                "User Deletion Process Cancelled!",
                JOptionPane.WARNING_MESSAGE
                );
                return;
        }
        for(int i = 0; i < UserList.size(); i++){
            String selectedUser = UserList.get(i).get(1);
            if (selectedUser.equalsIgnoreCase(username)){
                UserList.remove(i);
                
                JOptionPane.showMessageDialog(
                    null,
                    "User '" + username + "' successfully deleted!",
                    "Delete Success",
                    JOptionPane.OK_OPTION);
                userfound = true;
                break;
            }
            else if (username.equalsIgnoreCase("SuperAdmin")){
                JOptionPane.showMessageDialog(null, 
                        "Super Admin cannot be deleted.", 
                        "Permission Denied", 
                        JOptionPane.WARNING_MESSAGE);
                break;
            }
        };
        if (!userfound){
            JOptionPane.showMessageDialog(
                    null,
                    "User '" + username + "' not found!",
                    "Delete Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
        tmpuser.updateTextFile(UserList, Userfilepath);
        System.out.println("User was successfully deleted!"
                + "Current number of users:" + UserList.size());
        }
    }
    
    public void UpdateUser(String username, String Passwd, String Role){
        boolean userfound = false;
        if(!validateUserFields(username, Passwd, Role)){
            JOptionPane.showMessageDialog(
            null,
                    "All fields must be filled out!", 
                    "Update Failed",
                    JOptionPane.WARNING_MESSAGE);
            return;
                    }
        else{
            for(int i = 0; i < UserList.size(); i++) {
                String selectedUser = UserList.get(i).get(1);
                if (selectedUser.equalsIgnoreCase(username)){
                    UserList.get(i).set(2, Passwd);
                    UserList.get(i).set(3, Role);
                    JOptionPane.showMessageDialog(
                        null,
                        "User '" + username + "' successfully update!",
                        "Update Success",
                        JOptionPane.OK_OPTION);
                    userfound = true;
                    break;
                }
                else if (username.equalsIgnoreCase("SuperAdmin")){
                JOptionPane.showMessageDialog(null, 
                        "Super Admin cannot be deleted.", 
                        "Permission Denied", 
                        JOptionPane.WARNING_MESSAGE);
                break;
            }
            }
    };
        if (!userfound){
            JOptionPane.showMessageDialog(
                    null,
                    "User '" + username + "' not found!",
                    "Update Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        this.updateTextFile(UserList, Userfilepath);
    }
}