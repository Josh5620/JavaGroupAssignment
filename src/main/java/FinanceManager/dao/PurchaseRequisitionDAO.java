/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.dao;


import FinanceManager.model.PurchaseRequisition;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 
public class PurchaseRequisitionDAO {
    
    // Path to your data file
    private static final Path PATH
            = Paths.get("src/main/resources/PurchaseRequisitions.txt");

    /**
     * Load every requisition from the text file.
     */
    public static List<PurchaseRequisition> loadAll() throws IOException {
        List<PurchaseRequisition> list = new ArrayList<>();
        for (String line : Files.readAllLines(PATH)) {
            // assume each line is: prId|YYYY-MM-DD|description|quantity|status
            String[] p = line.split("\\|");
            list.add(new PurchaseRequisition(
                    p[0],
                    LocalDate.parse(p[1]),
                    p[2],
                    Integer.parseInt(p[3]),
                    p[4]
            ));
        }
        return list;
    }

    /**
     * Rewrite the file, updating the given requisition’s status.
     */
    public static void update(PurchaseRequisition updated) throws IOException {
        List<PurchaseRequisition> all = loadAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getPrId().equals(updated.getPrId())) {
                all.set(i, updated);
                break;
            }
        }
        // write everything back
        List<String> lines = new ArrayList<>();
        for (PurchaseRequisition pr : all) {
            lines.add(String.join("|",
                    pr.getPrId(),
                    pr.getDate().toString(),
                    pr.getDescription(),
                    String.valueOf(pr.getQuantity()),
                    pr.getStatus()
            ));
        }
        Files.write(PATH, lines);
    }
    
}
