/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FinanceManager.ui;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout; 
import javax.swing.JButton;
import javax.swing.JTable;

import FinanceManager.manager.PurchaseOrderManager;
import FinanceManager.manager.PurchaseRequisitionManager;
import FinanceManager.manager.SupplierManager;
import FinanceManager.model.PurchaseOrder;
import FinanceManager.model.PurchaseRequisition;
import FinanceManager.model.Supplier;
import FinanceManager.MailUtil;
import FinanceManager.manager.InventoryManager;
import FinanceManager.model.InventoryEntry;
import FinanceManager.model.SalesEntry;
import FinanceManager.manager.SalesManager;




/**
 *
 * @author sumingfei
 */
public class FMForm extends javax.swing.JFrame {
    private SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
    private PurchaseRequisitionManager prMgr = new PurchaseRequisitionManager();
    private PurchaseOrderManager       poMgr = new PurchaseOrderManager();
    private SupplierManager            sMgr  = new SupplierManager();        // <— make sure this is here
    private InventoryManager invMgr = new InventoryManager();
    private SalesManager               salesMgr = new SalesManager();
    
    private DefaultTableModel tableModelRequisitions;
    private DefaultTableModel tableModelOrders;
    
    


    private DefaultTableModel tableModel;



    /**
     * Creates new form FMForm
     */
    public FMForm() {
        initComponents();
        
        try {
        prMgr .loadFromFile("prs.dat");
        poMgr .loadFromFile("pos.dat");
        sMgr  .loadFromFile("suppliers.dat");
        invMgr.loadFromFile("inventory.dat");
        salesMgr.loadFromFile("sales.dat");     // ← load your sales entries
        } catch(IOException e) {
        JOptionPane.showMessageDialog(this,
        "Failed to load data: "+e.getMessage(),
         "Load Error", JOptionPane.ERROR_MESSAGE);
}
        
        tableModelRequisitions = (DefaultTableModel) tblRecords.getModel();

        // and you probably already have:
        tableModelOrders       = (DefaultTableModel) tblOrders     .getModel();

    


        
        
        // add suppliers menu entry
        JMenuItem miSuppliers = new JMenuItem("Manage Suppliers");
        miSuppliers.addActionListener(e -> new SupplierForm().setVisible(true));
        // jMenuBar1 is the JMenuBar NetBeans created under "Other Components"
        jMenuBar1.add(miSuppliers);

        
        
        
        try {
        prMgr.loadFromFile("prs.dat");
        poMgr.loadFromFile("pos.dat");
        sMgr.loadFromFile("suppliers.dat");
        invMgr.loadFromFile("inventory.dat");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this,
            "Failed to load data: " + e.getMessage(),
            "Load Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
        
        JButton btnReceiveShipment = new JButton("Receive Shipment");
        btnReceiveShipment.addActionListener(e -> receiveShipment());
        jToolBar1.add(btnReceiveShipment);    // or wherever your other buttons live
        
        

        
        
        // 1. Create and attach our table model
        tableModel = new DefaultTableModel(
        new String[]{"ID","Item","Qty","Status","Date"}, 
        0
        );
        tblRecords.setModel(tableModel);
    
        // 2. Wire up button clicks
        btnLoadPRs   .addActionListener(e -> loadPendingRequisitions());
        btnApprovePO .addActionListener(e -> approveSelected());
        btnLoadPOs   .addActionListener(e -> loadPendingOrders());
        btnProcessPay.addActionListener(e -> processPaymentForSelected());
        btnReport    .addActionListener(e -> showReportDialog());

        btnReport    .addActionListener(e -> showReportDialog());
        // 3. Perform an initial load
        loadPendingRequisitions();

        loadPendingPRs();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        try {
            prMgr.saveToFile("prs.dat");
            poMgr.saveToFile("pos.dat");
            sMgr .saveToFile("suppliers.dat");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});
    }
    
