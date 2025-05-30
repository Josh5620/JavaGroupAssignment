package shared_model;

import java.util.*;

public class PurchaseRequisition {
    private String requisitionID;
    private List<String> itemIDs;
    private List<Integer> quantities;
    private String requiredDate;
    private String supplierID;
    private String smID;
    private String status;

    public PurchaseRequisition(String requisitionID,
                               List<String> itemIDs,
                               List<Integer> quantities,
                               String requiredDate,
                               String supplierID,
                               String smID,
                               String status) {
        this.requisitionID = requisitionID;
        this.itemIDs       = new ArrayList<>(itemIDs);
        this.quantities    = new ArrayList<>(quantities);
        this.requiredDate  = requiredDate;
        this.supplierID    = supplierID;
        this.smID          = smID;
        this.status        = status;
    }


    public static PurchaseRequisition parse(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 7) return null;

        String id       = parts[0];
        String date     = parts[3];
        String suppID   = parts[4];
        String sm       = parts[5];
        String stat     = parts[6];


        String[] items = parts[1].split("/");
        List<String> itemList = new ArrayList<>();
        for (String s : items) {
            if (!s.isEmpty()) itemList.add(s);
        }


        String[] qtys = parts[2].split("/");
        List<Integer> qtyList = new ArrayList<>();
        for (String q : qtys) {
            try {
                qtyList.add(Integer.parseInt(q));
            } catch (NumberFormatException e) {
                qtyList.add(0);
            }
        }

        return new PurchaseRequisition(id, itemList, qtyList, date, suppID, sm, stat);
    }

    public String toDataLine() {
        // join itemIDs with "/"
        String items = "";
        if (!itemIDs.isEmpty()) {
            items = itemIDs.get(0);
            for (int i = 1; i < itemIDs.size(); i++) {
                items += "/" + itemIDs.get(i);
            }
        }

        // join quantities with "/"
        String qtys = "";
        if (!quantities.isEmpty()) {
            qtys = quantities.get(0).toString();
            for (int i = 1; i < quantities.size(); i++) {
                qtys += "/" + quantities.get(i);
            }
        }

        return requisitionID + "|" +
               items         + "|" +
               qtys          + "|" +
               requiredDate  + "|" +
               supplierID    + "|" +
               smID          + "|" +
               status;
    }


    public String        getRequisitionID() { return requisitionID; }
    public List<String>  getItemIDs()       { return Collections.unmodifiableList(itemIDs); }
    public List<Integer> getQuantities()    { return Collections.unmodifiableList(quantities); }
    public String        getRequiredDate()  { return requiredDate; }
    public String        getSupplierID()    { return supplierID; }
    public String        getSmID()          { return smID; }
    public String        getStatus()        { return status; }

    public void setStatus(String status)      { this.status = status; }
    public void setRequiredDate(String date)  { this.requiredDate = date; }
    public void setSupplierID(String id)      { this.supplierID = id;  }
    public void setSmID(String smID)          { this.smID = smID;      }
    public void setItemIDs(List<String> ids) {
        this.itemIDs = new ArrayList<>(ids);
    }
    public void setQuantities(List<Integer> q) {
        this.quantities = new ArrayList<>(q);
    }
}
