/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.PurchaseRequisition;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;          // if you're using Date in your parsing
import java.util.ArrayList;     // already have List/ArrayList
import java.util.List;

/**
 *
 * @author sumingfei
 */
public class PurchaseRequisitionManager {
    private List<PurchaseRequisition> all = new ArrayList<>();

    public List<PurchaseRequisition> getAll() { return all; }
    public void add(PurchaseRequisition pr)  { all.add(pr); }

    // change signature to String
    public PurchaseRequisition findById(String prId) {
        for (PurchaseRequisition pr : all) {
            if (pr.getPrId().equals(prId)) return pr;
        }
        return null;
    }
    /** Persist list to file */
    public void saveToFile(String filename) throws IOException {
    // Overwrite the file with one line per PR, pipe-delimited
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
        for (PurchaseRequisition pr : all) {
            pw.printf(
                "%s|%s|%d|%s|%s|%s|%s%n",
                pr.getPrId(),
                pr.getItemIds(),
                pr.getQuantity(),
                fmt.format(pr.getDateRequested()),
                pr.getSupplierId(),
                pr.getSalesMgrId(),
                pr.getStatus()
            );
        }
    }   
    }

    /** Load list from file (if it exists) */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException {
   all.clear();
    File f = new File(filename);
    if (!f.exists()) return;

    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
        String line;
        while ((line = br.readLine()) != null) {
            // skip any line without exactly 7 pipe-separated fields
            String[] p = line.split("\\|", -1);
            if (p.length != 7) continue;

            try {
                PurchaseRequisition pr = new PurchaseRequisition(
                    p[0],                // prId
                    p[1],                // itemIds
                    Integer.parseInt(p[2]),      // quantity
                    fmt.parse(p[3]),     // dateRequested
                    p[4],                // supplierId
                    p[5],                // salesMgrId
                    p[6]                 // status
                );
                all.add(pr);
            } catch (NumberFormatException|ParseException ex) {
                // malformed data on this line; skip it
                System.err.println("Skipping bad PR line: " + line);
            }
        }
    } catch (IOException ioe) {
        // some I/O error; log and carry on
        System.err.println("Could not load requisitions: " + ioe.getMessage());
    }
}
    
    public void appendToFile(String filename, PurchaseRequisition pr) throws IOException {
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
        pw.printf(
            "%s|%s|%d|%s|%s|%s|%s%n",
            pr.getPrId(),
            pr.getItemIds(),
            pr.getQuantity(),
            fmt.format(pr.getDateRequested()),
            pr.getSupplierId(),
            pr.getSalesMgrId(),
            pr.getStatus()
        );
    }
}

}
