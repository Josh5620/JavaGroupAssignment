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
    public static final String File_Path = "src/PurchaseOrders.txt";


    public PurchaseOrder(String poID, String date, String itemIDs, String quantities, String status, String pmID, String resolution) {
        this.poID = poID;
        this.date = date;
        this.itemIDs = itemIDs;
        this.quantities = quantities;
        this.status = status;
        this.pmID = pmID;
        this.resolution = resolution;
    }

    public void setPoID(String poID) {
        this.poID = poID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemIDs(String itemIDs) {
        this.itemIDs = itemIDs;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPmID(String pmID) {
        this.pmID = pmID;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    
    public String getPoID() { return poID; }
    public String getDate() { return date; }
    public String getItemIDs() { return itemIDs; }
    public String getQuantities() { return quantities; }
    public String getStatus() { return status; }
    public String getPmID() { return pmID; }
    public String getResolution() { return resolution; }

    public String getFilePath() {
        return File_Path;
    }
    
    

    
    
    @Override
    public String toString() {
        return poID + "|" + date + "|" + itemIDs + "|" + quantities + "|" + status + "|" + pmID + "|" + resolution;
    }
}