    private void loadPendingRequisitions() {
    tableModelRequisitions.setRowCount(0);
    for (PurchaseRequisition pr : prMgr.getAll()) {
        tableModelRequisitions.addRow(new Object[]{
            pr.getId(),
            pr.getItemCode(),
            pr.getQuantity(),
            pr.getStatus(),                          // ← now exists
            DF.format(pr.getDateRequested())
        });
    }
}

    private void approveSelected() {
   // 0) Make sure a row is selected
    int row = tblRecords.getSelectedRow();
    if (row < 0) return;

    // 1) Look up the PR
    int prId = (int) tableModel.getValueAt(row, 0);
    PurchaseRequisition pr = prMgr.findById(prId);
    if (pr == null) {
        JOptionPane.showMessageDialog(this,
            "Couldn't find that requisition!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 2) Compute a new PO ID
    int newPoId = poMgr.getAll().size() + 1;

    // 3) Ask user for dollar amount
    String amtStr = JOptionPane.showInputDialog(
        this,
        "Enter dollar amount for PO #" + newPoId + ":",
        "Approve Requisition",
        JOptionPane.PLAIN_MESSAGE
    );
    if (amtStr == null) return;

    double amount;
    try {
        amount = Double.parseDouble(amtStr.trim());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Invalid amount",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 4) Use the PR's supplier
    int supplierId = pr.getSupplierId();

    // 5) Build & save the new 4-arg PurchaseOrder
    PurchaseOrder po = new PurchaseOrder(
        newPoId,
        pr,
        amount,
        supplierId
    );
    poMgr.add(po);

    // 6) Send notification email
    try {
  // look up the supplier once
  Supplier sup = sMgr.findById(pr.getSupplierId());
  if (sup == null) {
    JOptionPane.showMessageDialog(
      this,
      "No supplier found with ID " + pr.getSupplierId(),
      "Email Error",
      JOptionPane.ERROR_MESSAGE
    );
  } else {
    String to  = sup.getEmail();
    String sub = "New Purchase Order #" + po.getId();
    StringBuilder body = new StringBuilder()
      .append("Dear ").append(sup.getName()).append(",\n\n")
      .append("Your PO details:\n")
      .append("PO #: ").append(po.getId()).append("\n")
      .append("Item: ").append(pr.getItemCode()).append("\n")
      .append("Qty : ").append(pr.getQuantity()).append("\n\n")
      .append("Thanks,\nFinance Manager Team");

    MailUtil.send(to, sub, body.toString());
    JOptionPane.showMessageDialog(
      this,
      "Created PO #" + po.getId() + " and emailed to " + to
    );
  }
} catch (Exception ex) {
  JOptionPane.showMessageDialog(
    this,
    "PO created, but failed to email: " + ex.getMessage(),
    "Email Error",
    JOptionPane.ERROR_MESSAGE
  );
}


    // 7) Refresh both tables
    loadPendingRequisitions();
    loadPendingOrders();
   }
    
    private void addRequisition() {
     // 1) Gather the usual fields
    String itemCode = JOptionPane.showInputDialog(this, "Item code:");
    if (itemCode == null) return;

    int quantity;
    try {
        quantity = Integer.parseInt(
            JOptionPane.showInputDialog(this, "Quantity:")
        );
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid number"); 
        return;
    }

    Date dateRequested = new Date();

    // 2) Prompt the user for which supplier this comes from
    String supInput = JOptionPane.showInputDialog(
        this, "Supplier ID (e.g. 1,2,3):"
    );
    if (supInput == null) return;

    int supplierId;
    try {
        supplierId = Integer.parseInt(supInput);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid supplier ID");
        return;
    }

    // 3) Create the PR with all five args
    int newId = prMgr.getAll().size() + 1;
  
}
    

    private void loadPendingOrders() {
   
}

    private void processPaymentForSelected() {
        
     // 0) Make sure a PO is selected
    int row = tblOrders.getSelectedRow();
    if (row < 0) return;

    // 1) Look up the PO
    int poId = (int) tableModelOrders.getValueAt(row, 0);
    PurchaseOrder po = poMgr.findById(poId);
    if (po == null) {
        JOptionPane.showMessageDialog(
            this,
            "Couldn't find that purchase order!",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 2) Verify stock has been received
    InventoryEntry inv = invMgr.findByItem(po.getRequisition().getItemCode());
    if (inv == null || inv.getQuantity() < po.getRequisition().getQuantity()) {
        JOptionPane.showMessageDialog(
            this,
            "Cannot pay: you still need to receive "
            + po.getRequisition().getQuantity()
            + " of \""
            + po.getRequisition().getItemCode()
            + "\"",
            "Stock Error",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // 3) Deduct from inventory
    inv.setQuantity(inv.getQuantity() - po.getRequisition().getQuantity());
    try {
        invMgr.saveToFile("inventory.dat");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Failed to update stock: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 4) Ask user to confirm / enter the payment amount
    String amtStr = JOptionPane.showInputDialog(
        this,
        "Enter payment amount for PO #" + po.getId() + ":",
        "Process Payment",
        JOptionPane.PLAIN_MESSAGE
    );
    if (amtStr == null) return;

    double amt;
    try {
        amt = Double.parseDouble(amtStr.trim());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Invalid amount",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 5) Mark the PO as paid
    po.setStatus("PAID");
    po.setAmount(amt);
    try {
        poMgr.saveToFile("pos.dat");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Failed to save payment: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 6) Send confirmation email
    try {
        String to  = sMgr.findById(po.getSupplierId()).getEmail();
        String sub = "Payment received for PO #" + po.getId();
        StringBuilder body = new StringBuilder();
        body.append("Dear ")
            .append(sMgr.findById(po.getSupplierId()).getName())
            .append(",\n\n")
            .append("We have processed your payment for Purchase Order #")
            .append(po.getId())
            .append(".\n\nThank you,\nFinance Manager Team");
        MailUtil.send(to, sub, body.toString());
        JOptionPane.showMessageDialog(
            this,
            "PO #" + po.getId() + " marked PAID and emailed to " + to
        );
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            this,
            "PO paid, but failed to email: " + ex.getMessage(),
            "Email Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // 7) Refresh the orders table
    loadPendingRequisitions();
    loadPendingOrders();
 

}

    private void showReportDialog() {
    // 1) Create two date spinners
    SpinnerDateModel fromModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    JSpinner spinnerFrom = new JSpinner(fromModel);
    spinnerFrom.setEditor(new JSpinner.DateEditor(spinnerFrom, "yyyy-MM-dd"));

    SpinnerDateModel toModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    JSpinner spinnerTo = new JSpinner(toModel);
    spinnerTo.setEditor(new JSpinner.DateEditor(spinnerTo, "yyyy-MM-dd"));

    // 2) Lay them out in a panel
    JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
    panel.add(new JLabel("From:"));
    panel.add(spinnerFrom);
    panel.add(new JLabel("To:"));
    panel.add(spinnerTo);

    // 3) Show OK/CANCEL dialog
    int result = JOptionPane.showConfirmDialog(
      this,
      panel,
      "Select Report Date Range",
      JOptionPane.OK_CANCEL_OPTION,
      JOptionPane.PLAIN_MESSAGE
    );
    if (result != JOptionPane.OK_OPTION) return;

    // 4) Pull the dates
    Date from = (Date) spinnerFrom.getValue();
    Date to   = (Date) spinnerTo.getValue();

    // 5) Filter POs in range
    List<PurchaseOrder> filtered = poMgr.getAll().stream()
      .filter(po -> !po.getDateIssued().before(from) && !po.getDateIssued().after(to))
      .collect(Collectors.toList());

    // 6) Compute summary
    long total   = filtered.size();
    long paid    = filtered.stream().filter(po -> "PAID".equals(po.getStatus())).count();
    long pending = total - paid;

    // 7) Build a simple report string
    StringBuilder rpt = new StringBuilder();
    rpt.append("Orders from ")
       .append(DF.format(from))
       .append(" to ")
       .append(DF.format(to))
       .append(":\n\n")
       .append(" Total orders: ").append(total).append("\n")
       .append(" Paid:         ").append(paid).append("\n")
       .append(" Pending:      ").append(pending).append("\n\n")
       .append("Details:\n");
    for (PurchaseOrder po : filtered) {
      rpt.append("  PO#").append(po.getId())
         .append(" [").append(po.getStatus()).append("] ")
         .append(po.getRequisition().getItemCode())
         .append(" x").append(po.getRequisition().getQuantity())
         .append("\n");
    }

    // 8) Show the report
    JTextArea text = new JTextArea(rpt.toString());
    text.setEditable(false);
    text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    JOptionPane.showMessageDialog(this, new JScrollPane(text), "PO Report", JOptionPane.INFORMATION_MESSAGE);
}

    
    private void loadPendingPRs() {
        // e.g. jTable1.setModel( new MyTableModel(prMgr.getAll()) );
    }
    
    private void receiveShipment() {
     // 1) Which order?
    int row = tblOrders.getSelectedRow();  
    if (row < 0) return;  // nothing selected
    int poId = (int) tableModelOrders.getValueAt(row, 0);
    PurchaseOrder po = poMgr.findById(poId);

    // 2) Ask for quantity received
    String qtyStr = JOptionPane.showInputDialog(
      this,
      "Enter quantity received for PO #"+poId+":",
      "Receive Shipment",
      JOptionPane.PLAIN_MESSAGE
    );
    if (qtyStr == null) return;

    int recQty;
    try {
      recQty = Integer.parseInt(qtyStr.trim());
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this,
        "Invalid number",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    // 3) Create & store the inventory entry
    int newInvId = invMgr.getAll().size() + 1;
    InventoryEntry entry = new InventoryEntry(
      newInvId,
      po.getItemCode(),
      recQty,
      new Date(),
      po.getSupplierId()
    );
    invMgr.add(entry);

    // 4) Persist both inventory and orders
    try {
      invMgr.saveToFile("inventory.dat");
      poMgr.saveToFile("pos.dat");
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(this,
        "Failed to save data: "+ioe.getMessage(),
        "Save Error",
        JOptionPane.ERROR_MESSAGE
      );
    }

    // 5) Refresh your tables
    loadPendingOrders();
    loadPendingRequisitions();
}
    
//private void generateReport() {
//   1) total revenue = sum of every SalesEntry.getAmount()
//  double revenue = salesMgr.getAll().stream()
//    .mapToDouble(se -> se.getAmount())
//    .sum();
//
//   2) direct cost = sum of every PurchaseOrder.getAmount()
//  double cost = poMgr.getAll().stream()
//    .mapToDouble(po -> po.getAmount())
//    .sum();
//
//   3) profit
//  double profit = revenue - cost;
//
//   4) build a little JTable
//  DefaultTableModel model = new DefaultTableModel(
//    new String[]{"Category", "Amount"}, 0
//  );
//  model.addRow(new Object[]{"Revenue", String.format("$%.2f", revenue)});
//  model.addRow(new Object[]{"Cost",    String.format("$%.2f", cost)});
//  model.addRow(new Object[]{"Profit",  String.format("$%.2f", profit)});
//
//  JTable tbl = new JTable(model);
//  JOptionPane.showMessageDialog(
//    this,
//    new JScrollPane(tbl),
//    "Financial Report",
//    JOptionPane.INFORMATION_MESSAGE
//  );
//}




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
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnLoadPRs = new javax.swing.JButton();
        btnLoadPOs = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnApprovePO = new javax.swing.JButton();
        btnAddPR = new javax.swing.JButton();
        btnProcessPay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecords = new javax.swing.JTable();
        btnReceiveShipment = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnLoadPRs.setText("Load Requisitions");
        btnLoadPRs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadPRsActionPerformed(evt);
            }
        });

        btnLoadPOs.setText("Load Orders");
        btnLoadPOs.setActionCommand("Load Orders");
        btnLoadPOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLoadPOs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLoadPOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadPOsActionPerformed(evt);
            }
        });

        btnReport.setText("Generate Report");
        btnReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        btnApprovePO.setText("Approve PO");
        btnApprovePO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnApprovePO.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnApprovePO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovePOActionPerformed(evt);
            }
        });

