/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
public class DailySalesEntryPanel extends javax.swing.JPanel {
        private final SalesManager salesManager = new SalesManager();

    /**
     * Creates new form DailySalesEntryPanel
     */
    public DailySalesEntryPanel() {
        initComponents();
            loadSales();             
        loadItemsToComboBox();   
        setupTableSelection();
        clearFields();
      
    }
    
    private void loadSales() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"SaleID", "ItemID", "Date", "Quantity", "UnitPrice", "Total"}, 0);
        try {
            List<Sale> sales = salesManager.loadSales();  // OOP
            for (Sale s : sales) {
                model.addRow(new Object[]{
                    s.getSaleID(),
                    s.getItemID(),
                    s.getDate(),
                    s.getQuantity(),
                    s.getUnitPrice(),
                    s.getTotal()
                });
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading sales: " + e.getMessage());
        }

        tblSales.setModel(model);

        try {
            txtSaleID.setText(salesManager.generateNextSaleID());
        } catch (IOException e) {
            txtSaleID.setText("Error");
        }
    }
    
    private void loadItemsToComboBox() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    comboItemID.addItem(parts[0]);  
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading items: " + e.getMessage());
        }
    }
    
    
    
    private void updateUnitPrice() {
        String selectedItem = (String) comboItemID.getSelectedItem();
        if (selectedItem != null) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/Items.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts[0].equals(selectedItem)) {
                        txtUnitPrice.setText(parts[2]); 
                        updateTotal();
                        return;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading unit price: " + e.getMessage());
            }   
        }
    }
    
    private void updateTotal() {
        try {
            int qty = Integer.parseInt(txtQuantity.getText().trim());
            double price = Double.parseDouble(txtUnitPrice.getText().trim());
            txtTotal.setText(String.format("%.2f", qty * price));
        } catch (NumberFormatException ignored) {
            txtTotal.setText("");
        }
    }
    
    private void clearFields() {
        txtSaleID.setText("");
        comboItemID.setSelectedIndex(0);
        txtDate.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");
        txtTotal.setText("");
        try {
            txtSaleID.setText(salesManager.generateNextSaleID());
        } catch (IOException e) {
            txtSaleID.setText("Error");
        }
    }
    
    private void setupTableSelection() {
        tblSales.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = tblSales.getSelectedRow();
            if (selectedRow >= 0) {
                txtSaleID.setText(tblSales.getValueAt(selectedRow, 0).toString());
                comboItemID.setSelectedItem(tblSales.getValueAt(selectedRow, 1).toString());
                txtDate.setText(tblSales.getValueAt(selectedRow, 2).toString());
                txtQuantity.setText(tblSales.getValueAt(selectedRow, 3).toString());
                txtUnitPrice.setText(tblSales.getValueAt(selectedRow, 4).toString());
                txtTotal.setText(tblSales.getValueAt(selectedRow, 5).toString());
            }
        });
    }
    
    private void updateQuantity() {
        String selectedItem = (String) comboItemID.getSelectedItem();
        if (selectedItem != null) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/Inventory.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts[0].equals(selectedItem)) {
                        txtQuantity.setText(parts[2]);  
                        updateTotal();  
                        return;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading quantity: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSaleID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboItemID = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        ASD = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        txtSaleID.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Sale ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Item ID");

        comboItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboItemIDActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Date");

        ASD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ASD.setText("Quantity");

        txtQuantity.setEditable(false);
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Unit Price");

        txtUnitPrice.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        tblSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "saleID", "ItemID", "Date", "Quantity", "Unit Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSales);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ASD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSaleID)
                            .addComponent(comboItemID, 0, 110, Short.MAX_VALUE)
                            .addComponent(txtDate)
                            .addComponent(txtQuantity)
                            .addComponent(txtUnitPrice)
                            .addComponent(txtTotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(comboItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ASD)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnEdit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String saleID = txtSaleID.getText().trim();
        String itemID = (String) comboItemID.getSelectedItem();
        String date = txtDate.getText().trim();
        String quantity = txtQuantity.getText().trim();
        String unitPrice = txtUnitPrice.getText().trim();
        String total = txtTotal.getText().trim();

        if (itemID == null || date.isEmpty() || quantity.isEmpty() || unitPrice.isEmpty() || total.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        for (int i = 0; i < tblSales.getRowCount(); i++) {
            if (tblSales.getValueAt(i, 0).equals(saleID)) {
                JOptionPane.showMessageDialog(this, "Sale ID already exists.");
                return;
            }
        }

        try {
            Sale newSale = new Sale(saleID, itemID, date, quantity, unitPrice, total);
            salesManager.addSale(newSale);
            loadSales();
            clearFields();
            JOptionPane.showMessageDialog(this, "Sale added successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error adding sale: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String saleID = txtSaleID.getText().trim();
        String itemID = (String) comboItemID.getSelectedItem();
        String date = txtDate.getText().trim();
        String quantity = txtQuantity.getText().trim();
        String unitPrice = txtUnitPrice.getText().trim();
        String total = txtTotal.getText().trim();

        if (saleID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a sale to edit.");
            return;
        }

        try {
            Sale updatedSale = new Sale(saleID, itemID, date, quantity, unitPrice, total);
            salesManager.editSale(updatedSale);
            loadSales();
            clearFields();
            JOptionPane.showMessageDialog(this, "Sale updated successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error editing sale: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       int selectedRow = tblSales.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a sale to delete.");
            return;
        }
        String saleID = tblSales.getValueAt(selectedRow, 0).toString();
        try {
            salesManager.deleteSale(saleID);
            loadSales();
            clearFields();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting sale: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        updateTotal();
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void comboItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboItemIDActionPerformed
        updateUnitPrice();
        updateQuantity();
    }//GEN-LAST:event_comboItemIDActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        updateTotal();
    }//GEN-LAST:event_txtTotalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ASD;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> comboItemID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSales;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSaleID;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
