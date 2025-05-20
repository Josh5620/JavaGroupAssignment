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
import javax.swing.table.DefaultTableModel;

public class ItemManager implements SalesManager {

    private final String ITEM_FILE = "C:\\Users\\dhoom\\Downloads\\Items.txt";
    private final String SUPPLIER_FILE = "C:\\Users\\dhoom\\Downloads\\Supplier.txt";

    public void addItem(Item item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE, true))) {
            writer.write(item.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing item: " + e.getMessage());
        }
    }

    public void updateItem(Item updatedItem) {
        File inputFile = new File(ITEM_FILE);
        File tempFile = new File("temp_items.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(updatedItem.getItemID())) {
                    writer.write(updatedItem.toString());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            System.out.println("Error updating item: " + e.getMessage());
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

    public Item getItemByID(String itemID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(itemID)) {
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String supplierID = parts[3];
                    return new Item(itemID, name, price, supplierID);
                }
            }
        } catch (IOException e) {
            System.out.println("Error getting item by ID: " + e.getMessage());
        }
        return null;
    }

    public List<String> getAllItemIDs() {
        List<String> itemIDs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    itemIDs.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading item IDs: " + e.getMessage());
        }
        return itemIDs;
    }

    public void loadItemsToTable(DefaultTableModel model) {
        model.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items to table: " + e.getMessage());
        }
    }

    public void loadSuppliersToTable(DefaultTableModel model) {
        model.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
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
    public void view() {}
}

