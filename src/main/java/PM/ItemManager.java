package PM;

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
                    String itemID = parts[0];
                    String itemName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String supplierID = parts[3];

                    Item item = new Item(itemID, itemName, price, supplierID);
                    itemList.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading items: " + e.getMessage());
        }
        return itemList;
    }
}
