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

public class SalesManager {
    private final String FILE_PATH = "C:\\Users\\dhoom\\Downloads\\SalesEntry.txt"; 

   
    public List<String[]> loadSales() throws IOException {
        List<String[]> sales = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {  
                sales.add(parts);
            }
        }
        br.close();
        return sales;
    }

   
    public String generateNextSaleID() throws IOException {
        List<String[]> sales = loadSales();
        int maxNum = 0;
        for (String[] s : sales) {
            String id = s[0]; 
            if (id.startsWith("S")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        int nextNum = maxNum + 1;
        return String.format("S%03d", nextNum);
    }

    
    public void addSale(String saleID, String itemID, String date, String quantity, String unitPrice, String total) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(saleID + "|" + itemID + "|" + date + "|" + quantity + "|" + unitPrice + "|" + total);
        bw.newLine();
        bw.close();
    }

   
    public void editSale(String saleID, String newItemID, String newDate, String newQuantity, String newUnitPrice, String newTotal) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(saleID)) {
                lines.add(saleID + "|" + newItemID + "|" + newDate + "|" + newQuantity + "|" + newUnitPrice + "|" + newTotal);
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

   
    public void deleteSale(String saleID) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (!parts[0].equals(saleID)) {
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

