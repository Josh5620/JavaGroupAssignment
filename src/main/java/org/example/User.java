package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.Scanner;

public class User {
    public static void main(String[] args) {

    }

    private static String txt = "", name = "", pass = "", field = "";
    private static int  id = 0;
    public static boolean verified = false;
    static Scanner scanner = new Scanner(System.in);




    private static String jsonRead(){
        try {

            File file = new File("Users.json");
            if (!file.exists()) {
                System.out.println("Users.json not found. Please place it in the correct directory.");
                return "";
            }


            FileReader fr = new FileReader("Users.json");
            BufferedReader reader = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            txt = sb.toString();

        }catch (Exception e ){e.printStackTrace();}
        return txt;
    }

    private static void readObject(int target){
        JSONObject jsonObject = new JSONObject(txt);
        JSONArray jsonArray = jsonObject.getJSONArray("users");



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject user = jsonArray.getJSONObject(i);
            int userId = user.getInt("userId");

            if (userId == target) {
                id = userId;
                name = user.getString("userName");
                pass = user.getString("userPass");
                field = user.getString("userField");
                break;
            }
        }
        if(id != target){
            System.out.println("userId not found!");
            id = -1;
            return;
        }
        // Print the values
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Pass: " + pass);
        System.out.println("Field: " + field);

    }

    private static int userInput() {
        System.out.println("Enter userID to login!:");

        while (true) {
            try {
                return scanner.nextInt();  // If valid, return immediately
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static void userVerify(){
        if(!name.isEmpty()){
            System.out.println("Enter username: ");
            String inName = scanner.nextLine();

            if(!inName.equals(name)){
                System.out.println("Name does not match userID try again!\n");
                return;
            }

            System.out.println("Name: " + name);

            for(int i = 5; i >= 0; i--){
                System.out.println("Enter your password!: ");
                System.out.println(i + " attempts left!");
                String inPass = scanner.nextLine();
                if(inPass.equals(pass)){
                    System.out.println("Verified!");
                    verified = true;
                    break;
                }
            }
        } else {
            System.out.println("org.example.User is verified already!");
        }
    }

    public static void Start() {
        txt = jsonRead(); // Reads the file and makes it a long string
        readObject(userInput()); // Basically turns it into a object to read from and sets the name pass and field
        scanner.nextLine();
        while (!verified && id != 0) {
            userVerify();
        }
    }
}