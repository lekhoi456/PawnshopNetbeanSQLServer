package View;

import Controller.Validation;
import Model.AssetModel;
import Model.ContractModel;
import Model.CustomerModel;
import static View.Home.contractSelected;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KhoiLeQuoc
 */
public class Redeem extends javax.swing.JFrame {

    /**
     * Creates new form Redeem
     */
    public Redeem() {
        initComponents();
        this.setLocationRelativeTo(null);
        Image favicon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/favicon.png"));
        this.setIconImage(favicon);
        showInfo();

    }

    public void showInfo() {
        try {
            contractModel = new ContractModel();
            customerModel = new CustomerModel();
        } catch (Exception ex) {
            Logger.getLogger(Redeem.class.getName()).log(Level.SEVERE, null, ex);
        }
        int customerIdSelected = 0;
        for (int i = 0; i < contractModel.getList().size(); i++) {
            if (contractModel.getList().get(i).getContractId() == Home.contractSelected) {
                txtContractId.setText(Integer.toString(Home.contractSelected));
                customerIdSelected = contractModel.getList().get(i).getCustomerId();
                txtAssetName.setText(contractModel.getList().get(i).getAssetName());
                txtLoanAmount.setText(Validation.convertLongFormat(contractModel.getList().get(i).getTotalLoanAmount()));
                txtInterestRate.setText(Validation.convertLongFormat(contractModel.getList().get(i).getInterestRate()));
                txtStartDate.setText(contractModel.getList().get(i).getStartDate());
                txtEndDate.setText(contractModel.getList().get(i).getEndDate());
                txtTotalMoney.setText(Validation.convertLongFormat(contractModel.getList().get(i).getTotalMoney()));
            }
        }
        for (int i = 0; i < customerModel.getList().size(); i++) {
            if (customerModel.getList().get(i).getCustomerId() == customerIdSelected) {
                txtCustomerName.setText(customerModel.getList().get(i).getCustomerName());
            }
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

        lblTotalMoney = new javax.swing.JLabel();
        txtTotalMoney = new javax.swing.JLabel();
        lbEndDate = new javax.swing.JLabel();
        txtEndDate = new javax.swing.JLabel();
        lblInterestRate = new javax.swing.JLabel();
        txtInterestRate = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JLabel();
        lblLoanAmount = new javax.swing.JLabel();
        txtLoanAmount = new javax.swing.JLabel();
        txtAssetName = new javax.swing.JLabel();
        lblAssetName = new javax.swing.JLabel();
        lblCustomerName = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JLabel();
        txtContractId = new javax.swing.JLabel();
        lblContractId = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnRedeem = new javax.swing.JButton();
        bgRedeem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Redeem Asset");
        setMaximumSize(new java.awt.Dimension(400, 610));
        setMinimumSize(new java.awt.Dimension(400, 610));
        setName("redeemAsset"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 610));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalMoney.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalMoney.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblTotalMoney.setForeground(new java.awt.Color(30, 30, 30));
        lblTotalMoney.setText("Total Money:");
        getContentPane().add(lblTotalMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        txtTotalMoney.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalMoney.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtTotalMoney.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtTotalMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 455, 200, 30));

