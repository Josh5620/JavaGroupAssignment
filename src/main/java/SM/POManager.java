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

public class POManager {

    private static final String PO_FILE = "C:\\Users\\dhoom\\Downloads\\PurchaseOrders.txt";

   
    public List<String[]> loadPOs() throws IOException {
        List<String[]> pos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(PO_FILE));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");  
            pos.add(parts);  
        }
        br.close();
        return pos;
    }

   
}
