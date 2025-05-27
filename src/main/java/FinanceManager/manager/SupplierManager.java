/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.manager;

import FinanceManager.model.Supplier;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author sumingfei
 */
public class SupplierManager {
    private List<Supplier> all = new ArrayList<>();
    public List<Supplier> getAll() { return all; }
    public void add(Supplier s)     { all.add(s); }
    
}
