package org.example;
import java.io.FileReader;
import java.util.Scanner;




public class Main {
    public static void main(String[] args) {
        File file = new File('users.json');
        Scanner scanner = new Scanner(reader);


        String line;
        while(scanner.hasNext()){
            System.out.println(line);
        }



    }
}