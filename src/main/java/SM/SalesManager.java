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
    private final String FILE_PATH = "C:\\Users\\dhmez\\OneDrive - Asia Pacific University\\Desktop\\APU\\Assignment\\JavaGroupAssignment\\src\\SalesEntry.txt";

    // Load all sales
    public List<Sale> loadSales() throws IOException {
        List<Sale> sales = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 6) {
                sales.add(new Sale(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
            }
        }
        br.close();
        return sales;
    }

    // Generate next Sale ID
    public String generateNextSaleID() throws IOException {
        List<Sale> sales = loadSales();
        int maxNum = 0;
        for (Sale s : sales) {
            String id = s.getSaleID();
            if (id.startsWith("S")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("S%03d", maxNum + 1);
    }

    // Add Sale
    public void addSale(Sale sale) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(sale.toString());
        bw.newLine();
        bw.close();
    }

    // Edit Sale
    public void editSale(Sale updatedSale) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(updatedSale.getSaleID())) {
                lines.add(updatedSale.toString());
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

    // Delete Sale
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

