package shared_model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseRequisition {
    private String prID;
    private List<String> itemIDs;
    private List<Integer> quantities;
    private String date;
    private String supplierID;
    private String salesManagerID;
    private String status;

    // Constructor
    public PurchaseRequisition(String prID, List<String> itemIDs, List<Integer> quantities,
                                String date, String supplierID, String salesManagerID, String status) {
        this.prID = prID;
        this.itemIDs = itemIDs;
        this.quantities = quantities;
        this.date = date;
        this.supplierID = supplierID;
        this.salesManagerID = salesManagerID;
        this.status = status;
    }

    // Static parser from text file line
    public static PurchaseRequisition parse(String line) throws IllegalArgumentException {
        String[] parts = line.split("\\|");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid PR line format: " + line);
        }

        List<String> itemIDs = Arrays.asList(parts[1].split("/"));
        String[] qtyParts = parts[2].split("/");
        if (itemIDs.size() != qtyParts.length) {
            throw new IllegalArgumentException("Mismatch in itemIDs and quantities for PR: " + parts[0]);
        }

        List<Integer> quantities = Arrays.stream(qtyParts)
                                         .map(Integer::parseInt)
                                         .collect(Collectors.toList());

        return new PurchaseRequisition(
            parts[0], itemIDs, quantities, parts[3], parts[4], parts[5], parts[6]
        );
    }

    // Converts object to text file line
    public String toDataLine() {
        String itemStr = String.join("/", itemIDs);
        String qtyStr = quantities.stream()
                                  .map(String::valueOf)
                                  .collect(Collectors.joining("/"));
        return String.join("|", prID, itemStr, qtyStr, date, supplierID, salesManagerID, status);
    }

    // Getters and Setters
    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }

    public List<String> getItemIDs() {
        return itemIDs;
    }

    public void setItemIDs(List<String> itemIDs) {
        this.itemIDs = itemIDs;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSalesManagerID() {
        return salesManagerID;
    }

    public void setSalesManagerID(String salesManagerID) {
        this.salesManagerID = salesManagerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Debugging utility
    @Override
    public String toString() {
        return "PR{" +
                "prID='" + prID + '\'' +
                ", itemIDs=" + itemIDs +
                ", quantities=" + quantities +
                ", date='" + date + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", salesManagerID='" + salesManagerID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
