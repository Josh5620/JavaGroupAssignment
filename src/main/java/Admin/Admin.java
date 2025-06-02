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
    private List<List<String>> UserList = tmpuser.getFullUserList();
    protected String currentRole = "Admin";
    protected String currentUsername;
    public void setCurrentUsername(String username) {
        this.currentUsername = username; 
    }
    public String getCurrentUsername() {
        return currentUsername;
    }
    
    public String getCurrentRole(){
        return currentRole;
    }
    
    protected List<List<String>> getUserList(){
        return UserList;
    }
    
    protected void setUserList(List<List<String>> updatedList){
        this.UserList = updatedList;
    }
    
    protected String getUserFilepath(){
        return Userfilepath;
    }
    
    protected User getTmpUser(){
        return tmpuser;
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
        logUserAction("added", username, Role);
        UserList.add(tmpUserList);
        tmpuser.updateTextFile(UserList, Userfilepath);
        JOptionPane.showMessageDialog(
        null,
        "User was successfully added! " + "Current number of users: " + UserList.size(),
        "User creation successful!",
        JOptionPane.INFORMATION_MESSAGE);
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
                String role = UserList.get(i).get(3);
                UserList.remove(i);
                
                logUserAction("deleted", username, role);
                
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
        logUserAction("updated", username, Role);
        this.updateTextFile(UserList, Userfilepath);
    }
    
       protected void logUserAction(String action, String targetUsername, String targetRole) {
    String logFilePath = "src/user_action_log.txt"; // You can change this to a full path if needed
    
    try {
        // Create the file if it doesn't exist
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            logFile.createNewFile();
            System.out.println("Created new log file: " + logFile.getAbsolutePath());
        }
        
        // Use try-with-resources for automatic resource management
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String timestamp = java.time.LocalDateTime.now()
                                 .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String logLine = timestamp + " | " +
                             getCurrentRole() + " " + getCurrentUsername() +
                             " " + action + " " + targetRole + " " + targetUsername;

            out.println(logLine);
            out.flush(); // Explicitly flush the buffer
            
            System.out.println("Log entry written: " + logLine);
            System.out.println("Log file location: " + logFile.getAbsolutePath());

        }
        
    } catch (IOException e) {
        System.err.println("Failed to log user action: " + e.getMessage());
        e.printStackTrace(); // This will show you the full stack trace
        
        // Show error dialog to user as well
        JOptionPane.showMessageDialog(
            null,
            "Failed to log user action: " + e.getMessage(),
            "Logging Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
        
        public String getLastUserActionLog() {
        String lastLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader("src/user_action_log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lastLine = line;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading action log: " + e.getMessage());
            lastLine = "No user actions found.";
        }

        return lastLine.isEmpty() ? "No user actions found." : lastLine;
    }
}