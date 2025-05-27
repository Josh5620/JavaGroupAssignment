/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.PurchaseOrder;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author sumingfei
 */
public class PurchaseOrderManager {
    private List<PurchaseOrder> all = new ArrayList<>();

    public List<PurchaseOrder> getAll() {
        return all;
    }

    public void add(PurchaseOrder po) {
        all.add(po);
    }

    /** Find a PO by its id (returns null if not found) */
    public PurchaseOrder findById(int id) {
        for (PurchaseOrder po : all) {
            if (po.getId() == id) {
                return po;
            }
        }
        return null;
    }
    
}
