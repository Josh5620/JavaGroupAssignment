/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;

import java.util.Date;
/**
 *
 * @author sumingfei
 */
public class PurchaseOrder {
    private static int nextId = 1;       // simple auto-increment

    private int id;
    private PurchaseRequisition requisition;
    private String status;               // e.g. "PENDING", "PAID"
    private Date dateIssued;

    // Existing getters/setters...

    /** 
     * New constructor: create a PO from a PR, assign unique id, initial status & date 
     */
    public PurchaseOrder(PurchaseRequisition pr) {
        this.id = nextId++;
        this.requisition = pr;
        this.status = "PENDING";
        this.dateIssued = new Date();
    }

    // You’ll need a getter for requisition to call getItemCode() later:
    public PurchaseRequisition getRequisition() {
        return requisition;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateIssued() {
        return dateIssued;
    }
    
}
