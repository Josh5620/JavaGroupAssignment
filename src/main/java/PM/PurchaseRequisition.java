/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM;

/**
 *
 * @author dhmez
 */


public class PurchaseRequisition {
    private String prID;
    private String itemIDs;     
    private String quantities;  
    private String date;
    private String supplierID;
    private String salesManagerID;
    private String status;

    public PurchaseRequisition(String prID, String itemIDs, String quantities, String date, String supplierID, String salesManagerID, String status) {
        this.prID = prID;
        this.itemIDs = itemIDs;
        this.quantities = quantities;
        this.date = date;
        this.supplierID = supplierID;
        this.salesManagerID = salesManagerID;
        this.status = status;
    }

    public String getPrID() { return prID; }
    public String getItemID() { return itemIDs; }
    public String getQuantity() { return quantities; }
    public String getRequiredDate() { return date; }
    public String getSupplierID() { return supplierID; }
    public String getSalesManagerID() { return salesManagerID; }
    public String getStatus() { return status; }
}
