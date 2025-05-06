/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;


import java.time.LocalDate;
/**
 *
 * @author sumingfei
 */
public class PurchaseRequisition {
    
    private String prId;
    private LocalDate date;
    private String description;
    private int quantity;
    private String status;  // e.g. "PENDING", "APPROVED"

    public PurchaseRequisition(String prId, LocalDate date, String desc, int qty, String status) {
        this.prId = prId;
        this.date = date;
        this.description = desc;
        this.quantity = qty;
        this.status = status;
    }

    // getters & setters
    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
