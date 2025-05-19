/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
public class Item {
        private String itemID;
    private String name;
    private double price;
    private String supplierID;

    public Item(String itemID, String name, double price, String supplierID) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.supplierID = supplierID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String toString() {
        return itemID + "|" + name + "|" + price + "|" + supplierID;
    }
}
