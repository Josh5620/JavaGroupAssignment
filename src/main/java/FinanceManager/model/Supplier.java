/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;


import java.io.Serializable;
import java.util.List;
/**
 *
 * @author sumingfei
 */
public class Supplier implements Serializable{
     private static final long serialVersionUID = 1L;

    private int           id;
    private String        name;
    private String        email;
    private String        contactInfo;
    private List<String>  itemCodes;    // the “last thing” column
    
    public Supplier(int id, String name, String email, String contactInfo, List<String> itemCodes) {
        this.id           = id;
        this.name         = name;
        this.email        = email;
        this.contactInfo  = contactInfo;
        this.itemCodes    = itemCodes;
    }

    public int           getId()          { return id; }
    public String        getName()        { return name; }
    public String        getEmail()       { return email; }
    public String        getContactInfo() { return contactInfo; }
    public List<String>  getItemCodes()   { return itemCodes; }

    public void setName(String n)            { this.name        = n; }
    public void setEmail(String e)           { this.email       = e; }
    public void setContactInfo(String c)     { this.contactInfo = c; }
    public void setItemCodes(List<String> l) { this.itemCodes   = l; }
}
