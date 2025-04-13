package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.User.scanner;

public class OldLoginJosh {

    public static void main(String[] args) {
        makeBigList();

        while(currentUser == null) {
            findList(userInput());
        }
        login(currentUser);


    }

    static String filePath = "src/LoginTest.txt";
    static List<List<String>> bigList = new ArrayList<>();
    static List<String> currentUser;


    public static void makeBigList(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                bigList.add(Arrays.asList(parts));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static String userInput() {
        System.out.println("Enter username thanks :");

        while (true) {
            try {

                return scanner.next();  // If valid, return immediately
            } catch (Exception e) {
                System.out.println("eenter actual username");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public static void findList(String InName){
        outer:
        for(List<String> BigI : bigList){
            for(String SmallI : BigI){
                if(SmallI.equals(InName)){
                    System.out.println("FOUND IT OUTPUTTING THE LISt");
                    currentUser = BigI;
                    break outer;
                }
            }
        }
    }

    public static void login(List<String> userList) {
        System.out.println("Enter password thanks :");
        String testpass = "";
        while (true) {
            try {
                testpass = scanner.next();  // If valid, return immediately
            } catch (Exception e) {
                System.out.println("eenter actual username");
                scanner.next(); // Clear invalid input
                }

                if(testpass.equals(userList.get(2))){
                    System.out.println("Nice One");
                    break;
                }
        }
    }
}
