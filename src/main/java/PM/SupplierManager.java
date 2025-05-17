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

public class SupplierManager {
    private final String filePath = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\Suppliers.txt";

    public List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String phone = parts[3].trim();

                    List<String> itemIds = new ArrayList<>();
                    if (parts.length == 5 && !parts[4].trim().isEmpty()) {
                        itemIds = Arrays.asList(parts[4].trim().split(","));
                    }

                    Supplier supplier = new Supplier(id, name, email, phone, itemIds);
                    supplierList.add(supplier);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Suppliers.txt: " + e.getMessage());
        }

        return supplierList;
    }
}
