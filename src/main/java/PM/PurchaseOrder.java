/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM;

/**
 *
 * @author dhmez
 */
public class PurchaseOrder {
    private String poID;
    private String itemID;
    private int quantity;
    private String date;
    private String supplierID;
    private double price;
    private String pmID;
    private String status;

    public PurchaseOrder(String poID, String itemID, int quantity, String date, String supplierID, double price, String pmID, String status) {
        this.poID = poID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.date = date;
        this.supplierID = supplierID;
        this.price = price;
        this.pmID = pmID;
        this.status = status;
    }

    public String getPoID() { return poID; }
    public String getItemID() { return itemID; }
    public int getQuantity() { return quantity; }
    public String getDate() { return date; }
    public String getSupplierID() { return supplierID; }
    public double getPrice() { return price; }
    public String getPmID() { return pmID; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return poID + "|" + itemID + "|" + quantity + "|" + date + "|" + supplierID + "|" + price + "|" + pmID + "|" + status;
    }
}

