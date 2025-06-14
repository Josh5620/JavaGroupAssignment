package InventoryManager;
import shared_model.StockReport;
import javax.swing.*;
import shared_model.PurchaseOrder;
import UserLogin.LoginPage;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import InventoryManager.InvenUser;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;

public class IMForm extends javax.swing.JFrame {

    public IMForm(String username, String role) {
        
        initComponents();
        customizeTable(InvenTable);
        customizeTable(SmallTable);
        homePageLoad(username,role);
    }

    JFrame adminFrame; 
    public IMForm(String username, String role, JFrame adminFrame) {
    this(username, role); 
    this.adminFrame = adminFrame;

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    

    this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            if (adminFrame != null) {
                adminFrame.setVisible(true);
            }
        }
    });
}
    
    InvenUser user = new InvenUser();
    List<List<String>> PurchaseOrderTextList = new ArrayList<>();
    List<List<String>> ApprovedOrderList = new ArrayList<>();
    String POFilePath = "src/PurchaseOrders.txt";
    String[] itemIDs;
    String[] incomingQty;
    List<String> focusAPO = new ArrayList<>();
    List<PurchaseOrder> PurchaseOrderList = new ArrayList<>();
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        HomePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        LSA1 = new javax.swing.JLabel();
        LSA2 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        LSA3 = new javax.swing.JLabel();
        LSA4 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        LSA5 = new javax.swing.JLabel();
        jProgressBar5 = new javax.swing.JProgressBar();
        LSA6 = new javax.swing.JLabel();
        jProgressBar6 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TAI = new javax.swing.JLabel();
        UAP = new javax.swing.JLabel();
        InvenNAlerts = new javax.swing.JPanel();
        Label2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InvenTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        AllUserBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Label4 = new javax.swing.JLabel();
        Label5 = new javax.swing.JLabel();
        specificUserField = new javax.swing.JTextField();
        specificUserBtn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        UpdateStock = new javax.swing.JPanel();
        Label7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ApprovedList = new javax.swing.JList<>();
        APOBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ClearBtn = new javax.swing.JButton();
        SubmitBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        AfterBox = new javax.swing.JTextField();
        BfrBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        IDNameBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        SmallTable = new javax.swing.JTable();
        UpdateBtn = new javax.swing.JButton();
        ViewPOs = new javax.swing.JPanel();
        Label6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        POList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        POTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        POForm = new javax.swing.JButton();
        SidePanel = new javax.swing.JPanel();
        ExitButton = new javax.swing.JButton();
        HomeButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Button5 = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 490));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainPanel.setBackground(new java.awt.Color(153, 153, 153));
        MainPanel.setLayout(new java.awt.CardLayout());

        HomePanel.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Home Panel");

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setForeground(new java.awt.Color(255, 204, 102));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("role");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("username");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Low Stock Alerts:");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        LSA1.setForeground(new java.awt.Color(102, 102, 102));
        LSA1.setText("Test");

        LSA2.setForeground(new java.awt.Color(102, 102, 102));
        LSA2.setText("Test");

        LSA3.setForeground(new java.awt.Color(102, 102, 102));
        LSA3.setText("Test");

        LSA4.setForeground(new java.awt.Color(102, 102, 102));
        LSA4.setText("Test");

        LSA5.setForeground(new java.awt.Color(102, 102, 102));
        LSA5.setText("Test");

        LSA6.setForeground(new java.awt.Color(102, 102, 102));
        LSA6.setText("Test");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Unresolved Approved POs:");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Total Inventory Items:");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        TAI.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TAI.setForeground(new java.awt.Color(51, 51, 51));
        TAI.setText("ITems");

        UAP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UAP.setForeground(new java.awt.Color(51, 51, 51));
        UAP.setText("ITems");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LSA6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSA5)
                            .addComponent(LSA4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TAI))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UAP))
                            .addComponent(LSA1)
                            .addComponent(LSA2)
                            .addComponent(LSA3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TAI))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(UAP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LSA4))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSA5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LSA6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSA1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSA2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LSA3))))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGroup(HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomePanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HomePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        MainPanel.add(HomePanel, "card7");

        InvenNAlerts.setBackground(new java.awt.Color(153, 153, 153));
        InvenNAlerts.setFocusable(false);

        Label2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Label2.setText("Low Stock Items");

        InvenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ItemID", "ItemName", "Quantity"
            }
        ));
        jScrollPane2.setViewportView(InvenTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Items highlighted Red : (<20 Stock)");

        Label3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Label3.setText("Inventory List");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea1);

        AllUserBtn.setText("Submit");
        AllUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllUserBtnActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 102));
        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Label4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Label4.setText("Send Alert to all SM:");

        Label5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Label5.setText("Send Alert to specific SM:");

        specificUserBtn.setText("Submit");
        specificUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specificUserBtnActionPerformed(evt);
            }
        });

        jButton4.setText("Stock Report");
        jButton4.setActionCommand("GenerateReportBtn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InvenNAlertsLayout = new javax.swing.GroupLayout(InvenNAlerts);
        InvenNAlerts.setLayout(InvenNAlertsLayout);
        InvenNAlertsLayout.setHorizontalGroup(
            InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InvenNAlertsLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(InvenNAlertsLayout.createSequentialGroup()
                        .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AllUserBtn)
                    .addComponent(specificUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(specificUserBtn)
                    .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(InvenNAlertsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addGap(47, 47, 47))
            .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(InvenNAlertsLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(Label3)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );
        InvenNAlertsLayout.setVerticalGroup(
            InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvenNAlertsLayout.createSequentialGroup()
                .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InvenNAlertsLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(InvenNAlertsLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label2)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AllUserBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Label5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(specificUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(specificUserBtn)))
                .addGap(30, 30, 30))
            .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(InvenNAlertsLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(Label3)
                    .addContainerGap(458, Short.MAX_VALUE)))
        );

        MainPanel.add(InvenNAlerts, "card3");

        UpdateStock.setBackground(new java.awt.Color(153, 153, 153));

        Label7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Label7.setText("Update Stock");

        ApprovedList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Load APOs to View", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(ApprovedList);

        APOBtn.setBackground(new java.awt.Color(153, 153, 255));
        APOBtn.setText("Load APOs");
        APOBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APOBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        ClearBtn.setBackground(new java.awt.Color(255, 51, 51));
        ClearBtn.setText("Clear");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        SubmitBtn.setBackground(new java.awt.Color(102, 255, 102));
        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Quantity before:");

        AfterBox.setEditable(false);
        AfterBox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        BfrBox.setEditable(false);
        BfrBox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Quantity after:");

        IDNameBox.setEditable(false);
        IDNameBox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Details:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(IDNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BfrBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(AfterBox, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(SubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClearBtn)
                        .addGap(6, 6, 6)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IDNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BfrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AfterBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClearBtn)
                    .addComponent(SubmitBtn))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        SmallTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Quantity"
            }
        ));
        jScrollPane7.setViewportView(SmallTable);

        UpdateBtn.setBackground(new java.awt.Color(153, 153, 255));
        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdateStockLayout = new javax.swing.GroupLayout(UpdateStock);
        UpdateStock.setLayout(UpdateStockLayout);
        UpdateStockLayout.setHorizontalGroup(
            UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateStockLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UpdateStockLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(APOBtn)
                            .addGroup(UpdateStockLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(UpdateBtn)))
                        .addContainerGap(27, Short.MAX_VALUE))))
            .addGroup(UpdateStockLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Label7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        UpdateStockLayout.setVerticalGroup(
            UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateStockLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Label7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UpdateStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(UpdateStockLayout.createSequentialGroup()
                        .addComponent(APOBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UpdateBtn)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        MainPanel.add(UpdateStock, "card6");

        ViewPOs.setBackground(new java.awt.Color(153, 153, 153));
        ViewPOs.setForeground(new java.awt.Color(153, 153, 153));

        Label6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Label6.setText("Purchase Orders");

        POList.setBackground(new java.awt.Color(204, 204, 204));
        POList.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        POList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Refresh List", "To Load" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(POList);

        POTextArea.setEditable(false);
        POTextArea.setBackground(new java.awt.Color(204, 204, 204));
        POTextArea.setColumns(20);
        POTextArea.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        POTextArea.setRows(5);
        jScrollPane4.setViewportView(POTextArea);

        jButton1.setText("Load Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        POForm.setText("Detail Form");
        POForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewPOsLayout = new javax.swing.GroupLayout(ViewPOs);
        ViewPOs.setLayout(ViewPOsLayout);
        ViewPOsLayout.setHorizontalGroup(
            ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewPOsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label6)
                    .addGroup(ViewPOsLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ViewPOsLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(POForm)))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        ViewPOsLayout.setVerticalGroup(
            ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewPOsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Label6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewPOsLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ViewPOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(POForm)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        MainPanel.add(ViewPOs, "card5");

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 0, 660, 540));

        SidePanel.setBackground(new java.awt.Color(249, 143, 37));
        SidePanel.setFocusable(false);

        ExitButton.setBackground(new java.awt.Color(255, 51, 51));
        ExitButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.setBorderPainted(false);
        ExitButton.setMargin(new java.awt.Insets(2, 10, 3, 10));
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonAction(evt);
            }
        });

        HomeButton.setBackground(new java.awt.Color(102, 153, 255));
        HomeButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HomeButton.setText("Logout");
        HomeButton.setToolTipText("");
        HomeButton.setBorderPainted(false);
        HomeButton.setMargin(new java.awt.Insets(2, 10, 3, 10));
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mega (3).png"))); // NOI18N
        jLabel9.setText("jLabel6");
        jLabel9.setMaximumSize(new java.awt.Dimension(300, 288));
        jLabel9.setMinimumSize(new java.awt.Dimension(300, 288));
        jLabel9.setPreferredSize(new java.awt.Dimension(300, 288));
        jLabel9.setRequestFocusEnabled(false);

        Button5.setBackground(new java.awt.Color(173, 216, 230));
        Button5.setText("Update Stock");
        Button5.setActionCommand("Button3");
        Button5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ColouredHighlight(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ColorReset(evt);
            }
        });
        Button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button5Action(evt);
            }
        });

        Button3.setBackground(new java.awt.Color(173, 216, 230));
        Button3.setText("Purchase Orders");
        Button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ColouredHighlight(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ColorReset(evt);
            }
        });
        Button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3Action(evt);
            }
        });

        Button1.setBackground(new java.awt.Color(173, 216, 230));
        Button1.setText("InventoryList & Alerts");
        Button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ColouredHighlight(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ColorReset(evt);
            }
        });
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1Action(evt);
            }
        });

        Button2.setBackground(new java.awt.Color(173, 216, 230));
        Button2.setText("Home Dashboard");
        Button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ColouredHighlight(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ColorReset(evt);
            }
        });
        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2Action(evt);
            }
        });

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SidePanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Button1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addComponent(Button2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SidePanelLayout.createSequentialGroup()
                                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SidePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );

        Button5.getAccessibleContext().setAccessibleName("Button3");

        getContentPane().add(SidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    CardLayout cl;
    private void Button1Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1Action
        cl.show(MainPanel, "card3"); 
    }//GEN-LAST:event_Button1Action

    private void Button5Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button5Action
        cl.show(MainPanel, "card6");  
    }//GEN-LAST:event_Button5Action

    private void ExitButtonAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonAction
        this.dispose();
    }//GEN-LAST:event_ExitButtonAction

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void ColouredHighlight(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColouredHighlight
        JButton btn = (JButton) evt.getSource();
        btn.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_ColouredHighlight

    private void ColorReset(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColorReset
        JButton btn = (JButton) evt.getSource();
        btn.setBackground(new Color(173,216,230));    
    }//GEN-LAST:event_ColorReset

    private void Button2Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2Action
        cl.show(MainPanel, "card7");
    }//GEN-LAST:event_Button2Action

    private void Button3Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3Action
        cl.show(MainPanel, "card5");   
    }//GEN-LAST:event_Button3Action

    private void AllUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllUserBtnActionPerformed
        user.lowItemAlertSend("all");
    }//GEN-LAST:event_AllUserBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextArea1.setText(" ");
        lowItemText();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void specificUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specificUserBtnActionPerformed
        user.lowItemAlertSend(specificUserField.getText());
    }//GEN-LAST:event_specificUserBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        loadPOs();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PODetails();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void APOBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APOBtnActionPerformed
       loadAPOs();
    }//GEN-LAST:event_APOBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        UpdatePO();
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        IDNameBox.setText("");
        BfrBox.setText("");
        AfterBox.setText("");
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        submitUpdate();
        IDNameBox.setText("");
        BfrBox.setText("");
        AfterBox.setText("");
        customizeTable(SmallTable);
        customizeTable(InvenTable);
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void POFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POFormActionPerformed
        showPOForm();
    }//GEN-LAST:event_POFormActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        StockReport();
    }//GEN-LAST:event_jButton4ActionPerformed
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IMForm("test", "IM").setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APOBtn;
    private javax.swing.JTextField AfterBox;
    private javax.swing.JButton AllUserBtn;
    private javax.swing.JList<String> ApprovedList;
    private javax.swing.JTextField BfrBox;
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button5;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JTextField IDNameBox;
    private javax.swing.JPanel InvenNAlerts;
    private javax.swing.JTable InvenTable;
    private javax.swing.JLabel LSA1;
    private javax.swing.JLabel LSA2;
    private javax.swing.JLabel LSA3;
    private javax.swing.JLabel LSA4;
    private javax.swing.JLabel LSA5;
    private javax.swing.JLabel LSA6;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label3;
    private javax.swing.JLabel Label4;
    private javax.swing.JLabel Label5;
    private javax.swing.JLabel Label6;
    private javax.swing.JLabel Label7;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton POForm;
    private javax.swing.JList<String> POList;
    private javax.swing.JTextArea POTextArea;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JTable SmallTable;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JLabel TAI;
    private javax.swing.JLabel UAP;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JPanel UpdateStock;
    private javax.swing.JPanel ViewPOs;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton specificUserBtn;
    private javax.swing.JTextField specificUserField;
    // End of variables declaration//GEN-END:variables
    
    private void customizeTable(JTable table) {
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setResizingAllowed(false);
    table.setRowSelectionAllowed(true);

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
    for (List<String> item : user.getInvenList()) {
        model.addRow(new Object[]{item.get(0), item.get(1), item.get(2)});
    }

    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {

            } else if (column == 2) { // Quantity column
                try {
                    int qty = Integer.parseInt(table.getValueAt(row, column).toString());
                    c.setBackground(qty < 20 ? Color.RED : Color.WHITE);
                } catch (NumberFormatException e) {
                    c.setBackground(Color.WHITE);
                }
            } else {
                c.setBackground(Color.WHITE);
            }

            return c;
        }
    });

    table.repaint();
}

    private void homePageLoad(String username, String role){
        int totalQuantity = 0;
            this.cl = (CardLayout)(MainPanel.getLayout());
            cl.show(MainPanel, "card7");  
            user.checkAlert(username, role);
            jLabel11.setText("Welcome User: " + username);
            jLabel10.setText("Role: Inventory Manager");
            matchBars(user.botSix());
            int approvedAndResolvedCount = 0;
            user.makeBigList(POFilePath, PurchaseOrderTextList);

            for (List<String> po : PurchaseOrderTextList) {
                String status = po.get(4);     
                String resolution = po.get(6); 

                if (status.equalsIgnoreCase("Approved") && resolution.equalsIgnoreCase("Unresolved")) {
                    approvedAndResolvedCount++;

                }
            }
            
            for (List<String> item : user.getInvenList()) {
                totalQuantity += Integer.parseInt(item.get(2).trim());
            }
            UAP.setText(String.valueOf(approvedAndResolvedCount));
            TAI.setText(String.valueOf(totalQuantity));
        }

    private void matchBars(List<List<String>> bot6){
            List<String> nameList = new ArrayList<>();
            List<Integer> amountList = new ArrayList<>();

            List<JProgressBar> barsList = Arrays.asList(
                jProgressBar1,jProgressBar2,jProgressBar3,
                jProgressBar4,jProgressBar5,jProgressBar6);

            List<JLabel> labelList = Arrays.asList(
                LSA1,LSA2,LSA3,LSA4,LSA5,LSA6); 

            for(List<String> item : bot6){
                nameList.add(item.get(1));
                amountList.add(Integer.valueOf(item.get(2)));
            }

            for(int i = 0; i < barsList.size(); i++){
                JProgressBar bar = barsList.get(i);
                bar.setMinimum(0);
                bar.setMaximum(50);
                bar.setValue(amountList.get(i));
                bar.setStringPainted(true);        
            }
            for(int i = 0; i < labelList.size(); i++){
                JLabel label = labelList.get(i);
                label.setText(nameList.get(i));
            }

        }
    
    private void lowItemText(){
    StringBuilder builder = new StringBuilder();

    for (List<String> row : user.lowitemStock()) {
        builder.append(String.join(" | ", row)).append("\n");
    }

    jTextArea1.setText(builder.toString());
    builder.setLength(0);
    
    }
    
    private void loadPOs(){
        PurchaseOrderTextList.clear();
        user.makeBigList(POFilePath, PurchaseOrderTextList);
        
        DefaultListModel<String> model = new DefaultListModel<>();
        POList.setModel(model);
        PurchaseOrderList.clear();
        for(List<String> order : PurchaseOrderTextList){
            PurchaseOrderList.add(new PurchaseOrder(order));
            model.addElement(order.get(0));       
        }      
    }   
   
    private void PODetails(){
        StringBuilder builder = new StringBuilder();
        String selectedItem = POList.getSelectedValue();
        
            for (List<String> parts : PurchaseOrderTextList) {
                if (parts.get(0).equals(selectedItem)) {
                    builder.append("Purchase ID: ").append(parts.get(0)).append("\n");
                    builder.append("Date: ").append(parts.get(1)).append("\n");
                    builder.append("Item IDs: ").append(parts.get(2)).append("\n");
                    builder.append("Quantities: ").append(parts.get(3)).append("\n");
                    builder.append("Status: ").append(parts.get(4)).append("\n");
                    builder.append("Approved By: ").append(parts.get(5)).append("\n");
                    builder.append("Resolution: ").append(parts.get(6)).append("\n\n");
                    break;
                }
            }

            if (builder.length() == 0) {
                builder.append("No purchase orders found for this item.");
            }

            POTextArea.setText(builder.toString());
        }
        
    private void loadAPOs(){
        PurchaseOrderTextList.clear();
        user.makeBigList(POFilePath, PurchaseOrderTextList);
            
        DefaultListModel<String> model = new DefaultListModel<>();
        ApprovedList.setModel(model);
        System.out.println(PurchaseOrderTextList);
        
        for(List<String> order : PurchaseOrderTextList){
            if(order.get(4).equals("Approved") && order.get(6).equals("Unresolved")){
                ApprovedOrderList.add(order);
                    String displayText = String.format(
                    "PurchaseID: %s | Date: %s | ItemIDs: %s | Quantities: %s | Status: %s | Approved By: %s |",
                    order.get(0), order.get(1), order.get(2), order.get(3),
                    order.get(4), order.get(5) 
                );
                model.addElement(displayText);
            }
        }
    }
    
    private void UpdatePO(){
        int index = ApprovedList.getSelectedIndex();
        if(index == -1){
            return;
        }
        System.out.println(index);

        focusAPO = ApprovedOrderList.get(index);
        itemIDs = focusAPO.get(2).split("/");     
        incomingQty = focusAPO.get(3).split("/"); 

        StringBuilder bfr = new StringBuilder();
        StringBuilder aft = new StringBuilder();

        for (int i = 0; i < itemIDs.length; i++) {
            String id = itemIDs[i];
            String incoming = incomingQty[i];

            for (List<String> item : user.getInvenList()) {
                if (item.get(0).equals(id)) {
                    int oldQty = Integer.parseInt(item.get(2));
                    int newQty = oldQty + Integer.parseInt(incoming);

                    bfr.append(oldQty).append("/");
                    aft.append(newQty).append("/");
                    break;
                }
            }
        }
        if (bfr.length() > 0) bfr.setLength(bfr.length() - 1);
        if (aft.length() > 0) aft.setLength(aft.length() - 1);

        IDNameBox.setText(focusAPO.get(2));
        BfrBox.setText(bfr.toString());
        AfterBox.setText(aft.toString());
    }
    
    private void submitUpdate(){
        
        StringBuilder confirmText = new StringBuilder("Confirm Inventory Update:\n");
        for (int i = 0; i < itemIDs.length; i++) {
            confirmText.append("Item: ").append(itemIDs[i])
                       .append(" | Add: ").append(incomingQty[i]).append("\n");
            }
        
        int choice = JOptionPane.showConfirmDialog(
            null,
            confirmText.toString(),
            "Inventory Update",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            for (int i = 0; i < itemIDs.length; i++) {
                String itemID = itemIDs[i];
                int addQty = Integer.parseInt(incomingQty[i]);

                for (List<String> item : user.getInvenList()) {
                    if (item.get(0).equals(itemID)) {
                        int newTotal = Integer.parseInt(item.get(2)) + addQty;
                        user.updateStock(itemID, newTotal);
                        break;
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Inventory updated successfully.");
            focusAPO.set(6, "Resolved");
            user.updateTextFile(PurchaseOrderTextList, POFilePath);
        } else {
            JOptionPane.showMessageDialog(null, "Update cancelled.");
        }
    }
    
    private void showPOForm(){
        String selectedItem = POList.getSelectedValue();
            for (PurchaseOrder PO : PurchaseOrderList) {
                if (PO.getPoID().equals(selectedItem)) {
                    System.out.println(selectedItem);
                    PO.showPO();
                }
            }
    }
    
    private void StockReport(){
        StockReport stockReport = new StockReport(user.getInvenList());
        stockReport.createStockReportPopup();
    }
}