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
/**
 *
 * @author sumingfei
 */
public class PurchaseRequisitionManager {
     private List<PurchaseRequisition> all = new ArrayList<>();

    public List<PurchaseRequisition> getAll() { 
        return all; 
    }
    public void add(PurchaseRequisition pr)   { 
        all.add(pr); 
    }

    public PurchaseRequisition findById(int id) {
        for (PurchaseRequisition pr : all) {
            if (pr.getId() == id) return pr;
        }
        return null;
    }

    /** Persist list to file */
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(all);
        }
    }

    /** Load list from file (if it exists) */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            all = (List<PurchaseRequisition>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Data file is corrupt", e);
        }
    }
}
