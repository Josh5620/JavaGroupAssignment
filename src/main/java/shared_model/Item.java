package shared_model;

public class Item {
    private String itemID;
    private String itemName;
    private String price;
    private String supplierID;

    public Item(String itemID, String itemName, String price, String supplierID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.supplierID = supplierID;
    }

    public String getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public String getPrice() { return price; }
    public String getSupplierID() { return supplierID; }

    // Setters
    public void setItemID(String itemID) { this.itemID = itemID; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setPrice(String price) { this.price = price; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }

    @Override
    public String toString() {
        return itemID + "|" + itemName + "|" + price + "|" + supplierID;
    }
}