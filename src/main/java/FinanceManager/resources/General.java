/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FinanceManager.resources;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;
import java.io.IOException;
import java.util.List;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import FinanceManager.model.PurchaseRequisition;
import FinanceManager.controller.FinanceManagerController;





/**
 *
 * @author sumingfei
 */
public class General extends javax.swing.JFrame {
    private final CardLayout workAreaCards = new CardLayout();
    private static final String[] CARD_TITLES = {
  "Total Payables",
  "Total Paid This Month",
  "Pending POs",
  "Cash on Hand"
};
private static final String[] CARD_VALUES = {
  "$45,200",
  "$18,950",
  "8",
  "$27,600"
};  
    
    private JPanel cardsBar;
    
    private JPanel buildDashboardPanel() {
        JPanel p = new JPanel(new BorderLayout());
        // reuse the cardsBar you already constructed in the constructor
        p.add(cardsBar, BorderLayout.NORTH);
        return p;
    }

    private JPanel buildPurchaseRequisitionsPanel() {
        // 1) Main panel with padding
    JPanel panel = new JPanel(new BorderLayout(10,10));
    panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

    // 2) Header label
    JLabel header = new JLabel("Purchase Requisitions", SwingConstants.CENTER);
    header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
    panel.add(header, BorderLayout.NORTH);

    // 3) Table setup
    String[] cols = {"ID","Date","Item","Qty","Status"};
    DefaultTableModel model = new DefaultTableModel(cols, 0);
    JTable table = new JTable(model);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);

    // 4) Data‐loader runnable
    Runnable loadData = () -> {
        model.setRowCount(0);
        try {
            List<PurchaseRequisition> prs = FinanceManagerController.getAllRequisitions();
            for (PurchaseRequisition pr : prs) {
                model.addRow(new Object[]{
                    pr.getPrId(),
                    pr.getDate(),
                    pr.getDescription(),
                    pr.getQuantity(),
                    pr.getStatus()
                });
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error loading requisitions:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    };
    loadData.run();  // initial load

    // 5) Buttons at the bottom
    JPanel btnBar = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));
    JButton btnRefresh  = new JButton("Refresh");
    JButton btnApprove  = new JButton("Approve Selected");
    JButton btnNew = new JButton("New Requisition");
btnNew.addActionListener(e -> showNewRequisitionDialog(model, loadData));
btnBar.add(btnNew);


    btnRefresh.addActionListener(e -> loadData.run());

    btnApprove.addActionListener(e -> {
        int row = table.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Please select a PR first.");
        return;
    }
    String id = model.getValueAt(row, 0).toString();
    try {
        FinanceManagerController.approveRequisition(id);
        JOptionPane.showMessageDialog(this, "Approved PR " + id);
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(
            this,
            "Error approving requisition:\n" + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    loadData.run();
    });

    btnBar.add(btnRefresh);
    btnBar.add(btnApprove);
    panel.add(btnBar, BorderLayout.SOUTH);

    return panel;
    }
    
    private void showNewRequisitionDialog(DefaultTableModel model, Runnable reload) {
    JPanel form = new JPanel(new GridLayout(0,2,8,8));
    JTextField txtItem = new JTextField();
    JSpinner spnQty   = new JSpinner(new SpinnerNumberModel(1,1,1000,1));

    form.add(new JLabel("Item Description:"));
    form.add(txtItem);
    form.add(new JLabel("Quantity:"));
    form.add(spnQty);

    int choice = JOptionPane.showConfirmDialog(
        this, form, "New Purchase Requisition",
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
    );
    if (choice == JOptionPane.OK_OPTION) {
        String desc = txtItem.getText().trim();
        int qty     = (Integer) spnQty.getValue();
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an item description.");
            return;
        }
        try {
            FinanceManagerController.createRequisition(desc, qty);
            JOptionPane.showMessageDialog(this, "Requisition created!");
            reload.run();  // refresh the table
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Error creating requisition:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}


    private JPanel buildPurchaseOrdersPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Purchase Orders", SwingConstants.CENTER),
              BorderLayout.CENTER);
        return p;
    }

    private JPanel buildInventoryPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Inventory", SwingConstants.CENTER),
              BorderLayout.CENTER);
        return p;
    }

    private JPanel buildProcessPaymentsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Process Payments", SwingConstants.CENTER),
              BorderLayout.CENTER);
        return p;
    }

    private JPanel buildReportsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Reports", SwingConstants.CENTER),
              BorderLayout.CENTER);
        return p;
    }

    private JPanel buildSettingsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Settings", SwingConstants.CENTER),
              BorderLayout.CENTER);
        return p;
    }
    
    

    /**
     * Creates new form General
     */
    public General() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
            
            // now start your GUI exactly once, with FlatDarkLaf active
