/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SM;

/**
 *
 * @author dhoom
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import shared_manager.SupplierManager;
import shared_model.Supplier;
public class SupplierEntryPanel extends javax.swing.JPanel {
    private final SupplierManager supplierManager = new SupplierManager();


   

    /**
     * Creates new form SupplierEntryPanel
     */
    
    public SupplierEntryPanel() {
        
        initComponents();
        loadSuppliers();
        loadItemsToComboBox();
        setupTableSelection();
        
        
    }
    
    private void loadSuppliers() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "ItemIDs"}, 0);
        try {
            List<Supplier> suppliers = supplierManager.loadSuppliers();  // OOP
            for (Supplier s : suppliers) {
                model.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getItemIDs()
                });
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage());
    }

    tblSuppliers.setModel(model);
    try {
        txtSupplierID.setText(supplierManager.generateNextSupplierID());
    } catch (IOException e) {
        txtSupplierID.setText("Error");
    }
}
    
    private void loadItemsToComboBox() {
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow >= 0) {
            String supplierID = tblSuppliers.getValueAt(selectedRow, 0).toString();

            comboItemID.removeAllItems();  

            try (BufferedReader br = new BufferedReader(new FileReader("src/Items.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 4 && parts[3].equals(supplierID)) {
                        comboItemID.addItem(parts[0]);  
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading items for the selected supplier: " + e.getMessage());
            }
        }
    }
    
    private void setupTableSelection() {
        tblSuppliers.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblSuppliers.getSelectedRow();
            if (selectedRow >= 0) {
                txtSupplierID.setText(tblSuppliers.getValueAt(selectedRow, 0).toString());
                txtName.setText(tblSuppliers.getValueAt(selectedRow, 1).toString());
                txtEmail.setText(tblSuppliers.getValueAt(selectedRow, 2).toString());
                txtPhone.setText(tblSuppliers.getValueAt(selectedRow, 3).toString());

                loadItemsToComboBox();  
            }
        });
    }
    
    
    private void clearFields() {
        txtSupplierID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        comboItemID.removeAllItems();
        
        try {
            txtSupplierID.setText(supplierManager.generateNextSupplierID());
        } catch (IOException e) {
            txtSupplierID.setText("Error");
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

        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSupplierID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        comboItemID = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Supplier ID");

        txtSupplierID.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        a.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        a.setText("Email");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Phone");

        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Name", "Email", "Phone", "ItemID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSuppliers);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Item ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSupplierID)
                            .addComponent(txtName)
                            .addComponent(txtPhone)
                            .addComponent(txtEmail)
                            .addComponent(comboItemID, 0, 110, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addGap(210, 210, 210))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String id = txtSupplierID.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            Supplier newSupplier = new Supplier(id, name, email, phone, "NoItems");  
            supplierManager.addSupplier(newSupplier);  // OOP method call
            loadSuppliers();  
            clearFields();  
            JOptionPane.showMessageDialog(this, "Supplier added successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error adding supplier: " + ex.getMessage());
}
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String id = txtSupplierID.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String selectedItem = (String) comboItemID.getSelectedItem();

        if (id.isEmpty() || selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select a supplier and an item.");
            return;
        }

        try {
            Supplier updatedSupplier = new Supplier(id, name, email, phone, "");  // OOP: ItemIDs handled in editSupplier
            supplierManager.editSupplier(updatedSupplier, selectedItem);  
            loadSuppliers();  // Refresh table
            clearFields();  // Clear fields
            JOptionPane.showMessageDialog(this, "Supplier updated successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error editing supplier: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a supplier to delete.");
            return;
        }

        String id = tblSuppliers.getValueAt(selectedRow, 0).toString();
        try {
            supplierManager.deleteSupplier(id);
            loadSuppliers(); clearFields();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting supplier: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtSupplierID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        try {
            txtSupplierID.setText(supplierManager.generateNextSupplierID());
        } catch (IOException e) {
            txtSupplierID.setText("Error");
        }
        
    }//GEN-LAST:event_btnClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> comboItemID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSupplierID;
    // End of variables declaration//GEN-END:variables
}
