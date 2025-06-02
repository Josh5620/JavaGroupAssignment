package shared_manager;

import java.io.*;
import java.util.*;
import shared_model.Supplier;

public class SupplierManager {
    private final String FILE_PATH = "src/Suppliers.txt";

    // Load all suppliers
    public List<Supplier> loadSuppliers() throws IOException {
        List<Supplier> suppliers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                suppliers.add(new Supplier(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        }
        br.close();
        return suppliers;
    }

    // Generate next Supplier ID
    public String generateNextSupplierID() throws IOException {
        List<Supplier> suppliers = loadSuppliers();
        int maxNum = 0;
        for (Supplier s : suppliers) {
            String id = s.getId();
            if (id.startsWith("SUP")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("SUP%03d", maxNum + 1);
    }

    // Add Supplier
    public void addSupplier(Supplier supplier) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(supplier.toString());
        bw.newLine();
        bw.close();
    }

    // Edit Supplier
    public void editSupplier(Supplier updatedSupplier, String newItemID) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(updatedSupplier.getId())) {
                String currentItems = parts.length >= 5 ? parts[4] : "NoItems";
                Set<String> itemSet = new LinkedHashSet<>(Arrays.asList(currentItems.split("\\\\")));
                if (currentItems.equals("NoItems") || currentItems.isEmpty()) {
                    itemSet.clear();
                }
                itemSet.add(newItemID);  
                String updatedItems = String.join("/", itemSet);
                Supplier newSupplier = new Supplier(updatedSupplier.getId(), updatedSupplier.getName(), updatedSupplier.getEmail(), updatedSupplier.getPhone(), updatedItems);
                lines.add(newSupplier.toString());
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

    // Delete Supplier
    public void deleteSupplier(String id) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (!parts[0].equals(id)) {
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
