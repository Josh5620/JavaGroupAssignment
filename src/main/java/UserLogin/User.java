
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

    static String LoginFilePath = "src/LoginTest.txt";
    public List<List<String>> FullUserList = new ArrayList<>();
    List<List<String>> SpecifiedUserList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);


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
    
    public void ReloadUsers(){
        makeBigList(LoginFilePath, FullUserList);
                JOptionPane.showMessageDialog(
                null,
                "User List Reloaded",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
