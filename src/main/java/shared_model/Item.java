package shared_model;

public class Item {
    private String itemID;
    private String itemName;
    private double price;
    private String supplierID;

    public Item(String itemID, String itemName, double price, String supplierID) {
        this.itemID     = itemID;
        this.itemName   = itemName;
        this.price      = price;
        this.supplierID = supplierID;
    }

    /** line format: itemID|itemName|price|supplierID */
    public static Item parse(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 4) return null;
        try {
            return new Item(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                parts[3]
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String toDataLine() {
        return itemID + "|" + itemName + "|" + price + "|" + supplierID;
    }

    // Getters & setters
    public String getItemID()     { return itemID; }
    public String getItemName()   { return itemName; }
    public double getPrice()      { return price; }
    public String getSupplierID() { return supplierID; }

    public void setItemName(String itemName)     { this.itemName = itemName; }
    public void setPrice(double price)           { this.price = price; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }
}