/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author sumingfei
 */
public class PurchaseOrder implements Serializable{
    private int id;
    private PurchaseRequisition requisition;
    private double amount;
    private String supplierId;      
    private Date dateIssued;
    private String status;

    public PurchaseOrder(int id,
                         PurchaseRequisition requisition,
                         double amount,
                         String supplierId) {
        this.id = id;
        this.requisition = requisition;
        this.amount = amount;
        this.supplierId  = supplierId;
        this.dateIssued = new Date();
        this.status = "PENDING";
    }

    public int    getId()        { return id; }
    public String getItemIds()   { return requisition.getItemIds(); }
    public int    getQuantity()  { return requisition.getQuantity(); }
    public double getAmount()    { return amount; }
    public String getSupplierId() { return supplierId; }
    public String getStatus()    { return status; }
    public Date   getDateIssued(){ return dateIssued; }
    public PurchaseRequisition getRequisition() { return requisition; }

    public void setStatus(String status) { this.status = status; }
    public void setAmount(double amt)    { this.amount = amt; }
    
    public String getItemCode() {
        return requisition.getItemIds();   // or getItemCode(), whichever your PR model exposes
    }
}
