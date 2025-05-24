/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
public class Supplier {
    private String supplierID;
    private String name;
    private String email;
    private String phone;
    private String itemIDs;

    public Supplier(String supplierID, String name, String email, String phone, String itemIDs) {
        this.supplierID = supplierID;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getItemIDs() {
        return itemIDs;
    }

    public String toString() {
        return supplierID + "|" + name + "|" + email + "|" + phone + "|" + itemIDs;
    }
}