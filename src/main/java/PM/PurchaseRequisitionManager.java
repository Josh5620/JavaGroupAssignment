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

public class PurchaseRequisitionManager {
    private final String filePath = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\PurchaseRequisitions";

    public List<PurchaseRequisition> getAllPRs() {
        List<PurchaseRequisition> prList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String prID = parts[0].trim();
                    String itemID = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    String date = parts[3].trim();
                    String supplierID = parts[4].trim();
                    String smID = parts[5].trim();
                    String status = parts[6].trim();

                    PurchaseRequisition pr = new PurchaseRequisition(prID, itemID, quantity, date, supplierID, smID, status);
                    prList.add(pr);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading PRs: " + e.getMessage());
        }

        return prList;
    }
}
