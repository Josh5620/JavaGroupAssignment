package shared_model;

import java.util.*;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String email;
    private String contact;
    private List<String> itemIDs;

    public Supplier(String supplierID, String supplierName,
                    String email, String contact,
                    List<String> itemIDs) {
        this.supplierID   = supplierID;
        this.supplierName = supplierName;
        this.email        = email;
        this.contact      = contact;
        this.itemIDs      = new ArrayList<>(itemIDs);
    }

    /** line format: 
     *  supplierID|supplierName|email|contact|itemID1,itemID2,... 
     */
    public static Supplier parse(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) return null;
        List<String> ids = parts[4].isEmpty()
            ? Collections.emptyList()
            : Arrays.asList(parts[4].split(","));
        return new Supplier(
            parts[0],     // supplierID
            parts[1],     // supplierName
            parts[2],     // email
            parts[3],     // contact
            ids
        );
    }

    public String toDataLine() {
        String joined = itemIDs.isEmpty()
            ? ""
            : String.join(",", itemIDs);
        return supplierID   + "|" +
               supplierName + "|" +
               email        + "|" +
               contact      + "|" +
               joined;
    }

    public String        getSupplierID()   { return supplierID;   }
    public String        getSupplierName() { return supplierName; }
    public String        getEmail()        { return email;        }
    public String        getContact()      { return contact;      }
    public List<String>  getItemIDs()      { return Collections.unmodifiableList(itemIDs); }

    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public void setEmail(String email)               { this.email        = email;        }
    public void setContact(String contact)           { this.contact      = contact;      }
    public void setItemIDs(List<String> itemIDs) {
        this.itemIDs = new ArrayList<>(itemIDs);
    }
}
