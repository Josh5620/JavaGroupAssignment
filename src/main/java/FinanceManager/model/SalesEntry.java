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
public class SalesEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private int     id;
    private String  itemCode;
    private int     quantity;
    private double  pricePerUnit;
    private Date    dateSold;

    /**
     * @param id            Unique identifier for this sale
     * @param itemCode      Which item was sold
     * @param quantity      How many units
     * @param pricePerUnit  Revenue per unit
     * @param dateSold      When the sale occurred
     */
    public SalesEntry(int id, String itemCode, int quantity, double pricePerUnit, Date dateSold) {
        this.id           = id;
        this.itemCode     = itemCode;
        this.quantity     = quantity;
        this.pricePerUnit = pricePerUnit;
        this.dateSold     = dateSold;
    }

    /** Returns the unique sale ID. */
    public int getId() {
        return id;
    }

    /** Returns the item code sold. */
    public String getItemCode() {
        return itemCode;
    }

    /** Returns the number of units sold. */
    public int getQuantity() {
        return quantity;
    }

    /** Returns the per-unit price. */
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    /** Returns the date this sale was made. */
    public Date getDateSold() {
        return dateSold;
    }

    /**
     * Returns total revenue for this sale (quantity × pricePerUnit).
     * Used by your report code’s mapToDouble(se -> se.getAmount()).
     */
    public double getAmount() {
        return quantity * pricePerUnit;
    }

}
