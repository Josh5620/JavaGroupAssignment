/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.InventoryEntry;
import java.io.*;
import java.util.*;

/**
 *
 * @author sumingfei
 */
public class InventoryManager {
      private List<InventoryEntry> all = new ArrayList<>();

  public List<InventoryEntry> getAll() { return all; }
  public void add(InventoryEntry e)   { all.add(e); }

  public InventoryEntry findByItem(String code) {
    for (InventoryEntry e : all)
      if (e.getItemCode().equals(code))
        return e;
    return null;
  }

  @SuppressWarnings("unchecked")
  public void loadFromFile(String fn) throws IOException {
    File f = new File(fn);
    if (!f.exists()) return;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
      all = (List<InventoryEntry>)in.readObject();
    } catch (ClassNotFoundException e) {
      throw new IOException("Corrupt inventory file", e);
    }
  }

  public void saveToFile(String fn) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fn))) {
      out.writeObject(all);
    }
  }
    
}
