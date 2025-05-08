package UserLogin;
import Admin.AdminPanel;
import FinanceManager.resources.General;
import InventoryManager.IMForm;
import PM.PMForm1;
import SM.SMForm;
import java.util.*;
import javax.swing.JOptionPane;



public class LoginPage extends javax.swing.JFrame {
    User tempUser = new User();
    Map<String, String> roleMap = new HashMap<>() {{
        put("Admin", "Admin");
        put("Finance Manager (FM)", "FM");
        put("Inventory Manager (IM)", "IM");
        put("Purchase Manager (PM)", "PM");
        put("Sales Manager (SM)", "SM");
    }};
    

    public LoginPage() {
        initComponents();
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        IMForm = new javax.swing.JButton();
        RoleCombo = new javax.swing.JComboBox<>();
        userField = new javax.swing.JTextField();
        passField = new javax.swing.JTextField();
        LoginBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IMForm");

        IMForm.setText("IMForm");
        IMForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IMFormActionPerformed(evt);
            }
        });

        RoleCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Finance Manager (FM)", "Inventory Manager (IM)", "Purchase Manager (PM)", "Sales Manager (SM)", " " }));
        RoleCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoleComboActionPerformed(evt);
            }
        });

        userField.setText("jTextField1");

        passField.setText("jTextField2");

        LoginBtn.setText("Login");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("username");

        jLabel3.setText("password");

        jLabel4.setText("role");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RoleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(IMForm))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LoginBtn)
                        .addGap(137, 137, 137))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(RoleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LoginBtn)
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IMForm)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IMFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IMFormActionPerformed
        IMForm InvenForm = new IMForm();
        InvenForm.setVisible(true);
        
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_IMFormActionPerformed

    private void RoleComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoleComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoleComboActionPerformed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        tempUser.makeLoginList(tempUser.FullUserList, roleMap.get(RoleCombo.getSelectedItem()));
        Login(tempUser.SpecifiedUserList);
    }//GEN-LAST:event_LoginBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IMForm;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JComboBox<String> RoleCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField passField;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables

    public void Login(List<List<String>> userList){
        String userLog = userField.getText();
        String passLog = passField.getText();
        boolean foundUser = false;
        List<String> focusUser = new ArrayList<>();
        boolean Verified = false;
        try{
            for(List<String> user : userList){ 
                if(userLog.equals(user.get(1))){
                    foundUser = true;  
                    focusUser = user;
                    break;
                }
            }
            if(foundUser != true){
                JOptionPane.showMessageDialog(
                null,
                "User Not found!",
                "Login Failed",
                JOptionPane.WARNING_MESSAGE);
            }
            for(String pswd : focusUser){
                if(passLog.equals(pswd)){
                    Verified = true;
                    System.out.println("Logged in");
                    break;
                }  
            } 
            if(Verified != true){ 
                JOptionPane.showMessageDialog(
                null,
                "Password Incorrect",
                "Login Failed",
                JOptionPane.WARNING_MESSAGE);}
            else{
                switch(RoleCombo.getSelectedIndex()){
                    case 0:
                        AdminPanel adminPanel = new AdminPanel();
                        adminPanel.setVisible(true);
                        this.dispose();
                        break;
                    case 1:
                        FinanceManager.resources.General FMFormGeneral = new FinanceManager.resources.General();
                        FMFormGeneral.setVisible(true);
                        this.dispose();
                        break;
                    case 2:
                        IMForm iMForm = new IMForm();
                        iMForm.setVisible(true);
                        this.dispose();
                        break;
                    case 3:
                        PMForm1 pMForm = new PMForm1();
                        pMForm.setVisible(true);
                        this.dispose();
                        break;
                    case 4:
                        SMForm sMForm = new SMForm();
                        sMForm.setVisible(true);
                        this.dispose();
                        break;
                }          
            }  
        } catch (Exception e){
            System.out.println(e);
        }
    }    
}
