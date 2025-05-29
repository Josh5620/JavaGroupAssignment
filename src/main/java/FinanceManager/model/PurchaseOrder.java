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
     private static final long serialVersionUID = 1L;

    private int id;
    private PurchaseRequisition requisition;
    private double amount;
    private int supplierId;
    private String status;
    private Date dateIssued;

    /**
     * New constructor with amount & supplierId.
     */
    public PurchaseOrder(int id,
                         PurchaseRequisition requisition,
                         double amount,
                         int supplierId) {
        this.id           = id;
        this.requisition  = requisition;
        this.amount       = amount;
        this.supplierId   = supplierId;
        this.dateIssued   = new Date();
        this.status       = "PENDING";    // default
    }

    public int    getId()          { return id; }
    public PurchaseRequisition getRequisition() { return requisition; }
    public double getAmount()      { return amount; }
    public int    getSupplierId()  { return supplierId; }
    public String getStatus()      { return status; }
    public Date   getDateIssued()  { return dateIssued; }

    // These two allow your UI to call po.getItemCode() / po.getQuantity()
    public String getItemCode()    { return requisition.getItemCode(); }
    public int    getQuantity()    { return requisition.getQuantity(); }

    // Optional setters if you ever need them
    public void setStatus(String status) { this.status = status; }
    public void setAmount(double amount) { this.amount = amount; }
}
