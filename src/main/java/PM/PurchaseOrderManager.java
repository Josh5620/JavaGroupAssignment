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
    private Item item;
    private PurchaseRequisition pr;
    private PurchaseOrder po;

    public void addPOFromPR(String prID, String date, String pmID) {
        try {
            String prFilePath = "src/PurchaseRequisitions.txt";
            String itemIDs = "", quantities = "", supplierID = "", smID = "", status = "";
            boolean found = false;

        
            List<String> updatedPRLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseRequisition.File_Path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts[0].equals(prID)) {
                        status = parts[6];
                        if (!status.equalsIgnoreCase("Pending")) {
                            javax.swing.JOptionPane.showMessageDialog(null,
                                    "This PR is not pending and cannot be converted to PO.",
                                    "Error",
                                    javax.swing.JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        itemIDs = parts[1];
                        quantities = parts[2];
                        supplierID = parts[4];
                        smID = parts[5];
                        found = true;
                        updatedPRLines.add(parts[0] + "|" + parts[1] + "|" + parts[2] + "|" + parts[3] + "|" + parts[4] + "|" + parts[5] + "|Approved");
                    } else {
                        updatedPRLines.add(line);
                    }
                }
            }

            if (!found) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "PR not found.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

        
            String lastPOID = "PO000";
            try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseOrder.File_Path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length > 0 && parts[0].startsWith("PO") && parts[0].length() >= 5) {
                        String numberPart = parts[0].substring(2);
                        if (numberPart.matches("\\d+")) {
                            lastPOID = parts[0];
                        }
                    }
                }
            }
            int nextID = Integer.parseInt(lastPOID.substring(2)) + 1;
            String newPOID = String.format("PO%03d", nextID);

            String poStatus = "Processing";
            String resolution = "Unresolved";

        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PurchaseOrder.File_Path, true))) {
                writer.write(newPOID + "|" + date + "|" + itemIDs + "|" + quantities + "|" + poStatus + "|" + pmID + "|" + resolution);
                writer.newLine();
            }

        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PurchaseRequisition.File_Path))) {
                for (String l : updatedPRLines) {
                    writer.write(l);
                    writer.newLine();
                }
            }

            javax.swing.JOptionPane.showMessageDialog(null,
                    "Purchase Order generated successfully and PR status updated to Approved.",
                    "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Error generating PO or updating PR status: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }




    public List<PurchaseOrder> getAllPOs() {
        List<PurchaseOrder> poList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseOrder.File_Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String poID = parts[0];
                    String date = parts[1];
                    String itemIDs = parts[2];
                    String quantities = parts[3];
                    String status = parts[4];
                    String pmID = parts[5];
                    String resolution = parts[6];

                    po = new PurchaseOrder(poID, date, itemIDs, quantities, status, pmID, resolution);
                    poList.add(po);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading POs: " + e.getMessage());
        }
        return poList;
    }

    public void editPO(PurchaseOrder updatedPO) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseOrder.File_Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7 && parts[0].equals(updatedPO.getPoID())) {
                    String updatedLine = updatedPO.getPoID() + "|" + 
                                         updatedPO.getDate() + "|" + 
                                         updatedPO.getItemIDs() + "|" + 
                                         updatedPO.getQuantities() + "|" + 
                                         updatedPO.getStatus() + "|" + 
                                         updatedPO.getPmID() + "|" + 
                                         updatedPO.getResolution();
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading PO file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PurchaseOrder.File_Path))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader(PurchaseOrder.File_Path))) {
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PurchaseOrder.File_Path))) {
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
    
    public PurchaseOrder getPOByID(String poID) {
        List<PurchaseOrder> allPOs = getAllPOs();
        for (PurchaseOrder Po : allPOs) {
            if (Po.getPoID().equals(poID)) {
                return Po;
            }
        }
        return null;
    }

}