//<<<<<<< Updated upstream
            //SwingUtilities.invokeLater(() -> new General().setVisible(true));
//=======
            //SwingUtilities.invokeLater(() -> new General().setVisible(true));

//>>>>>>> Stashed changes
        }
        initComponents();
        
        
        // switch to CardLayout
        workAreaPanel.removeAll();
        workAreaPanel.setLayout(workAreaCards);
        // Build the cards bar and assign to the field
// 1) Build the cards bar and assign to the field
cardsBar = new JPanel(new GridLayout(1, CARD_TITLES.length, 16, 0));
cardsBar.setOpaque(false);
for (int i = 0; i < CARD_TITLES.length; i++) {
    cardsBar.add(makeSummaryCard(CARD_TITLES[i], CARD_VALUES[i]));
}

// 2) Add it as the NORTH “card” in workAreaPanel
workAreaPanel.add(cardsBar, BorderLayout.NORTH);

        // register each “page” panel under a name
        workAreaPanel.add(buildDashboardPanel(),             "DASHBOARD");
        workAreaPanel.add(buildPurchaseRequisitionsPanel(),  "PR");
        workAreaPanel.add(buildPurchaseOrdersPanel(),        "PO");
        workAreaPanel.add(buildInventoryPanel(),             "INV");
        workAreaPanel.add(buildProcessPaymentsPanel(),       "PAY");
        workAreaPanel.add(buildReportsPanel(),               "REP");
        workAreaPanel.add(buildSettingsPanel(),              "SET");

// show DASHBOARD by default
workAreaCards.show(workAreaPanel, "DASHBOARD");
        
        // 1) Build the cards bar
        JPanel cardsBar = new JPanel(new GridLayout(1, 4, 16, 0));
        cardsBar.setOpaque(false);

        String[] titles = {
            "Total Payables",
            "Total Paid This Month",
            "Pending POs",
            "Cash on Hand"
        };
        String[] values = {"$45,200", "$18,950", "8", "$27,600"};

        for (int i = 0; i < titles.length; i++) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(0x2E4053));
            card.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

            JLabel lblT = new JLabel(titles[i], SwingConstants.CENTER);
            lblT.setForeground(Color.WHITE);
            lblT.setFont(lblT.getFont().deriveFont(Font.PLAIN, 14f));

            JLabel lblV = new JLabel(values[i], SwingConstants.CENTER);
            lblV.setForeground(Color.WHITE);
            lblV.setFont(lblV.getFont().deriveFont(Font.BOLD, 24f));

            card.add(lblT, BorderLayout.NORTH);
            card.add(lblV, BorderLayout.CENTER);
            cardsBar.add(card);
        }

