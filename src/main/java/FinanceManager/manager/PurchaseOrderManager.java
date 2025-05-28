/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.PurchaseOrder;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author sumingfei
 */
public class PurchaseOrderManager {
   private List<PurchaseOrder> all = new ArrayList<>();

    public List<PurchaseOrder> getAll() { return all; }
    public void add(PurchaseOrder po)   { all.add(po); }

    public PurchaseOrder findById(int id) {
        for (PurchaseOrder po : all) {
            if (po.getId() == id) return po;
        }
        return null;
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(all);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            all = (List<PurchaseOrder>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Data file is corrupt", e);
        }
    }
}
