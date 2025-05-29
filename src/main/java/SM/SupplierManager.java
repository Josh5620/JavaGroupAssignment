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

public class SupplierManager {
    private final String FILE_PATH = "C:\\Users\\dhoom\\Downloads\\Supplier.txt"; 

    
    public List<String[]> loadSuppliers() throws IOException {
        List<String[]> suppliers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {  // ID, Name, Email, Phone, ItemIDs
                suppliers.add(parts);
            }
        }
        br.close();
        return suppliers;
    }

  
    public String generateNextSupplierID() throws IOException {
        List<String[]> suppliers = loadSuppliers();
        int maxNum = 0;
        for (String[] s : suppliers) {
            String id = s[0];  // SUP001
            if (id.startsWith("SUP")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        int nextNum = maxNum + 1;
        return String.format("SUP%03d", nextNum);
    }

  
    public void addSupplier(String id, String name, String email, String phone) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(id + "|" + name + "|" + email + "|" + phone + "|" + "NoItems");
        bw.newLine();
        bw.close();
    }

    
    public void editSupplier(String id, String newName, String newEmail, String newPhone, String newItemID) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts[0].equals(id)) {
          
                String currentItems = parts.length >= 5 ? parts[4] : "NoItems";
                if (currentItems.equals("NoItems")) {
                    currentItems = newItemID;  
                } else if (!currentItems.contains(newItemID)) {
                    currentItems += "\\" + newItemID;  
                }
                lines.add(id + "|" + newName + "|" + newEmail + "|" + newPhone + "|" + currentItems);
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

    // حذف Supplier
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