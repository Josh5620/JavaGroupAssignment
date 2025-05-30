/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
public class PR {
        private String prid;
    private String itemIDs;
    private String quantities;
    private String date;
    private String supplierID;
    private String smID;
    private String status;

    public PR(String prid, String itemIDs, String quantities, String date, String supplierID, String smID, String status) {
        this.prid = prid;
        this.itemIDs = itemIDs;
        this.quantities = quantities;
        this.date = date;
        this.supplierID = supplierID;
        this.smID = smID;
        this.status = status;
    }

    // Getters
    public String getPrid() { return prid; }
    public String getItemIDs() { return itemIDs; }
    public String getQuantities() { return quantities; }
    public String getDate() { return date; }
    public String getSupplierID() { return supplierID; }
    public String getSmID() { return smID; }
    public String getStatus() { return status; }

    // Setters
    public void setPrid(String prid) { this.prid = prid; }
    public void setItemIDs(String itemIDs) { this.itemIDs = itemIDs; }
    public void setQuantities(String quantities) { this.quantities = quantities; }
    public void setDate(String date) { this.date = date; }
    public void setSupplierID(String supplierID) { this.supplierID = supplierID; }
    public void setSmID(String smID) { this.smID = smID; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return prid + "|" + itemIDs + "|" + quantities + "|" + date + "|" + supplierID + "|" + smID + "|" + status;
    }
    
}
