
package UserLogin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class User {
    public static void main(String[] args) {       

      
    }
    
    public User(){
        makeBigList(LoginFilePath,FullUserList);
        
}

    

    private final String AlertFilePath = "src/AlertList.txt";
    private final String LoginFilePath = "src/LoginTest.txt";
    private List<List<String>> FullUserList = new ArrayList<>();

    public static String TestFilePath = "src/Testing.txt";
    List<List<String>> SpecifiedUserList = new ArrayList<>();
    
    
    
    public static Scanner scanner = new Scanner(System.in);

    
    public List<List<String>> getFullUserList(){
        return this.FullUserList;
    }
    
    public String getLoginFilePath (){
        return this.LoginFilePath;
    }

    public void makeBigList(String filepath, List mainList){
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                mainList.add(Arrays.asList(parts));

            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void makeLoginList(List<List<String>> UserList, String Type){
                for(List<String> User : UserList){
                    for(String InType : User){
                        if(InType.equals(Type)){                  
                            SpecifiedUserList.add(User);
                            System.out.println(User + " User List has been made");
                        }
                    }
                }
                System.out.println(Type + " User List has been made");
        
    }
    
    public void reloadUsers(){
        makeBigList(LoginFilePath, FullUserList);
                JOptionPane.showMessageDialog(
                null,
                "User List Reloaded",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void checkAlert(String username, String role){
        List<List<String>> Alerts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(AlertFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            Alerts.add(Arrays.asList(parts));
            }
        
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        for(List<String> Alert : Alerts){
            String roleString = Alert.get(1);
            String userString = Alert.get(2);
            
            if(Alert.get(4).equals("Done")){
                continue;
            }
            if(roleString.equals(role)){
                System.out.println(Alert);
                System.out.println(userString);
                if(userString.equals("all")){  
                    makeBigList(LoginFilePath, FullUserList);
                    JOptionPane.showMessageDialog(
                    null,
                    "Alert for all " + roleString + ": \n" + Alert.get(3),
                    "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
                    Alert.set(4, "Done");
                    updateTextFile(Alerts, AlertFilePath);
                }
                if(userString.equals(username)){
                    makeBigList(LoginFilePath, FullUserList);
                    JOptionPane.showMessageDialog(
                    null,
                    "Username Alert: " + userString + "\n" + Alert.get(3),
                    "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
                    Alert.set(4, "Done");
                    updateTextFile(Alerts, AlertFilePath);
                }
            }
            
                    
        }
    }
    
    public void updateTextFile(List<List<String>> arrayList, String FilePathString){
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilePathString))) {
            for (List<String> row : arrayList) {
                String line = String.join("|", row);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File overwritten successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        
    }
    
    public void sendAlert(String role, String username, String message) {
    try {
        int id = 1;

        // Count lines to determine new ID
        try (BufferedReader reader = new BufferedReader(new FileReader(AlertFilePath))) {
            while (reader.readLine() != null) {
                id++;
            }
        }

        
        String alert = "\n" + id + "|" + role + "|" + username + "|" + message + "|Not Done";

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AlertFilePath, true))) {
            writer.write(alert);
            writer.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
