/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.PurchaseRequisition;
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

    public void add(PurchaseRequisition pr) {
        all.add(pr);
    }

    /** Find a PR by its id (returns null if not found) */
    public PurchaseRequisition findById(int id) {
        for (PurchaseRequisition pr : all) {
            if (pr.getId() == id) {
                return pr;
            }
        }
        return null;
    }
    
}
