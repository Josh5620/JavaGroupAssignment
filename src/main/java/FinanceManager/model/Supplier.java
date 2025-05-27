/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;


import java.io.Serializable;
/**
 *
 * @author sumingfei
 */
public class Supplier implements Serializable{
     private static final long serialVersionUID = 1L;

    private int    id;
    private String name;
    private String contactInfo;

    public Supplier(int id, String name, String contactInfo) {
        this.id          = id;
        this.name        = name;
        this.contactInfo = contactInfo;
    }

    public int    getId()           { return id; }
    public String getName()         { return name; }
    public String getContactInfo()  { return contactInfo; }

    // ← Add these if you haven’t already
    public void setName(String name) {
        this.name = name;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
