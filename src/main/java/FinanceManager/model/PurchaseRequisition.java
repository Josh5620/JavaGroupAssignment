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
public class PurchaseRequisition implements Serializable{
      private String prId;
    private String itemIds;
    private int quantity;
    private Date dateRequested;
    private String supplierId;
    private String salesMgrId;
    private String status;

    public PurchaseRequisition(
        String prId,
        String itemIds,
        int quantity,
        Date dateRequested,
        String supplierId,
        String salesMgrId,
        String status
    ) {
        this.prId = prId;
        this.itemIds = itemIds;
        this.quantity = quantity;
        this.dateRequested = dateRequested;
        this.supplierId = supplierId;
        this.salesMgrId = salesMgrId;
        this.status = status;
    }

    public String getPrId()          { return prId; }
    public String getItemIds()       { return itemIds; }
    public int    getQuantity()      { return quantity; }
    public Date   getDateRequested() { return dateRequested; }
    public String getSupplierId()    { return supplierId; }
    public String getSalesMgrId()    { return salesMgrId; }
    public String getStatus()        { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
