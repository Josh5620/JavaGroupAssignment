/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
import java.io.*;
import java.util.*;

public class ItemManager {
    private final String FILE_PATH = "C:\\Users\\dhoom\\Downloads\\Items.txt";  

    public List<String[]> loadItems() throws IOException {
        List<String[]> items = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
                items.add(parts);
            }
        }
        br.close();
        return items;
    }

    public String generateNextItemID() throws IOException {
        List<String[]> items = loadItems();
        int maxNum = 0;
        for (String[] item : items) {
            String id = item[0];  
            if (id.startsWith("ITM")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        int nextNum = maxNum + 1;
        return String.format("ITM%03d", nextNum); 
    }

    public void addItem(String itemID, String itemName, String price, String supplierID) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(itemID + "|" + itemName + "|" + price + "|" + supplierID);
        bw.newLine();
        bw.close();
    }

    public void editItem(String itemID, String newName, String newPrice, String newSupplierID) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(itemID)) {
                lines.add(itemID + "|" + newName + "|" + newPrice + "|" + newSupplierID);
            } else {
                lines.add(line);
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public void deleteItem(String itemID) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (!parts[0].equals(itemID)) {
                lines.add(line);
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }
}
