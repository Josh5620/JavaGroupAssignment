package InventoryManager;
import javax.swing.*;
import java.awt.event.*;
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
        customizeTable();
        homePageLoad(username,role);
    }
    InvenUser user = new InvenUser();
    
    
    
    
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
        Panel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Panel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Panel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Panel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SidePanel = new javax.swing.JPanel();
        ExitButton = new javax.swing.JButton();
        HomeButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Button4 = new javax.swing.JButton();
        Button5 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Button1 = new javax.swing.JButton();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(InvenNAlertsLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(Label3)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );
        InvenNAlertsLayout.setVerticalGroup(
            InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvenNAlertsLayout.createSequentialGroup()
                .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InvenNAlertsLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
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
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(InvenNAlertsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(InvenNAlertsLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(Label3)
                    .addContainerGap(458, Short.MAX_VALUE)))
        );

        MainPanel.add(InvenNAlerts, "card3");

        jLabel6.setText("Panel6");

        javax.swing.GroupLayout Panel6Layout = new javax.swing.GroupLayout(Panel6);
        Panel6.setLayout(Panel6Layout);
        Panel6Layout.setHorizontalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel6Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel6)
                .addContainerGap(373, Short.MAX_VALUE))
        );
        Panel6Layout.setVerticalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel6Layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(244, 244, 244))
        );

        MainPanel.add(Panel6, "card6");

        jLabel4.setText("Panel4");

        javax.swing.GroupLayout Panel4Layout = new javax.swing.GroupLayout(Panel4);
        Panel4.setLayout(Panel4Layout);
        Panel4Layout.setHorizontalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(291, 291, 291))
        );
        Panel4Layout.setVerticalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(546, 546, 546))
        );

        MainPanel.add(Panel4, "card4");

        jLabel5.setText("Panel5");

        javax.swing.GroupLayout Panel5Layout = new javax.swing.GroupLayout(Panel5);
        Panel5.setLayout(Panel5Layout);
        Panel5Layout.setHorizontalGroup(
            Panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel5Layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel5)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        Panel5Layout.setVerticalGroup(
            Panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel5Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel5)
                .addContainerGap(295, Short.MAX_VALUE))
        );

        MainPanel.add(Panel5, "card5");

        jLabel2.setText("Panel2");

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ItemID", "ItemName", "Supplier", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(186, 186, 186))))
        );

        MainPanel.add(Panel2, "card2");

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
        HomeButton.setText("Home Page");
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

        Button4.setBackground(new java.awt.Color(173, 216, 230));
        Button4.setText("jButton3");
        Button4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ColouredHighlight(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ColorReset(evt);
            }
        });
        Button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button4Action(evt);
            }
        });

        Button5.setBackground(new java.awt.Color(173, 216, 230));
        Button5.setText("jButton3");
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

        Button2.setBackground(new java.awt.Color(173, 216, 230));
        Button2.setText("jButton3");
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

        Button3.setBackground(new java.awt.Color(173, 216, 230));
        Button3.setText("jButton3");
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

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SidePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SidePanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Button4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addComponent(Button3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Button2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Button1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SidePanelLayout.createSequentialGroup()
                                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Button5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        cl.show(MainPanel, "card7");  
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
        cl.show(MainPanel, "card4");  
    }//GEN-LAST:event_Button2Action

    private void Button3Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3Action
        cl.show(MainPanel, "card2");  
    }//GEN-LAST:event_Button3Action

    private void Button4Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button4Action
        cl.show(MainPanel, "card5");  
    }//GEN-LAST:event_Button4Action

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
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IMForm("test", "IM").setVisible(true);
            }
        });
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AllUserBtn;
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JButton Button5;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JPanel HomePanel;
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
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel4;
    private javax.swing.JPanel Panel5;
    private javax.swing.JPanel Panel6;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JLabel TAI;
    private javax.swing.JLabel UAP;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton specificUserBtn;
    private javax.swing.JTextField specificUserField;
    // End of variables declaration//GEN-END:variables
    
    private void customizeTable(){
        InvenTable.getTableHeader().setReorderingAllowed(false);
        InvenTable.getTableHeader().setResizingAllowed(false);
        InvenTable.setRowSelectionAllowed(true);

        // Adjust column widths for 3 columns
        InvenTable.getColumnModel().getColumn(0).setPreferredWidth(75);   // ID
        InvenTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Name
        InvenTable.getColumnModel().getColumn(2).setPreferredWidth(100);  // Quantity

        DefaultTableModel model = (DefaultTableModel) InvenTable.getModel();
        model.setRowCount(0); 

        // Add only 3 columns worth of data
        for(List<String> item : user.getInvenList()){
            model.addRow(new Object[]{item.get(0), item.get(1), item.get(2)});
        }

        // Custom renderer to highlight low stock (quantity < 10)
        InvenTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column){

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
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

        InvenTable.repaint();
    }

    private void homePageLoad(String username, String role){
        this.cl = (CardLayout)(MainPanel.getLayout());
        cl.show(MainPanel, "card7");  
        user.checkAlert(username, role);
        jLabel11.setText("Welcome User: " + username);
        jLabel10.setText("Role: " + role);
        matchBars(user.botSix());
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
}
