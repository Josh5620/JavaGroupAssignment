package InventoryManager;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class IMForm extends javax.swing.JFrame {

    public IMForm() {
                
        initComponents();
        this.cl = (CardLayout)(MainPanel.getLayout());
        cl.show(MainPanel, "card7");  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        Panel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Panel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Panel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Panel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Panel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        HomePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        SidePanel = new javax.swing.JPanel();
        ExitButton = new javax.swing.JButton();
        Button1 = new javax.swing.JButton();
        Button4 = new javax.swing.JButton();
        Button5 = new javax.swing.JButton();
        HomeButton = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Button2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(850, 490));

        MainPanel.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Panel2");

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel2)
                .addContainerGap(402, Short.MAX_VALUE))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel2)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        MainPanel.add(Panel2, "card2");

        jLabel3.setText("Panel3");

        javax.swing.GroupLayout Panel3Layout = new javax.swing.GroupLayout(Panel3);
        Panel3.setLayout(Panel3Layout);
        Panel3Layout.setHorizontalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel3)
                .addContainerGap(486, Short.MAX_VALUE))
        );
        Panel3Layout.setVerticalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel3Layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(180, 180, 180))
        );

        MainPanel.add(Panel3, "card3");

        jLabel4.setText("Panel4");

        javax.swing.GroupLayout Panel4Layout = new javax.swing.GroupLayout(Panel4);
        Panel4.setLayout(Panel4Layout);
        Panel4Layout.setHorizontalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel4Layout.createSequentialGroup()
                .addContainerGap(320, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(291, 291, 291))
        );
        Panel4Layout.setVerticalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel4Layout.createSequentialGroup()
                .addContainerGap(295, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(199, 199, 199))
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
                .addContainerGap(364, Short.MAX_VALUE))
        );
        Panel5Layout.setVerticalGroup(
            Panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel5Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel5)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        MainPanel.add(Panel5, "card5");

        jLabel6.setText("Panel6");

        javax.swing.GroupLayout Panel6Layout = new javax.swing.GroupLayout(Panel6);
        Panel6.setLayout(Panel6Layout);
        Panel6Layout.setHorizontalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel6Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel6)
                .addContainerGap(359, Short.MAX_VALUE))
        );
        Panel6Layout.setVerticalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel6Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(244, 244, 244))
        );

        MainPanel.add(Panel6, "card6");

        jLabel7.setText("HomePanel");

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel7)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePanelLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel7)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        MainPanel.add(HomePanel, "card7");

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

        Button1.setBackground(new java.awt.Color(173, 216, 230));
        Button1.setText("Button1");
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

        jLabel1.setText("jLabel1");

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

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SidePanelLayout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Button5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Button5.getAccessibleContext().setAccessibleName("Button3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    CardLayout cl;
    private void Button1Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1Action
        cl.show(MainPanel, "card2");  
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
        javax.swing.JButton btn = (JButton) evt.getSource();
        btn.setBackground(new Color(173,216,230));    
    }//GEN-LAST:event_ColorReset

    private void Button2Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2Action
        cl.show(MainPanel, "card3");  
    }//GEN-LAST:event_Button2Action

    private void Button3Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3Action
        cl.show(MainPanel, "card4");  
    }//GEN-LAST:event_Button3Action

    private void Button4Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button4Action
        cl.show(MainPanel, "card5");  
    }//GEN-LAST:event_Button4Action
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IMForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JButton Button5;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton HomeButton;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;
    private javax.swing.JPanel Panel4;
    private javax.swing.JPanel Panel5;
    private javax.swing.JPanel Panel6;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
