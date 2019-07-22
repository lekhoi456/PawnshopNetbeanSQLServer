/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CheckAccount;
import Controller.HashPW;
import Model.EmployeeModel;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KhoiLeQuoc
 */
public class ChangePassword extends javax.swing.JFrame {

    /**
     * Creates new form ChangePassword
     */
    public ChangePassword() {
        initComponents();
        empIdEdit = Home.empIdLoadToEditEmp;
        userNameLogged = Login.userNameLogged;
        this.setLocationRelativeTo(null);
        Image favicon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/favicon.png"));
        this.setIconImage(favicon);
        try {
            employeeModel = new EmployeeModel();
            checkAccount = new CheckAccount();
        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
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

        btnSave = new javax.swing.JButton();
        lblOldPW = new javax.swing.JLabel();
        txtOldPW = new javax.swing.JPasswordField();
        lblNewPW1 = new javax.swing.JLabel();
        txtNewPW1 = new javax.swing.JPasswordField();
        lblNewPW2 = new javax.swing.JLabel();
        txtNewPW2 = new javax.swing.JPasswordField();
        bgChangePW = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Password");
        setMaximumSize(new java.awt.Dimension(400, 420));
        setMinimumSize(new java.awt.Dimension(400, 420));
        setName("changepassword"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 420));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconSave.png"))); // NOI18N
        btnSave.setBorder(null);
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 340, -1, -1));

        lblOldPW.setBackground(new java.awt.Color(255, 255, 255));
        lblOldPW.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblOldPW.setForeground(new java.awt.Color(30, 30, 30));
        lblOldPW.setText("Old Password:");
        getContentPane().add(lblOldPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        txtOldPW.setBackground(new java.awt.Color(242, 243, 248));
        txtOldPW.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtOldPW.setForeground(new java.awt.Color(30, 30, 30));
        txtOldPW.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtOldPW.setBorder(null);
        txtOldPW.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtOldPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 260, 30));

        lblNewPW1.setBackground(new java.awt.Color(255, 255, 255));
        lblNewPW1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblNewPW1.setForeground(new java.awt.Color(30, 30, 30));
        lblNewPW1.setText("New Password:");
        getContentPane().add(lblNewPW1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        txtNewPW1.setBackground(new java.awt.Color(242, 243, 248));
        txtNewPW1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNewPW1.setForeground(new java.awt.Color(30, 30, 30));
        txtNewPW1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNewPW1.setBorder(null);
        txtNewPW1.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtNewPW1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 260, 30));

        lblNewPW2.setBackground(new java.awt.Color(255, 255, 255));
        lblNewPW2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblNewPW2.setForeground(new java.awt.Color(30, 30, 30));
        lblNewPW2.setText("Confirm Password:");
        getContentPane().add(lblNewPW2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        txtNewPW2.setBackground(new java.awt.Color(242, 243, 248));
        txtNewPW2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNewPW2.setForeground(new java.awt.Color(30, 30, 30));
        txtNewPW2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNewPW2.setBorder(null);
        txtNewPW2.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtNewPW2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 260, 30));

        bgChangePW.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pnlChangePassword.png"))); // NOI18N
        getContentPane().add(bgChangePW, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconSaveHover.png")));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconSave.png")));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean isOk = true;
        String message = "";
        try {
            System.out.println("userNameLogged: " + userNameLogged);
            canChange = checkAccount.isTrue(userNameLogged, txtOldPW.getText());
        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (canChange == false) {
            isOk = false;
            message = message + "- The old password does not match.\n";
        } else {
            if (txtNewPW1.getText().isEmpty()) {
                isOk = false;
                message = message + "- New Password can't be empty.\n";
            }
            if (!txtNewPW2.getText().endsWith(txtNewPW1.getText())) {
                isOk = false;
                message = message + "- Confirm password does not match.\n";
            }
        }
        if (isOk == false) {
            JOptionPane.showMessageDialog(null, message, "Error list:", 0);
        } else {
            try {
                savePassword();
            } catch (SQLException ex) {
                Logger.getLogger(EditEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Change password successfully.", "Notification", 1);
            callHome();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void callHome() {
        Home home = new Home();
        home.cardLayout.show(home.pnlRight, "pnlCardEmployee");
        home.setVisible(true);
        this.dispose();
    }

    private void savePassword() throws SQLException {
        employeeModel.changePassword(userNameLogged, txtNewPW2.getText());
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChangePassword().setVisible(true);
//            }
//        });
//    }
    public static String userNameLogged = "";
    private static CheckAccount checkAccount;
    private static int empIdEdit;
    private boolean canChange = false;
    private EmployeeModel employeeModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgChangePW;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblNewPW1;
    private javax.swing.JLabel lblNewPW2;
    private javax.swing.JLabel lblOldPW;
    private javax.swing.JPasswordField txtNewPW1;
    private javax.swing.JPasswordField txtNewPW2;
    private javax.swing.JPasswordField txtOldPW;
    // End of variables declaration//GEN-END:variables

}
