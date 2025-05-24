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

public class SupplierManager implements SalesManager {

    private final String SUPPLIER_FILE = "C:\\Users\\dhoom\\Downloads\\Supplier.txt";
    private final String ITEM_FILE = "C:\\Users\\dhoom\\Downloads\\Items.txt";

    public void addSupplier(Supplier supplier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SUPPLIER_FILE, true))) {
            writer.write(supplier.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error adding supplier: " + e.getMessage());
        }
    }

    public Supplier getSupplierByID(String supplierID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(supplierID)) {
                    return new Supplier(parts[0], parts[1], parts[2], parts[3], parts.length > 4 ? parts[4] : "None");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading supplier: " + e.getMessage());
        }
        return null;
    }

    public void updateSupplier(Supplier updated) {
        File inputFile = new File(SUPPLIER_FILE);
        File tempFile = new File("temp_supplier.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(updated.getSupplierID())) {
                    writer.write(updated.toString());
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
            System.out.println("Error updating supplier: " + e.getMessage());
        }
    }

    public void deleteSupplier(String supplierID) {
        File inputFile = new File(SUPPLIER_FILE);
        File tempFile = new File("temp_supplier.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (!parts[0].equals(supplierID)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            System.out.println("Error deleting supplier: " + e.getMessage());
        }
    }

    public void loadSuppliersToTable(DefaultTableModel model) {
        model.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    model.addRow(new Object[]{
                        parts[0], parts[1], parts[2], parts[3], parts.length > 4 ? parts[4] : "None"
                    });
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
        }
    }

    public List<String> getAllSupplierIDs() {
        List<String> ids = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                ids.add(parts[0]);
            }
        } catch (IOException e) {
            System.out.println("Error reading supplier IDs: " + e.getMessage());
        }
        return ids;
    }

    public List<String> getAllItemIDs() {
        List<String> ids = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                ids.add(parts[0]);
            }
        } catch (IOException e) {
            System.out.println("Error reading item IDs: " + e.getMessage());
        }
        return ids;
    }

    public void loadItemsToTable(DefaultTableModel model) {
        model.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items to table: " + e.getMessage());
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