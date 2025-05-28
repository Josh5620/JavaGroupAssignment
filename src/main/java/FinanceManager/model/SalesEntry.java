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

  private int    id;
  private String itemCode;
  private int    quantity;
  private double pricePerUnit;
  private Date   dateSold;
    
}
