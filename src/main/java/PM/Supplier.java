/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PM;

/**
 *
 * @author dhmez
 */
import java.util.List;

public class Supplier {
    private String supplierID;
    private String name;
    private String email;
    private String phone;
    private List<String> itemIDs; 

    public Supplier(String supplierID, String name, String email, String phone, List<String> itemIDs) {
        this.supplierID = supplierID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.itemIDs = itemIDs;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setItemIDs(List<String> itemIDs) {
        this.itemIDs = itemIDs;
    }

    
    
    public String getSupplierID() {
        return supplierID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getItemIDs() {
        return itemIDs;
    }

    
    
    
    
}
