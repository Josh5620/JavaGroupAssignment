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

public class PRManager {
    private final String FILE_PATH = "C:\\Users\\dhoom\\Downloads\\PurchaseRequisitions.txt";  
   
    public List<String[]> loadPRs() throws IOException {
        List<String[]> prs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            prs.add(parts);
        }
        br.close();
        return prs;
    }

    public String generateNextPRID() throws IOException {
        List<String[]> prs = loadPRs();
        int maxNum = 0;
        for (String[] p : prs) {
            String id = p[0];  
            if (id.startsWith("PR")) {
                try {
                    int num = Integer.parseInt(id.substring(2));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("PR%03d", maxNum + 1);
    }

    
    public void addPR(String prid, String itemIDs, String quantities, String date, String supplierID, String smID, String status) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(prid + "|" + itemIDs + "|" + quantities + "|" + date + "|" + supplierID + "|" + smID + "|" + status);
        bw.newLine();
        bw.close();
    }

    
    public void editPR(String prid, String newItemIDs, String newQuantities, String newDate, String newSupplierID, String newSMID, String newStatus) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(prid)) {
                lines.add(prid + "|" + newItemIDs + "|" + newQuantities + "|" + newDate + "|" + newSupplierID + "|" + newSMID + "|" + newStatus);
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

    
    public void deletePR(String prid) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (!parts[0].equals(prid)) {
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


