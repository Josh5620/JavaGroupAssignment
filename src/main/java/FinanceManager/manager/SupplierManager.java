/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.Supplier;
import java.io.Serializable;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author sumingfei
 */
public class SupplierManager implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<Supplier> all = new ArrayList<>();

    public List<Supplier> getAll() {
        return all;
    }

    public void add(Supplier s) {
        all.add(s);
    }

    public Supplier findById(String id) {
        for (Supplier s : all) {
            if (s.getIdString().equals(id)) return s;
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
            all = (List<Supplier>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Data file is corrupt", e);
        }
    }
}
