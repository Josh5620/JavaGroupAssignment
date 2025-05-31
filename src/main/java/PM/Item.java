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
    public static final String File_Path = "src/Items.txt";


    public Item(String itemId, String itemName, double price, String supplierId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.supplierId = supplierId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSupplierId(String supplierId) {
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

    public String getFilePath() {
        return File_Path;
    }
    

    
    
    

    @Override
    public String toString() {
        return itemId + " | " + itemName + " | " + price + " | " + supplierId;
    }
}
