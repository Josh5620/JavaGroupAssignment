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
    private String itemID;
    private int quantity;
    private String requiredDate;
    private String supplierID;
    private String salesManagerID;
    private String status;

    public PurchaseRequisition(String prID, String itemID, int quantity, String requiredDate,
                                String supplierID, String salesManagerID, String status) {
        this.prID = prID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.requiredDate = requiredDate;
        this.supplierID = supplierID;
        this.salesManagerID = salesManagerID;
        this.status = status;
    }

    public String getPrID() {
        return prID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getSalesManagerID() {
        return salesManagerID;
    }

    public String getStatus() {
        return status;
    }
}
