package shared_manager;
import shared_model.Item;
import UserLogin.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ItemManager {
    private final String FILE_PATH = "src/Items.txt";

    // Load All Items
    public List<Item> loadItems() throws IOException {
        List<Item> items = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
                items.add(new Item(parts[0], parts[1], parts[2], parts[3]));
            }
        }
        br.close();
        return items;
    }

    // Generate Next ItemID
    public String generateNextItemID() throws IOException {
        List<Item> items = loadItems();
        int maxNum = 0;
        for (Item item : items) {
            String id = item.getItemID();
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

    // Add Item
    public void addItem(Item item) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(item.toString());
        bw.newLine();
        bw.close();
    }

    // Edit Item
    public void editItem(Item updatedItem) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(updatedItem.getItemID())) {
                lines.add(updatedItem.toString());
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

    // Delete Item
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