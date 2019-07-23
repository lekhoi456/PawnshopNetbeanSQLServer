/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Validation;
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
public class EditEmployee extends javax.swing.JFrame {

    /**
     * Creates new form EditEmployee
     */
    public EditEmployee() {
        initComponents();
        empIdEdit = Home.empIdLoadToEditEmp;
        setEmployeeInfo();
        this.setLocationRelativeTo(null);
        Image favicon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/favicon.png"));
        this.setIconImage(favicon);

    }

    public void setEmployeeInfo() {
        try {
            employeeModel = new EmployeeModel();
            for (int i = 0; i < employeeModel.getList().size(); i++) {
                if (employeeModel.getList().get(i).getEmpId() == empIdEdit) {
                    txtEmployeeName.setText(employeeModel.getList().get(i).getFullName());
                    txtPhoneNumber.setText(employeeModel.getList().get(i).getPhoneNumber());
                    txtEmail.setText(employeeModel.getList().get(i).getEmail());
                    txtAddress.setText(employeeModel.getList().get(i).geteAddress());
                    int isEmpActive = employeeModel.getList().get(i).getIsActive();
                    if (isEmpActive != 0) {
                        chkDeactive.setSelected(true);
                    }
                    if (employeeModel.getList().get(i).geteRole().equalsIgnoreCase("mod")) {
                        chkSetMod.setSelected(true);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EditEmployee.class.getName()).log(Level.SEVERE, null, ex);
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

        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        chkDeactive = new javax.swing.JCheckBox();
        chkSetMod = new javax.swing.JCheckBox();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtEmployeeName = new javax.swing.JLabel();
        lblEmployeeName = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        bgEditEmployee = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Edit Employee");
        setMaximumSize(new java.awt.Dimension(500, 440));
        setMinimumSize(new java.awt.Dimension(500, 440));
        setPreferredSize(new java.awt.Dimension(500, 440));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCancel.png"))); // NOI18N
        btnCancel.setBorder(null);
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

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
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        chkDeactive.setBackground(new java.awt.Color(255, 255, 255));
        chkDeactive.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        chkDeactive.setForeground(new java.awt.Color(30, 30, 30));
        chkDeactive.setText("Deactive");
        chkDeactive.setBorder(null);
        chkDeactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDeactiveActionPerformed(evt);
            }
        });
        getContentPane().add(chkDeactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        chkSetMod.setBackground(new java.awt.Color(255, 255, 255));
        chkSetMod.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        chkSetMod.setForeground(new java.awt.Color(30, 30, 30));
        chkSetMod.setText("Set Employee to Moderator");
        chkSetMod.setBorder(null);
        chkSetMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSetModActionPerformed(evt);
            }
        });
        getContentPane().add(chkSetMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        lblAddress.setBackground(new java.awt.Color(255, 255, 255));
        lblAddress.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(30, 30, 30));
        lblAddress.setText("Address:");
        getContentPane().add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        txtAddress.setBackground(new java.awt.Color(250, 250, 250));
        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(30, 30, 30));
        txtAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAddress.setBorder(null);
        txtAddress.setMaximumSize(new java.awt.Dimension(220, 30));
        txtAddress.setMinimumSize(new java.awt.Dimension(220, 30));
        txtAddress.setPreferredSize(new java.awt.Dimension(220, 30));
        txtAddress.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 260, 30));

        lblEmail.setBackground(new java.awt.Color(255, 255, 255));
        lblEmail.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(30, 30, 30));
        lblEmail.setText("Email:");
        getContentPane().add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txtEmail.setBackground(new java.awt.Color(250, 250, 250));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(30, 30, 30));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setBorder(null);
        txtEmail.setMaximumSize(new java.awt.Dimension(220, 30));
        txtEmail.setMinimumSize(new java.awt.Dimension(220, 30));
        txtEmail.setPreferredSize(new java.awt.Dimension(220, 30));
        txtEmail.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 260, 30));

        txtPhoneNumber.setBackground(new java.awt.Color(250, 250, 250));
        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber.setForeground(new java.awt.Color(30, 30, 30));
        txtPhoneNumber.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPhoneNumber.setBorder(null);
        txtPhoneNumber.setMaximumSize(new java.awt.Dimension(220, 30));
        txtPhoneNumber.setMinimumSize(new java.awt.Dimension(220, 30));
        txtPhoneNumber.setPreferredSize(new java.awt.Dimension(220, 30));
        txtPhoneNumber.setSelectionColor(new java.awt.Color(119, 91, 200));
        getContentPane().add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 260, 30));

        txtEmployeeName.setBackground(new java.awt.Color(255, 255, 255));
        txtEmployeeName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtEmployeeName.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtEmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 260, 30));

        lblEmployeeName.setBackground(new java.awt.Color(255, 255, 255));
        lblEmployeeName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblEmployeeName.setForeground(new java.awt.Color(30, 30, 30));
        lblEmployeeName.setText("Edit Employee:");
        getContentPane().add(lblEmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        lblPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        lblPhoneNumber.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblPhoneNumber.setForeground(new java.awt.Color(30, 30, 30));
        lblPhoneNumber.setText("Phone number:");
        getContentPane().add(lblPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        bgEditEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pnlEditEmployee.png"))); // NOI18N
        getContentPane().add(bgEditEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkSetModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSetModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSetModActionPerformed

    private void chkDeactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDeactiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDeactiveActionPerformed

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconSaveHover.png")));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconSave.png")));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCancelHover.png")));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCancel.png")));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        callHome();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean isOk = true;
        String message = "";
        if (!Validation.phoneNumber(txtPhoneNumber.getText())) {
            isOk = false;
            message = message + "- Phone number must be 10+ digits.\n";
        }
        if (!Validation.email(txtEmail.getText())) {
            isOk = false;
            message = message + "- Email must be a email@address.\n";
        }
        if (isOk == false) {
            JOptionPane.showMessageDialog(null, message, "Error list:", 0);
        } else {
            try {
                saveAllEmployee();
            } catch (SQLException ex) {
                Logger.getLogger(EditEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Saving employee successfully.", "Notification", 1);
            callHome();
        }
        callHome();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void saveAllEmployee() throws SQLException {
        String role = "Staff";
        if (chkSetMod.isSelected()) {
            role = "Mod";
        }
        int isActive = 0;
        if (chkDeactive.isSelected()) {
            isActive = 1;
        }
        employeeModel.updateEmployeeInfo(empIdEdit, txtPhoneNumber.getText(), txtEmail.getText(), txtAddress.getText(), role, isActive);
    }

    private void callHome() {
        Home home = new Home();
        home.cardLayout.show(home.pnlRight, "pnlCardEmployee");
        home.setVisible(true);
        this.dispose();
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
//            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EditEmployee().setVisible(true);
//            }
//        });
//    }
    private static int empIdEdit;
    private EmployeeModel employeeModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgEditEmployee;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkDeactive;
    private javax.swing.JCheckBox chkSetMod;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmployeeName;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtEmployeeName;
    private javax.swing.JTextField txtPhoneNumber;
    // End of variables declaration//GEN-END:variables

}
