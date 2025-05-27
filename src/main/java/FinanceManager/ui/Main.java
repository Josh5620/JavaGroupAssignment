/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinanceManager.ui;

import javax.swing.SwingUtilities;

/**
 *
 * @author sumingfei
 */
public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new FMForm().setVisible(true);
    });
  }
    
}
