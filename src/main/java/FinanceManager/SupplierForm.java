/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FinanceManager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import FinanceManager.manager.SupplierManager;
import FinanceManager.model.Supplier;
import java.io.File;
/**
 *
 * @author sumingfei
 */
public class SupplierForm extends javax.swing.JFrame {
    
    private SupplierManager      sMgr       = new SupplierManager();
    private DefaultTableModel    tableModel;
    private DefaultTableModel tableModelSuppliers;
    // (tblSuppliers, btnLoadSuppliers, etc. are generated for you)
    private static final String DATA_DIR = "test" + File.separator;

    /**
     * Creates new form SupplierForm
     */
    public SupplierForm() {
        initComponents();
        tableModelSuppliers = (DefaultTableModel) tblSuppliers.getModel();
        loadSuppliers();
        
            // 1) load persisted list
    try {
        sMgr.loadFromFile(DATA_DIR + "Suppliers.txt");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Could not load suppliers: " + e.getMessage());
    }

    // 2) set up the table model
    tableModel = new DefaultTableModel(new String[]{"ID","Name","Email","Contact","Items"}, 0);
    tblSuppliers.setModel(tableModel);
    tableModelSuppliers = tableModel;
    tblSuppliers.setAutoResizeMode(tblSuppliers.AUTO_RESIZE_OFF);
    tblSuppliers.getColumnModel().getColumn(0).setPreferredWidth(40);
    tblSuppliers.getColumnModel().getColumn(1).setPreferredWidth(120);
    tblSuppliers.getColumnModel().getColumn(2).setPreferredWidth(180);
    tblSuppliers.getColumnModel().getColumn(3).setPreferredWidth(100);
    tblSuppliers.getColumnModel().getColumn(4).setPreferredWidth(200);


    // 3) wire buttons
    btnLoadSuppliers .addActionListener(e -> loadSuppliers());
    btnAddSupplier   .addActionListener(e -> addSupplier());
    btnEditSupplier  .addActionListener(e -> editSupplier());
    btnRemoveSupplier.addActionListener(e -> removeSupplier());

    // 4) initial load
    loadSuppliers();

    // 5) save on close
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        try {
          sMgr.saveToFile("Suppliers.txt");
        } catch (IOException ex) { ex.printStackTrace(); }
      }
    });

    }
    
    private void loadSuppliers() {
      tableModelSuppliers.setRowCount(0);
  for (Supplier s : sMgr.getAll()) {
    tableModelSuppliers.addRow(new Object[]{
      s.getId(),
      s.getName(),
      s.getEmail(),
      s.getContactInfo(),
      String.join(",", s.getItemCodes())  // however you store their catalog
    });
  }
}

    private void addSupplier() {
    String name    = JOptionPane.showInputDialog(this, "Supplier name:");
    if (name == null || name.isBlank()) return;

    String email   = JOptionPane.showInputDialog(this, "Supplier email:");
    if (email == null || email.isBlank()) return;

    String contact = JOptionPane.showInputDialog(this, "Contact number:");
    if (contact == null || contact.isBlank()) return;

    String itemsCsv = JOptionPane.showInputDialog(this,
       "Item codes (comma-separated), e.g. ITM001,ITM002:");
    List<String> items = List.of(itemsCsv.split("\\s*,\\s*"));

    int newId = sMgr.getAll().size() + 1;
    Supplier s = new Supplier(newId, name, email, contact, items);
    sMgr.add(s);
    loadSuppliers();
}

    private void editSupplier() {
int row = tblSuppliers.getSelectedRow();
if (row < 0) return;
// grab the String ID from the table, not an int
String id = tableModel.getValueAt(row, 0).toString();
Supplier s = sMgr.findById(id);



    String name    = JOptionPane.showInputDialog(this, "Name:", s.getName());
    if (name != null)    s.setName(name);

    String email   = JOptionPane.showInputDialog(this, "Email:", s.getEmail());
    if (email != null)   s.setEmail(email);

    String contact = JOptionPane.showInputDialog(this, "Contact:", s.getContactInfo());
    if (contact != null) s.setContactInfo(contact);

    String itemsCsv = JOptionPane.showInputDialog(this,
       "Items (comma-separated):", String.join(",", s.getItemCodes()));
    if (itemsCsv != null) {
        s.setItemCodes(List.of(itemsCsv.split("\\s*,\\s*")));
    }

    loadSuppliers();
}

    private void removeSupplier() {
int row = tblSuppliers.getSelectedRow();
if (row < 0) return;
// grab the String ID from the table, not an int
String id = tableModel.getValueAt(row, 0).toString();
Supplier s = sMgr.findById(id);

    sMgr.getAll().remove(s);
    loadSuppliers();
}
   


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAddSupplier = new javax.swing.JButton();
        btnEditSupplier = new javax.swing.JButton();
        btnRemoveSupplier = new javax.swing.JButton();
        btnLoadSuppliers = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddSupplier.setText("Add Supplier");

        btnEditSupplier.setText("Edit Supplier");

        btnRemoveSupplier.setText("Remove Supplier");

        btnLoadSuppliers.setText("Load Suppliers");

        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSuppliers);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLoadSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLoadSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SupplierForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierForm();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnEditSupplier;
    private javax.swing.JButton btnLoadSuppliers;
    private javax.swing.JButton btnRemoveSupplier;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSuppliers;
    // End of variables declaration//GEN-END:variables
}