        btnAddPR.setText("Add Requisition");
        btnAddPR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddPR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPRActionPerformed(evt);
            }
        });

        btnProcessPay.setText("Process Payment");
        btnProcessPay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProcessPay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProcessPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessPayActionPerformed(evt);
            }
        });

        tblRecords.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblRecords);

        btnReceiveShipment.setText("Receive Shipment");
        btnReceiveShipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiveShipmentActionPerformed(evt);
            }
        });

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblOrders);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApprovePO)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnReport)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnProcessPay)
                                        .addComponent(btnAddPR))))
                            .addComponent(btnLoadPRs, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btnLoadPOs))
                            .addComponent(btnReceiveShipment))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnLoadPRs)
                        .addGap(24, 24, 24)
                        .addComponent(btnLoadPOs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReport)
                        .addGap(27, 27, 27)
                        .addComponent(btnApprovePO)
                        .addGap(34, 34, 34)
                        .addComponent(btnAddPR)
                        .addGap(52, 52, 52)
                        .addComponent(btnProcessPay)
                        .addGap(58, 58, 58)
                        .addComponent(btnReceiveShipment)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel3);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessPayActionPerformed
        processPaymentForSelected();
    }//GEN-LAST:event_btnProcessPayActionPerformed

    private void btnAddPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPRActionPerformed
        // 1) Ask user for item code
        String code = JOptionPane.showInputDialog(this, "Enter item code:");
        if (code == null || code.trim().isEmpty()) return;

        // 2) Ask user for quantity
        String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity:");
        int qty;
        try {
            qty = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number for quantity.");
            return;
        }

        // 3) Compute a new ID (simple auto‐increment based on list size)
        int newId = prMgr.getAll().size() + 1;
        
            String supInput = JOptionPane.showInputDialog(
      this, "Supplier ID (e.g. 1,2,3):"
    );
    if (supInput == null) return;
    int supplierId;
    try {
      supplierId = Integer.parseInt(supInput.trim());
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Invalid supplier ID");
      return;
    }

    // now call the 5-arg constructor:
    PurchaseRequisition pr = new PurchaseRequisition(
      newId,
      code,
      qty,
      new Date(),
      supplierId     // ← fifth argument
    );
    prMgr.add(pr);
    loadPendingRequisitions();

        
        
    }//GEN-LAST:event_btnAddPRActionPerformed

    private void btnApprovePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePOActionPerformed
          approveSelected();
    }//GEN-LAST:event_btnApprovePOActionPerformed

    private void btnLoadPRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPRsActionPerformed
        loadPendingRequisitions();
    }//GEN-LAST:event_btnLoadPRsActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
//        generateReport();
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnLoadPOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPOsActionPerformed
        loadPendingOrders();
    }//GEN-LAST:event_btnLoadPOsActionPerformed

    private void btnReceiveShipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiveShipmentActionPerformed
        receiveShipment();
    }//GEN-LAST:event_btnReceiveShipmentActionPerformed

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
            java.util.logging.Logger.getLogger(FMForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FMForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPR;
    private javax.swing.JButton btnApprovePO;
    private javax.swing.JButton btnLoadPOs;
    private javax.swing.JButton btnLoadPRs;
    private javax.swing.JButton btnProcessPay;
    private javax.swing.JButton btnReceiveShipment;
    private javax.swing.JButton btnReport;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTable tblRecords;
    // End of variables declaration//GEN-END:variables
}
