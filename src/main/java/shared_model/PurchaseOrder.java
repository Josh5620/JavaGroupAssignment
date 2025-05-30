package shared_model;

import java.util.*;

public class PurchaseOrder {
    private String poID;
    private String date;
    private List<String> itemIDs;
    private List<Integer> quantities;
    private String status;
    private String pmID;
    private String resolution;

    public PurchaseOrder(String poID, String date,
                         List<String> itemIDs,
                         List<Integer> quantities,
                         String status,
                         String pmID,
                         String resolution) {
        this.poID       = poID;
        this.date       = date;
        this.itemIDs    = new ArrayList<>(itemIDs);
        this.quantities = new ArrayList<>(quantities);
        this.status     = status;
        this.pmID       = pmID;
        this.resolution = resolution;
    }

    /** line: PO001|2025-05-17|ITM001/ITM005|20/15|Approved|PM001|Resolved */
    public static PurchaseOrder parse(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 7) return null;

        String id      = parts[0];
        String dt      = parts[1];
        String stat    = parts[4];
        String pm      = parts[5];
        String resol   = parts[6];

        // split itemIDs
        String[] items = parts[2].split("/");
        List<String> itemList = new ArrayList<>();
        for (String s : items) {
            if (!s.isEmpty()) {
                itemList.add(s);
            }
        }

        // split quantities
        String[] qtys = parts[3].split("/");
        List<Integer> qtyList = new ArrayList<>();
        for (String q : qtys) {
            try {
                qtyList.add(Integer.parseInt(q));
            } catch (NumberFormatException e) {
                qtyList.add(0);
            }
        }

        return new PurchaseOrder(id, dt, itemList, qtyList, stat, pm, resol);
    }

    public String toDataLine() {
        // join itemIDs with "/"
        String items = "";
        if (itemIDs.size() > 0) {
            items = itemIDs.get(0);
            for (int i = 1; i < itemIDs.size(); i++) {
                items += "/" + itemIDs.get(i);
            }
        }
        // join quantities with "/"
        String qtys = "";
        if (quantities.size() > 0) {
            qtys = quantities.get(0).toString();
            for (int i = 1; i < quantities.size(); i++) {
                qtys += "/" + quantities.get(i);
            }
        }

        return poID       + "|" +
               date       + "|" +
               items      + "|" +
               qtys       + "|" +
               status     + "|" +
               pmID       + "|" +
               resolution;
    }


    public String        getPoID()       { return poID;       }
    public String        getDate()       { return date;       }
    public List<String>  getItemIDs()    { return Collections.unmodifiableList(itemIDs); }
    public List<Integer> getQuantities() { return Collections.unmodifiableList(quantities); }
    public String        getStatus()     { return status;     }
    public String        getPmID()       { return pmID;       }
    public String        getResolution() { return resolution; }
}
