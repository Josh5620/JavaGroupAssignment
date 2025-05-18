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

public class PurchaseOrderManager {
    private final String itemsFilePath = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\Items.txt";
    private final String poFilePath = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\PurchaseOrders.txt";

    // ✅ إضافة PO جديدة
    public void addPO(String poID, String itemID, int quantity, String date, String supplierID, String pmID) {
        try {
            if (poID.isEmpty() || itemID.isEmpty() || date.isEmpty() || supplierID.isEmpty() || pmID.isEmpty()) {
                System.out.println("One or more fields are empty. Cannot proceed.");
                return;
            }

            // 1. Get price per unit from Items.txt
            double unitPrice = -1;
            try (BufferedReader reader = new BufferedReader(new FileReader(itemsFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3 && parts[0].trim().equals(itemID)) {
                        unitPrice = Double.parseDouble(parts[2].trim());
                        break;
                    }
                }
            }

            if (unitPrice == -1) {
                System.out.println("Item ID not found in items file.");
                return;
            }

            double totalPrice = unitPrice * quantity;
            String status = "Processing";

            PurchaseOrder po = new PurchaseOrder(poID, itemID, quantity, date, supplierID, totalPrice, pmID, status);

            // 2. Append to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(poFilePath, true))) {
                writer.write(po.toString());
                writer.newLine();
            }

            System.out.println("PO added successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding PO: " + e.getMessage());
        }
    }

    // ✅ قراءة جميع الـ POs
    public List<PurchaseOrder> getAllPOs() {
        List<PurchaseOrder> poList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(poFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    String poID = parts[0];
                    String itemID = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    String date = parts[3];
                    String supplierID = parts[4];
                    double price = Double.parseDouble(parts[5]);
                    String pmID = parts[6];
                    String status = parts[7];
                    PurchaseOrder po = new PurchaseOrder(poID, itemID, quantity, date, supplierID, price, pmID, status);
                    poList.add(po);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading POs: " + e.getMessage());
        }
        return poList;
    }

    // ✅ تعديل PO موجود
    public void editPO(PurchaseOrder updatedPO) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(poFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(updatedPO.getPoID())) {
                    updatedLines.add(updatedPO.toString());
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading PO file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(poFilePath))) {
            for (String l : updatedLines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing PO file: " + e.getMessage());
        }
    }
    
    public boolean deletePO(String poID) {
        boolean deleted = false;
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(poFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith(poID + "|")) {
                    updatedLines.add(line);
                } else {
                    deleted = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading PO file: " + e.getMessage());
            return false;
        }

        if (deleted) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(poFilePath))) {
                for (String l : updatedLines) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing PO file: " + e.getMessage());
                return false;
            }
        }

        return deleted;
    }
    
    

}