// 2) Add cardsBar to the top of workAreaPanel
        workAreaPanel.add(cardsBar, BorderLayout.NORTH);


        
        
        jPanel2.setBackground(new Color(0x1F2A36));

        
        getContentPane().setBackground(new Color(0x2C3E50));

        
        lblGreeting.setForeground(Color.WHITE);

        
        Stream.of(
                btnDashboard,
                btnPurchaseRequisitions,
                btnPurchaseOrders,
                btnInventory,
                btnProcessPayments,
                btnReports,
                btnSettings
        ).forEach(b -> {
            b.setBackground(new Color(0x34495E));
            b.setForeground(Color.WHITE);
            b.setFont(b.getFont().deriveFont(Font.BOLD, 14f));
            b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        });
        
        // --------------- summary cards ----------------


        // Start the live clock
        startDateTimeUpdater();
        
        


    }
    
    
    
    private void startDateTimeUpdater() {
        final SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMMM dd, yyyy  HH:mm:ss");
        Timer t = new Timer(1000, e -> {
            Date now = new Date();
            lblGreeting.setText(
                    "Hi, welcome finance manager Leo! Today's date is "
                    + fmt.format(now)
            );
        });
        t.setInitialDelay(0);
        t.start();
    }
    
    
    
        
        
        
        
        
        
        
        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblGreeting = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnInventory = new javax.swing.JButton();
        btnProcessPayments = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnPurchaseOrders = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnColorMode = new javax.swing.JLabel();
        btnPurchaseRequisitions = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        workAreaPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblWorkArea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLogo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FinanceManager/resources/logo.jpeg"))); // NOI18N
        lblLogo.setText("jLabel1");

        lblGreeting.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblGreeting.setText("Hi, welcome finance manager Leo! ");

        btnInventory.setText("Inventory");
        btnInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventoryActionPerformed(evt);
            }
        });

        btnProcessPayments.setText("Process Payments");
        btnProcessPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessPaymentsActionPerformed(evt);
            }
        });

        btnReports.setText("Reports");
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });

        btnPurchaseOrders.setText(" Purchase Orders");
        btnPurchaseOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseOrdersActionPerformed(evt);
            }
        });

        btnSettings.setText("Settings");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        btnColorMode.setText("Dark mdoe");

        btnPurchaseRequisitions.setText("Purchase Requisitions");
        btnPurchaseRequisitions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPurchaseRequisitionsActionPerformed(evt);
            }
        });

        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnColorMode)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPurchaseOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProcessPayments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(btnSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPurchaseRequisitions, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnPurchaseRequisitions, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPurchaseOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProcessPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReports, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnColorMode)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        workAreaPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout lblWorkAreaLayout = new javax.swing.GroupLayout(lblWorkArea);
        lblWorkArea.setLayout(lblWorkAreaLayout);
        lblWorkAreaLayout.setHorizontalGroup(
            lblWorkAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        lblWorkAreaLayout.setVerticalGroup(
            lblWorkAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(271, 271, 271)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(workAreaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblWorkArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 108, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(workAreaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(664, 664, 664)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblWorkArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(6356, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
workAreaCards.show(workAreaPanel, "DASHBOARD");      

// TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnPurchaseRequisitionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseRequisitionsActionPerformed
        

           workAreaCards.show(workAreaPanel, "PR");
    }//GEN-LAST:event_btnPurchaseRequisitionsActionPerformed

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
workAreaCards.show(workAreaPanel, "INV");        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void btnPurchaseOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPurchaseOrdersActionPerformed
workAreaCards.show(workAreaPanel, "PO");        
    }//GEN-LAST:event_btnPurchaseOrdersActionPerformed

    private void btnProcessPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessPaymentsActionPerformed
workAreaCards.show(workAreaPanel, "PAY");        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcessPaymentsActionPerformed

    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
workAreaCards.show(workAreaPanel, "REP");        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportsActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
workAreaCards.show(workAreaPanel, "SET");        // TODO add your handling code here:
    }//GEN-LAST:event_btnSettingsActionPerformed

    
    private JPanel makeSummaryCard(String title, String value) {
    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(new Color(0x2E4053));
    card.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

    JLabel lblT = new JLabel(title, SwingConstants.CENTER);
    lblT.setForeground(Color.WHITE);
    lblT.setFont(lblT.getFont().deriveFont(Font.PLAIN, 14f));

    JLabel lblV = new JLabel(value, SwingConstants.CENTER);
    lblV.setForeground(Color.WHITE);
    lblV.setFont(lblV.getFont().deriveFont(Font.BOLD, 24f));

    card.add(lblT, BorderLayout.NORTH);
    card.add(lblV, BorderLayout.CENTER);

    return card;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.out.println(">>> General.main() starting!");   // <<<< add this
    java.awt.EventQueue.invokeLater(() -> {
        new General().setVisible(true);
    });
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new General().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnColorMode;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnProcessPayments;
    private javax.swing.JButton btnPurchaseOrders;
    private javax.swing.JButton btnPurchaseRequisitions;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnSettings;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel lblWorkArea;
    private javax.swing.JPanel workAreaPanel;
    // End of variables declaration//GEN-END:variables
}
