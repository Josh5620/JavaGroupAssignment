/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManager;

import shared_model.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshu
 */
public class testing {
    public static void main(String[] args) {
    ArrayList<String> parts = new ArrayList<>(List.of(
    "PO015", "2025-05-08", "ITM006/ITM005/ITM001", "40/18/6", "Approved", "PM001", "Unresolved"
));
    PurchaseOrder po = new PurchaseOrder(parts);
    
        po.showPO();
    }
}