        lbEndDate.setBackground(new java.awt.Color(255, 255, 255));
        lbEndDate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lbEndDate.setForeground(new java.awt.Color(30, 30, 30));
        lbEndDate.setText("End date:");
        getContentPane().add(lbEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        txtEndDate.setBackground(new java.awt.Color(255, 255, 255));
        txtEndDate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtEndDate.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, 200, 30));

        lblInterestRate.setBackground(new java.awt.Color(255, 255, 255));
        lblInterestRate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblInterestRate.setForeground(new java.awt.Color(30, 30, 30));
        lblInterestRate.setText("Interest Rate:");
        getContentPane().add(lblInterestRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtInterestRate.setBackground(new java.awt.Color(255, 255, 255));
        txtInterestRate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtInterestRate.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtInterestRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 305, 200, 30));

        lblStartDate.setBackground(new java.awt.Color(255, 255, 255));
        lblStartDate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(30, 30, 30));
        lblStartDate.setText("Start date:");
        getContentPane().add(lblStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        txtStartDate.setBackground(new java.awt.Color(255, 255, 255));
        txtStartDate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtStartDate.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 355, 200, 30));

        lblLoanAmount.setBackground(new java.awt.Color(255, 255, 255));
        lblLoanAmount.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblLoanAmount.setForeground(new java.awt.Color(30, 30, 30));
        lblLoanAmount.setText("Loan amount:");
        getContentPane().add(lblLoanAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        txtLoanAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtLoanAmount.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtLoanAmount.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtLoanAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 245, 200, 30));

        txtAssetName.setBackground(new java.awt.Color(255, 255, 255));
        txtAssetName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtAssetName.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtAssetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 195, 200, 30));

        lblAssetName.setBackground(new java.awt.Color(255, 255, 255));
        lblAssetName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblAssetName.setForeground(new java.awt.Color(30, 30, 30));
        lblAssetName.setText("Asset:");
        getContentPane().add(lblAssetName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        lblCustomerName.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomerName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblCustomerName.setForeground(new java.awt.Color(30, 30, 30));
        lblCustomerName.setText("Customer:");
        getContentPane().add(lblCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txtCustomerName.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 145, 200, 30));

        txtContractId.setBackground(new java.awt.Color(255, 255, 255));
        txtContractId.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        txtContractId.setForeground(new java.awt.Color(30, 30, 30));
        getContentPane().add(txtContractId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 85, 200, 30));

        lblContractId.setBackground(new java.awt.Color(255, 255, 255));
        lblContractId.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        lblContractId.setForeground(new java.awt.Color(30, 30, 30));
        lblContractId.setText("Contract ID:");
        getContentPane().add(lblContractId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

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
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, -1, -1));

        btnRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconRedeem.png"))); // NOI18N
        btnRedeem.setBorder(null);
        btnRedeem.setBorderPainted(false);
        btnRedeem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRedeem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRedeemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRedeemMouseExited(evt);
            }
        });
        btnRedeem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedeemActionPerformed(evt);
            }
        });
        getContentPane().add(btnRedeem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, -1, -1));

        bgRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pnlRedeemAsset.png"))); // NOI18N
        getContentPane().add(bgRedeem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRedeemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRedeemMouseEntered
        btnRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconRedeemHover.png")));
    }//GEN-LAST:event_btnRedeemMouseEntered

    private void btnRedeemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRedeemMouseExited
        btnRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconRedeem.png")));
    }//GEN-LAST:event_btnRedeemMouseExited

    private void btnRedeemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedeemActionPerformed
        redeemAsset();
        JOptionPane.showMessageDialog(null, "Redeem Asset successfully.", "Notification", 1);
        callHome();
    }//GEN-LAST:event_btnRedeemActionPerformed

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCancelHover.png")));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCancel.png")));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        callHome();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void redeemAsset() {
        try {
            String redeemday = dateFormat.format(Validation.currentDate());
            contractModel.redeemContract(Home.contractSelected, redeemday);
        } catch (SQLException ex) {
            Logger.getLogger(Redeem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void callHome() {
        Home home = new Home();
        home.cardLayout.show(home.pnlRight, "pnlCardPawnedOff");
        home.setVisible(true);
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(Redeem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Redeem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Redeem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Redeem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Redeem().setVisible(true);
            }
        });
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ContractModel contractModel;
    private CustomerModel customerModel;
    private long redeemValid;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgRedeem;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRedeem;
    private javax.swing.JLabel lbEndDate;
    private javax.swing.JLabel lblAssetName;
    private javax.swing.JLabel lblContractId;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblInterestRate;
    private javax.swing.JLabel lblLoanAmount;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblTotalMoney;
    private javax.swing.JLabel txtAssetName;
    private javax.swing.JLabel txtContractId;
    private javax.swing.JLabel txtCustomerName;
    private javax.swing.JLabel txtEndDate;
    private javax.swing.JLabel txtInterestRate;
    private javax.swing.JLabel txtLoanAmount;
    private javax.swing.JLabel txtStartDate;
    private javax.swing.JLabel txtTotalMoney;
    // End of variables declaration//GEN-END:variables
}