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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.*;
import javax.swing.*; 
import java.util.*;  
import java.text.ParseException;


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
import java.io.File;




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
private static final String DATA_DIR = "src/test/";



    
    
    private DefaultTableModel tableModelRequisitions;
    private DefaultTableModel tableModelOrders;


    
    






    /**
     * Creates new form FMForm
     */
    public FMForm() {
        initComponents();
        
         // ─── CUSTOM MENU BAR ────────────────────────────────────────
// right after initComponents():

// ─── CUSTOM “Manage Suppliers” MENU ─────────────────────────
JMenuBar menuBar    = new JMenuBar();
JMenu   manageMenu  = new JMenu("Manage");
JMenuItem supItem   = new JMenuItem("Manage Suppliers…");

supItem.addActionListener(e -> {
    // SupplierForm is a JFrame with only a no-arg constructor
    SupplierForm dlg = new SupplierForm();
    dlg.setLocationRelativeTo(this);
    dlg.setVisible(true);
});

manageMenu.add(supItem);
menuBar.add(manageMenu);
setJMenuBar(menuBar);
// ────────────────────────────────────────────────────────────
        
        
        
        tableModelRequisitions = (DefaultTableModel) tblRequisitions.getModel();
tableModelOrders       = (DefaultTableModel) tblOrders .getModel();
tblRequisitions = new javax.swing.JTable();
jScrollPane1.setViewportView(tblRequisitions);

    btnLoadPRs       .addActionListener(e -> loadPendingRequisitions());
    btnApprovePO     .addActionListener(e -> approveSelected());
    btnLoadPOs       .addActionListener(e -> loadPendingOrders());
    btnProcessPay    .addActionListener(e -> processPaymentForSelected());
    btnReport        .addActionListener(e -> showReportDialog());
    btnApprovePO.addActionListener(e -> approveSelected());
    btnProcessPay.addActionListener(e -> processPaymentForSelected());
    btnReceiveShipment.addActionListener(e -> receiveShipment());


    // ← add your “Add Requisition” hook right here:
    btnAddPR         .addActionListener(e -> addRequisition());

    btnReceiveShipment.addActionListener(e -> receiveShipment());
        
         // 1) Attach models
tableModelRequisitions = new DefaultTableModel(
  new String[] {
    "PRID",       // requisition id
    "ItemIDs",    // item code(s)
    "Quantities", // quantity(ies)
    "Date",       // date requested
    "SupplierID", // supplier reference
    "SMID",       // sales-manager (or staff) id
    "Status"
  },
  0
);
tblRequisitions.setModel(tableModelRequisitions);

  tableModelOrders = new DefaultTableModel(
      new String[]{"ID","Item","Qty","Amount","Supplier","Status","Date"}, 0
  );
  tblOrders.setModel(tableModelOrders);

  // 2) Load from files
  try {
    prMgr.loadFromFile(DATA_DIR + "PurchaseRequisitions.txt");


 poMgr.loadFromFile(DATA_DIR + "PurchaseOrders.txt");
    sMgr  .loadFromFile(DATA_DIR + "Suppliers.txt");
    invMgr.loadFromFile(DATA_DIR + "Inventory.txt");
    salesMgr.loadFromFile(DATA_DIR + "SalesEntry");
  } catch(IOException e) {
    JOptionPane.showMessageDialog(this,
      "Failed to load data: " + e.getMessage(),
      "Load Error",
      JOptionPane.ERROR_MESSAGE
    );
  }

  // 3) Initial display
  loadPendingRequisitions();
  loadPendingOrders();

  // 4) Save on close
  addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
      try {
        prMgr .saveToFile(DATA_DIR + "PurchaseRequisitions.txt");
        poMgr .saveToFile(DATA_DIR + "PurchaseOrders.txt");
        sMgr  .saveToFile(DATA_DIR + "Suppliers.txt");
        invMgr.saveToFile(DATA_DIR + "Inventory.txt");
        salesMgr.saveToFile(DATA_DIR + "SalesEntry");
      } catch(IOException ex) {
        ex.printStackTrace();
      }
    }
  });
        

        

        
        
 
    }
    
    private void loadPendingRequisitions() {
    tableModelRequisitions.setRowCount(0);
    for (PurchaseRequisition pr : prMgr.getAll()) {
        tableModelRequisitions.addRow(new Object[]{
            pr.getPrId(),
            pr.getItemIds(),
            pr.getQuantity(),
            DF.format(pr.getDateRequested()),
            pr.getSupplierId(),
            pr.getSalesMgrId(),
            pr.getStatus()
        });
    }
}

    private void approveSelected() {
        
          // 0) Make sure a requisition is selected
    int row = tblRequisitions.getSelectedRow();
    if (row < 0) {
        // nothing selected
        return;
    }

    // 1) Look up the PR (first column is the PR ID)
    String prId = (String)tableModelRequisitions.getValueAt(row, 0);
    PurchaseRequisition pr = prMgr.findById(prId);
    if (pr == null) {
        JOptionPane.showMessageDialog(this,
            "Couldn't find that requisition!",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 2) Compute a new PO ID
    int newPoId = poMgr.getAll().size() + 1;

    // 3) Ask user for dollar amount (total)
    String amtStr = JOptionPane.showInputDialog(
        this,
        "Enter dollar amount for PO #" + newPoId + ":",
        "Approve Requisition",
        JOptionPane.PLAIN_MESSAGE
    );
    if (amtStr == null) {
        // user cancelled
        return;
    }
    double amount;
    try {
        amount = Double.parseDouble(amtStr.trim());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
            "That's not a valid number.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 4) Ask user for unit cost (optional—you can skip if you only need total)
    String priceStr = JOptionPane.showInputDialog(
        this,
            "Enter price per unit for item “" + pr.getItemIds() + "”:",
        "Unit Price",
        JOptionPane.PLAIN_MESSAGE
    );
    double unitPrice = -1;
    if (priceStr != null) {
        try {
            unitPrice = Double.parseDouble(priceStr.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid unit price—using total only.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            unitPrice = -1;
        }
    }

    // 5) Build & save the new PurchaseOrder
    PurchaseOrder po = new PurchaseOrder(
        newPoId,
        pr,
        amount,
        pr.getSupplierId()
    );
    poMgr.add(po);
    try {
        poMgr.saveToFile(DATA_DIR + "PurchaseOrders.txt");
    } catch (IOException ioe) {
        JOptionPane.showMessageDialog(this,
            "Failed to save orders: " + ioe.getMessage(),
            "Save Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // 6) (Optional) Email notification to supplier
    try {
        Supplier sup = sMgr.findById(pr.getSupplierId());
        if (sup == null) {
            JOptionPane.showMessageDialog(this,
                "No supplier found with ID " + pr.getSupplierId(),
                "Email Error",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            String to    = sup.getEmail();
            String subj  = "New Purchase Order #" + po.getId();
            StringBuilder body = new StringBuilder()
                .append("Dear ").append(sup.getName()).append(",\n\n")
                .append("Your PO details:\n")
                .append("PO #: ").append(po.getId()).append("\n")
                    .append("Item: ").append(pr.getItemIds()).append("\n")
                .append("Qty: ").append(pr.getQuantity()).append("\n")
                .append("Total: ").append(amount).append("\n\n")
                .append("Thanks,\nFinance Manager Team");
            MailUtil.send(to, subj, body.toString());
            JOptionPane.showMessageDialog(this,
                "Created PO #" + po.getId() + " and emailed to " + to
            );
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
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
      // 1) PR-ID
  String prId = JOptionPane.showInputDialog(this, "PRID:");
  if (prId == null || prId.trim().isEmpty()) return;

  // 2) Item-IDs
  String itemIds = JOptionPane.showInputDialog(
    this,
    "Item IDs (slash-separated, e.g. ITM001/ITM002):"
  );
  if (itemIds == null || itemIds.trim().isEmpty()) return;

  // 3) Quantity
  String qtyStr = JOptionPane.showInputDialog(this, "Quantity:");
  if (qtyStr == null) return;
  int quantity;
  try {
    quantity = Integer.parseInt(qtyStr.trim());
  } catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(this, "Invalid quantity");
    return;
  }

  // 4) Date
  String dateStr = JOptionPane.showInputDialog(this, "Date (yyyy-MM-dd):");
  if (dateStr == null || dateStr.trim().isEmpty()) return;
  Date dateRequested;
  try {
    dateRequested = new SimpleDateFormat("yyyy-MM-dd")
                        .parse(dateStr.trim());
  } catch (ParseException ex) {
    JOptionPane.showMessageDialog(this, "Invalid date format");
    return;
  }

  // 5) Supplier-ID
  String supplierId = JOptionPane.showInputDialog(
    this,
    "Supplier ID (e.g. SUP001):"
  );
  if (supplierId == null || supplierId.trim().isEmpty()) return;

  // 6) Sales-Manager ID
  String salesMgrId = JOptionPane.showInputDialog(
    this,
    "Sales-Manager ID (e.g. SM001):"
  );
  if (salesMgrId == null || salesMgrId.trim().isEmpty()) return;

  // 7) Status
  String status = JOptionPane.showInputDialog(this, "Status:");
  if (status == null || status.trim().isEmpty()) return;

  // ─── Build & save ───────────────────────────────────────
  PurchaseRequisition pr = new PurchaseRequisition(
    prId,
    itemIds,
    quantity,
    dateRequested,
    supplierId,
    salesMgrId,
    status
  );
  prMgr.add(pr);
try {
  prMgr.appendToFile(DATA_DIR + "PurchaseRequisitions.txt", pr);
} catch (IOException ex) {
  ex.printStackTrace();
  JOptionPane.showMessageDialog(
    this,
    "Failed to save requisition:\n" + ex.getMessage(),
    "I/O Error",
    JOptionPane.ERROR_MESSAGE
  );
}

  

  loadPendingRequisitions();
  
 


   

  }

    

    private void loadPendingOrders() {
    tableModelOrders.setRowCount(0);
    for (PurchaseOrder po : poMgr.getAll()) {
        tableModelOrders.addRow(new Object[]{
            po.getId(),
            po.getItemIds(),
            po.getQuantity(),
            po.getAmount(),
            po.getSupplierId(),
            po.getStatus(),
            DF.format(po.getDateIssued())
        });
    }
}

    private void processPaymentForSelected() {
    int row = tblOrders.getSelectedRow();
    if (row < 0) return;

    int poId = (int) tableModelOrders.getValueAt(row, 0);
    PurchaseOrder po = poMgr.findById(poId);
    if (po == null) {
        JOptionPane.showMessageDialog(this, "Couldn't find that purchase order!",
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // verify stock received
    String[] parts = po.getItemIds().split("/");
    String itemId  = parts[0];                     // or handle multiple
    InventoryEntry inv = invMgr.findByItem(itemId);
    int needed = po.getQuantity();
    if (inv == null || inv.getQuantity() < needed) {
        JOptionPane.showMessageDialog(this,
            "Cannot pay: you still need to receive " + needed +
            " of '" + itemId + "'", "Stock Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // deduct from inventory
    inv.setQuantity(inv.getQuantity() - needed);

    try {
    invMgr.saveToFile(DATA_DIR + "Inventory.txt");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this,
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
        JOptionPane.showMessageDialog(this,
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
        poMgr.saveToFile(DATA_DIR + "PurchaseOrders.txt");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this,
            "Failed to save payment: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 6) Send confirmation email
    try {
        Supplier sup = sMgr.findById(po.getSupplierId());
        if (sup == null) {
            JOptionPane.showMessageDialog(this,
                "No supplier found with ID " + po.getSupplierId(),
                "Email Error",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            String to   = sup.getEmail();
            String subj = "Payment received for PO #" + po.getId();
            StringBuilder body = new StringBuilder()
                .append("Dear ").append(sup.getName()).append(",\n\n")
                .append("We have processed your payment for Purchase Order #")
                .append(po.getId()).append(".\n\n")
                .append("Thank you,\nFinance Manager Team");
            MailUtil.send(to, subj, body.toString());
            JOptionPane.showMessageDialog(this,
                "PO #" + po.getId()
                + " marked PAID and emailed to " + to
            );
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
            "PO paid, but failed to email: " + ex.getMessage(),
            "Email Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // 7) Refresh both tables
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
       .append(po.getItemIds())
       .append(" x").append(po.getQuantity())
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

    // 2) Look up the PO by its ID (in column 0)
    int poId = (int) tableModelOrders.getValueAt(row, 0);
    PurchaseOrder po = poMgr.findById(poId);
    if (po == null) {
        JOptionPane.showMessageDialog(this,
            "Couldn't find that order!",
            "Error", JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 3) Mark the order as received (so it will no longer show as “pending”)
    po.setStatus("RECEIVED");

    // 4) Ask for quantity received
    String qtyStr = JOptionPane.showInputDialog(
        this,
        "Enter quantity received for PO #" + poId + ":",
        "Receive Shipment",
        JOptionPane.PLAIN_MESSAGE
    );
    if (qtyStr == null) return;  // user cancelled

    int recQty;
    try {
        recQty = Integer.parseInt(qtyStr.trim());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
            "Invalid number",
            "Error", JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 5) Create & store a new InventoryEntry
    int newInvId = invMgr.getAll().size() + 1;
    InventoryEntry entry = new InventoryEntry(
        newInvId,
        po.getItemCode(),    // proxies through the PR
        recQty,
        new Date(),
        po.getSupplierId()
    );
    invMgr.add(entry);

    // 6) Persist both inventory and orders
    try {
        invMgr.saveToFile(DATA_DIR + "Inventory.txt");
        poMgr.saveToFile(DATA_DIR + "PurchaseOrders.txt");
    } catch (IOException ioe) {
        JOptionPane.showMessageDialog(this,
            "Failed to save data: " + ioe.getMessage(),
            "Save Error", JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // 7) Refresh your tables (so the PO drops out of “pending” and your inventory view updates)
    loadPendingRequisitions();
    loadPendingOrders();
     

   
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
        tblRequisitions = new javax.swing.JTable();
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

        tblRequisitions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "PRID", "ItemIDs", "Quantities", "Date", "SupplierID", "SMID", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblRequisitions);

        btnReceiveShipment.setText("Receive Shipment");
        btnReceiveShipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiveShipmentActionPerformed(evt);
            }
        });

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Item", "Qty", "Amount ", "Supplier", "Status", "Date"
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
                .addContainerGap(508, Short.MAX_VALUE))
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
            .addGap(0, 1315, Short.MAX_VALUE)
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
            .addGap(0, 1315, Short.MAX_VALUE)
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
       
addRequisition();

        
        
    }//GEN-LAST:event_btnAddPRActionPerformed

    private void btnApprovePOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePOActionPerformed
          approveSelected();
    }//GEN-LAST:event_btnApprovePOActionPerformed

    private void btnLoadPRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPRsActionPerformed
        loadPendingRequisitions();
    }//GEN-LAST:event_btnLoadPRsActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed

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
    private javax.swing.JTable tblRequisitions;
    // End of variables declaration//GEN-END:variables
}
