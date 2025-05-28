/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;


import FinanceManager.model.SalesEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sumingfei
 */
public class SalesManager {
    
    
    private List<SalesEntry> sales = new ArrayList<>();

    /** Return a fresh copy of all sales for display/reporting. */
    public List<SalesEntry> getAll() {
        return new ArrayList<>(sales);
    }

    /** Find one sale by its ID, or return null if not found. */
    public SalesEntry findById(int id) {
        for (SalesEntry s : sales) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    /** Add a new sale to the in‐memory list. */
    public void add(SalesEntry sale) {
        sales.add(sale);
    }

    /**
     * Load the entire list from a file.  Clobbers any
     * existing in‐memory data.
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) throws IOException {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            sales = (List<SalesEntry>) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Bad data format in " + fileName, e);
        }
    }

    /** Save the current list out to a file (overwrites). */
    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(sales);
        }
    }
    
}
