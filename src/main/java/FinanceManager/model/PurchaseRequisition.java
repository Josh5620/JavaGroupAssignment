/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author sumingfei
 */
public class PurchaseRequisition implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int    id;
    private String itemCode;
    private int    quantity;
    private Date   dateRequested;

    // Constructor(s) …
    public PurchaseRequisition(int id, String itemCode, int quantity, Date dateRequested) {
        this.id            = id;
        this.itemCode      = itemCode;
        this.quantity      = quantity;
        this.dateRequested = dateRequested;
    }

    // **Add these getters**:
    public int    getId()            { return id; }
    public String getItemCode()      { return itemCode; }
    public int    getQuantity()      { return quantity; }
    public Date   getDateRequested() { return dateRequested; }

    // (Optionally setters, if you need them)
    
}
