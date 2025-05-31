package shared_manager;

import UserLogin.User;
import shared_model.PurchaseOrder;
import shared_model.PurchaseRequisition;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class POManager extends User {
    private static final String filePath = "src/PurchaseOrders.txt";
    private static final List<List<String>> poList = new ArrayList<>();

    public POManager() {
        makeBigList(filePath, poList);
    }

    // === SHARED METHODS (used by Admin, FM, IM, etc.) ===

    public static List<List<String>> getPOList() {
        return poList;
    }

    public static boolean addPO(List<String> record) {
        if (record.size() != 7) return false;
        poList.add(record);
        updateTextFile(poList, filePath);
        return true;
    }

    public static boolean editPO(String poID, String newStatus) {
        for (List<String> row : poList) {
            if (row.get(0).equalsIgnoreCase(poID)) {
                row.set(4, newStatus);
                updateTextFile(poList, filePath);
                return true;
            }
        }
        return false;
    }

    public static boolean deletePO(String poID) {
        for (int i = 0; i < poList.size(); i++) {
            if (poList.get(i).get(0).equalsIgnoreCase(poID)) {
                poList.remove(i);
                updateTextFile(poList, filePath);
                return true;
            }
        }
        return false;
    }

    // === PM-SPECIFIC METHODS ===

    public void addPOFromPR(String prID, String date, String pmID) {
        try {
            String itemIDs = "", quantities = "", supplierID = "", smID = "", status = "";
            boolean found = false;

            List<String> updatedPRLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts[0].equals(prID)) {
                        status = parts[6];
                        if (!status.equalsIgnoreCase("Pending")) {
                            JOptionPane.showMessageDialog(null,
                                    "This PR is not pending and cannot be converted to PO.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null,
                        "PR not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String lastPOID = "PO000";
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length > 0 && parts[0].startsWith("PO")) {
                        lastPOID = parts[0];
                    }
                }
            }

            int nextID = Integer.parseInt(lastPOID.substring(2)) + 1;
            String newPOID = String.format("PO%03d", nextID);

            List<String> newRecord = Arrays.asList(newPOID, date, itemIDs, quantities, "Processing", pmID, "Unresolved");
            addPO(newRecord);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String l : updatedPRLines) {
                    writer.write(l);
                    writer.newLine();
                }
            }

            JOptionPane.showMessageDialog(null,
                    "Purchase Order generated successfully and PR status updated to Approved.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error generating PO or updating PR status: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<PurchaseOrder> getAllPOs() {
        List<PurchaseOrder> result = new ArrayList<>();
        for (List<String> row : poList) {
            if (row.size() == 7) {
                result.add(new PurchaseOrder(row));
            }
        }
        return result;
    }

    public PurchaseOrder getPOByID(String poID) {
        for (PurchaseOrder po : getAllPOs()) {
            if (po.getPoID().equals(poID)) {
                return po;
            }
        }
        return null;
    }

    public void editPO(PurchaseOrder updatedPO) {
        for (int i = 0; i < poList.size(); i++) {
            List<String> row = poList.get(i);
            if (row.get(0).equals(updatedPO.getPoID())) {
                poList.set(i, Arrays.asList(updatedPO.toString().split("\\|")));
                updateTextFile(poList, filePath);
                return;
            }
        }
    }

    public boolean deletePO(PurchaseOrder po) {
        return deletePO(po.getPoID());
    }
}
