/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM;

/**
 *
 * @author dhmez
 */
public class Item {
    private String itemId;
    private String itemName;
    private double price;
    private String supplierId;

    public Item(String itemId, String itemName, double price, String supplierId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.supplierId = supplierId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplierId() {
        return supplierId;
    }

    @Override
    public String toString() {
        return itemId + " | " + itemName + " | " + price + " | " + supplierId;
    }
}
