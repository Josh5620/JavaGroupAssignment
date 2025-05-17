/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM;

/**
 *
 * @author dhmez
 */
import java.io.*;
import java.util.*;

public class ItemManager {
    private final String filePath = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\Items.txt";

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String supplierId = parts[3].trim();

                    Item item = new Item(id, name, price, supplierId);
                    itemList.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Items.txt: " + e.getMessage());
        }

        return itemList;
    }
}
