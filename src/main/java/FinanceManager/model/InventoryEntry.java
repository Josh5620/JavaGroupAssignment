/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author sumingfei
 */
public class InventoryEntry implements Serializable{
      private static final long serialVersionUID = 1L;

  private int    id;
  private String itemCode;
  private int    quantity;
  private Date   dateReceived;
  private int    supplierId;

  /** full constructor */
  public InventoryEntry(int id,
                        String itemCode,
                        int quantity,
                        Date dateReceived,
                        int supplierId) {
    this.id           = id;
    this.itemCode     = itemCode;
    this.quantity     = quantity;
    this.dateReceived = dateReceived;
    this.supplierId   = supplierId;
  }
  
  public void setQuantity(int quantity) {
  this.quantity = quantity;
}


  // getters…
  public int    getId()           { return id; }
  public String getItemCode()     { return itemCode; }
  public int    getQuantity()     { return quantity; }
  public Date   getDateReceived() { return dateReceived; }
  public int    getSupplierId()   { return supplierId; }
    
}
