package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.User.scanner;

public class User {
    public static void main(String[] args) {
        
        makeBigList(LoginFilePath,bigList);
        while(currentUser == null) {
            findList(userInput());
        }
        login(currentUser);
        
    }

    static String LoginFilePath = "src/LoginTest.txt";
    static List<List<String>> bigList = new ArrayList<>();
    static List<String> currentUser;
    static Scanner scanner = new Scanner(System.in);


    public static void makeBigList(String filepath, List mainList){
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
        String testpass = "";  // Use this ltr when changing how its entered uk like the inputbox answer
        String testJob = "";
        boolean NotVeri = true;
        while (NotVeri) {
            try {
                System.out.println("Enter password thanks :");
                testpass = scanner.next();  // If valid, return immediately
                System.out.println("Enter jobType thanks :");
                testJob = scanner.next(); // same as the usernaem ltr change if case blah blah uk what i mean
            } catch (Exception e) {
                System.out.println("eenter actual username");
                scanner.next(); // Clear invalid input
            }
            if(testJob.equals(userList.get(3))) {
                if (testpass.equals(userList.get(2))) {
                    System.out.println("UserID: " + userList.get(0));
                    System.out.println("Username: " + userList.get(1));
                    System.out.println("Password: " + userList.get(2));
                    System.out.println("JobType: " + userList.get(3));
                    NotVeri = false;

                }else{System.out.println("Wrong password");}
            }else{System.out.println("No user with this jobtype available!");}
        }
    }
}
