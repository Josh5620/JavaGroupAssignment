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
    private String date;
    private String itemIDs;
    private String quantities;
    private String status;
    private String pmID;
    private String resolution;

    public PurchaseOrder(String poID, String date, String itemIDs, String quantities, String status, String pmID, String resolution) {
        this.poID = poID;
        this.date = date;
        this.itemIDs = itemIDs;
        this.quantities = quantities;
        this.status = status;
        this.pmID = pmID;
        this.resolution = resolution;
    }

    public String getPoID() { return poID; }
    public String getDate() { return date; }
    public String getItemIDs() { return itemIDs; }
    public String getQuantities() { return quantities; }
    public String getStatus() { return status; }
    public String getPmID() { return pmID; }
    public String getResolution() { return resolution; }

    @Override
    public String toString() {
        return poID + "|" + date + "|" + itemIDs + "|" + quantities + "|" + status + "|" + pmID + "|" + resolution;
    }
}

