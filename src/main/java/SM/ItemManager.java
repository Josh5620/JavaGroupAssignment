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
import javax.swing.table.DefaultTableModel;

public class ItemManager implements SalesManager {

    private final String ITEM_FILE = "C:\\Users\\dhoom\\Downloads\\Items.txt";
    private final String SUPPLIER_FILE = "C:\\Users\\dhoom\\Downloads\\Supplier.txt";

    public void addItem(Item item) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE, true));
            writer.write(item.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing item: " + e.getMessage());
        }
    }

    public void deleteItem(String itemID) {
        File inputFile = new File(ITEM_FILE);
        File tempFile = new File("temp_items.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (!parts[0].equals(itemID)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    public void loadSuppliersToTable(DefaultTableModel model) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
        }
    }

    @Override
    public void add() {}

    @Override
    public void edit() {}

    @Override
    public void delete() {}

    @Override
    public void view() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading items: " + e.getMessage());
        }
    }
}

