/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.dao;


import FinanceManager.model.PurchaseRequisition;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.nio.file.Paths;

 

public class PurchaseRequisitionDAO {
    private static final Path FILE_PATH =
     Paths.get("src", "main", "resources", "PurchaseRequisitions.txt");
    




    


    /**
     * Load every requisition from the text file.
     */
    public static List<PurchaseRequisition> loadAll() throws IOException {
        // 1) Open the file from the classpath
    try (InputStream in = 
             PurchaseRequisitionDAO.class
               .getClassLoader()
               .getResourceAsStream("PurchaseRequisitions.txt")) {
        if (in == null) {
            throw new IOException(
              "Could not find PurchaseRequisitions.txt on classpath");
        }

        // 2) Read & parse each line
        return new BufferedReader(new InputStreamReader(in))
          .lines()
          .map(line -> {
              String[] p = line.split("\\|");
              return new PurchaseRequisition(
                p[0],
                LocalDate.parse(p[1]),
                p[2],
                Integer.parseInt(p[3]),
                p[4]
              );
          })
          .collect(Collectors.toList());
    }
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
        
        Files.write(FILE_PATH, lines);
    }
    
}
