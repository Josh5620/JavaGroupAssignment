/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
public class Sale {
        private String saleID;
    private String itemID;
    private String date;
    private String quantity;
    private String unitPrice;
    private String total;

    public Sale(String saleID, String itemID, String date, String quantity, String unitPrice, String total) {
        this.saleID = saleID;
        this.itemID = itemID;
        this.date = date;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    // Getters
    public String getSaleID() { return saleID; }
    public String getItemID() { return itemID; }
    public String getDate() { return date; }
    public String getQuantity() { return quantity; }
    public String getUnitPrice() { return unitPrice; }
    public String getTotal() { return total; }

    // Setters
    public void setSaleID(String saleID) { this.saleID = saleID; }
    public void setItemID(String itemID) { this.itemID = itemID; }
    public void setDate(String date) { this.date = date; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public void setUnitPrice(String unitPrice) { this.unitPrice = unitPrice; }
    public void setTotal(String total) { this.total = total; }

    @Override
    public String toString() {
        return saleID + "|" + itemID + "|" + date + "|" + quantity + "|" + unitPrice + "|" + total;
    }
    
}
