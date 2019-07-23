package View;

import Utills.Validation;
import Model.AssetModel;
import Model.ContractModel;
import Model.CustomerModel;
import Model.EmployeeModel;
import Model.StoreModel;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Home Dashboard
 *
 * @author KhoiLeQuoc
 */
public class Home extends javax.swing.JFrame {

    public static CardLayout cardLayout;
    DefaultTableModel tableDashboard;
    DefaultTableModel tableContract;
    DefaultTableModel tableCustomer;
    DefaultTableModel tableEmployee;

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        Image favicon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/favicon.png"));
        this.setIconImage(favicon);
        cardLayout = (CardLayout) (pnlRight.getLayout());
        try {
            initContract();
            initpnlDashboard();
            initpnlStore();
            initpnlCustomer();
            initpnEmployee();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * init panel dashboard
     *
     * @throws Exception
     */
    private void initpnlDashboard() throws Exception {
        this.storeModel = new StoreModel();
        lblAmountOfContract.setText(Validation.convertLongFormat(contractModel.getFreeContractId() - 1));
        long moneyInvestment = 0;
        long totalInterestEarn = 0;
        for (int i = 0; i < contractModel.getList().size(); i++) {
            moneyInvestment += contractModel.getList().get(i).getTotalLoanAmount();
            totalInterestEarn += contractModel.getList().get(i).getCreditPeriod() * contractModel.getList().get(i).getInterestRate();
        }
        for (int i = 0; i < storeModel.getList().size(); i++) {
            lblTotalCashFund.setText(Validation.convertLongFormat(storeModel.getList().get(i).getCashFund()));

            lblMoneyInvestment.setText(Validation.convertLongFormat(moneyInvestment));
            lblTotalInterestEarn.setText(Validation.convertLongFormat(totalInterestEarn));
        }

        tableDashboard = (DefaultTableModel) tblDashboard.getModel();
        tableDashboard.setRowCount(0);
        Date dateNow = Validation.currentDate();
        String dateNowStr = dateFormat.format(dateNow);
        for (int i = 0; i < contractModel.getList().size(); i++) {
            if (contractModel.getList().get(i).getStartDate().equalsIgnoreCase(dateNowStr)) {
                int contractId = contractModel.getList().get(i).getContractId();
                String customerName = customerModel.getCustomerName(contractModel.getList().get(i).getCustomerId());
                String assetName = assetModel.getAssetName(contractModel.getList().get(i).getContractId());
                String totalLoanAmount = Validation.convertLongFormat(contractModel.getList().get(i).getTotalLoanAmount());
                String startDate = contractModel.getList().get(i).getStartDate();
                String endDate = contractModel.getList().get(i).getEndDate();
                Date now = Validation.currentDate();
                Date stDate = dateFormat.parse(startDate);
                long datePawnToNow = Validation.getDifferenceDays(stDate, now);
                String interestToNow = Validation.convertLongFormat(datePawnToNow * contractModel.getList().get(i).getInterestRate());
                String totalMoney = Validation.convertLongFormat(contractModel.getList().get(i).getTotalMoney());

                tableDashboard.insertRow(tblDashboard.getRowCount(), new Object[]{
                    contractId,
                    customerName,
                    assetName,
                    totalLoanAmount,
                    endDate,
                    interestToNow,
                    totalMoney
                });
            }
        }
    }

    /**
     * init panel store
     */
    private void initpnlStore() {
        for (int i = 0; i < storeModel.getList().size(); i++) {
            lblStoreName.setText(storeModel.getList().get(i).getStoreName());
            lblAddress.setText(storeModel.getList().get(i).getsAddress());
            lblRepresentative.setText(storeModel.getList().get(i).getRepresentative());
            lblInvestmentCapital.setText(Validation.convertLongFormat(storeModel.getList().get(i).getInvestmentCapital()));
            lblCashFund.setText(Validation.convertLongFormat(storeModel.getList().get(i).getCashFund()));
            lblInterestCollected.setText(Validation.convertLongFormat(storeModel.getList().get(i).getInterestCollected()));
        }

    }

    /**
     * init panel customer
     */
    private void initpnlCustomer() throws Exception {
        this.customerModel = new CustomerModel();
        tableCustomer = (DefaultTableModel) tblCustomer.getModel();
        tableCustomer.setRowCount(0);

        for (int i = 0; i < customerModel.getList().size(); i++) {
            int customerId = customerModel.getList().get(i).getCustomerId();
            cbSelectCustomerId.addItem(Integer.toString(customerId));
            String customerName = customerModel.getList().get(i).getCustomerName();
            String phoneNumber = customerModel.getList().get(i).getPhoneNumber();
            String dateRange = customerModel.getList().get(i).getDateRange();
            String registeredPlace = customerModel.getList().get(i).getRegisteredPlace();
            String address = customerModel.getList().get(i).getAddress();
            String isEnable = "Disable";
            if (customerModel.getList().get(i).getIsActive() == 0) {
                isEnable = "Enable";
            }
            String haveImage = "No";
            if (customerModel.getList().get(i).getCustomerImage().equals("0")) {
                haveImage = "Yes";
            }
            tableCustomer.insertRow(tblCustomer.getRowCount(), new Object[]{
                customerId,
                customerName,
                phoneNumber,
                dateRange,
                registeredPlace,
                address,
                isEnable,
                haveImage
            });
        }

    }

    /**
     * init panel employee
     */
    private void initpnEmployee() throws Exception {
        this.employeeModel = new EmployeeModel();
        tableEmployee = (DefaultTableModel) tblEmployee.getModel();
        tableEmployee.setRowCount(0);
        boolean enableShowEmployee = false;
        for (int i = 0; i < employeeModel.getList().size(); i++) {
            if (employeeModel.getList().get(i).getUsername().equalsIgnoreCase(Login.userNameLogged)) {
                if (employeeModel.getList().get(i).geteRole().equalsIgnoreCase("admin")
                        || employeeModel.getList().get(i).geteRole().equalsIgnoreCase("owner")
                        || employeeModel.getList().get(i).geteRole().equalsIgnoreCase("mod")) {
                    enableShowEmployee = true;
                }
            }
        }
        if (enableShowEmployee == true) {
            btnNewtEmployee.setEnabled(true);
            cbSelectEmployee.setEnabled(true);
            btnEditEmployee.setEnabled(true);
            for (int i = 0; i < employeeModel.getList().size(); i++) {
                int empId = employeeModel.getList().get(i).getEmpId();
                cbSelectEmployee.addItem(Integer.toString(empId));
                String userName = employeeModel.getList().get(i).getUsername();
                String fullName = employeeModel.getList().get(i).getFullName();
                String phoneNumber = employeeModel.getList().get(i).getPhoneNumber();
                String email = employeeModel.getList().get(i).getEmail();
                String address = employeeModel.getList().get(i).geteAddress();
                String role = employeeModel.getList().get(i).geteRole();
                String isEnable = "Disable";
                if (employeeModel.getList().get(i).getIsActive() == 0) {
                    isEnable = "Enable";
                }
                tableEmployee.insertRow(tblEmployee.getRowCount(), new Object[]{
                    empId,
                    userName,
                    fullName,
                    phoneNumber,
                    email,
                    address,
                    role,
                    isEnable
                });
            }
        }
    }

//    public void setCashier(String userName) {
//        Home.cashier = userName;
//    }
//
//    public String getCashier() {
//        return cashier;
//    }
    /**
     * set status to display table in pawned off
     *
     * @param statusDisplay
     * @throws Exception
     */
    private void setStatusDisplay(int statusDisplay) throws Exception {
        this.statusDisplay = statusDisplay;
        initContract();
    }

    /**
     * init panel contract
     *
     * @throws Exception
     */
    public void initContract() throws Exception {
        this.contractModel = new ContractModel();
        this.customerModel = new CustomerModel();
        this.assetModel = new AssetModel();
        tableContract = (DefaultTableModel) tblContract.getModel();
        tableContract.setRowCount(0);
        cbContractId.removeAllItems();
        for (int i = 0; i < contractModel.getList().size(); i++) {
            if (contractModel.getList().get(i).getStatus() == statusDisplay) {
                int contractId = contractModel.getList().get(i).getContractId();
                if (statusDisplay == 0) {
                    cbContractId.addItem(Integer.toString(contractId));
                }
                String customerName = customerModel.getCustomerName(contractModel.getList().get(i).getCustomerId());
                String assetName = assetModel.getAssetName(contractModel.getList().get(i).getContractId());

                String totalLoanAmount = Validation.convertLongFormat(contractModel.getList().get(i).getTotalLoanAmount());
                String startDate = contractModel.getList().get(i).getStartDate();
                String endDate = contractModel.getList().get(i).getEndDate();
                Date now = Validation.currentDate();
                Date stDate = dateFormat.parse(startDate);
                long datePawnToNow = Validation.getDifferenceDays(stDate, now);

                String interestToNow = Validation.convertLongFormat(datePawnToNow * contractModel.getList().get(i).getInterestRate());
                String totalMoney = Validation.convertLongFormat(contractModel.getList().get(i).getTotalMoney());

                tableContract.insertRow(tblContract.getRowCount(), new Object[]{
                    contractId,
                    customerName,
                    assetName,
                    totalLoanAmount,
                    startDate,
                    endDate,
                    interestToNow,
                    totalMoney
                });
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

        pnlSplitPane = new javax.swing.JSplitPane();
        pnlLeft = new javax.swing.JPanel();
        btnEmployee = new javax.swing.JLabel();
        btnStore = new javax.swing.JLabel();
        btnCustomer = new javax.swing.JLabel();
        btnPawnedOff = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JLabel();
        backgroundLeft = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlCardDashboard = new javax.swing.JPanel();
        pnlScrollTableDashboard = new javax.swing.JScrollPane();
        tblDashboard = new javax.swing.JTable();
        lblTotalInterestEarn = new javax.swing.JLabel();
        lblMoneyInvestment = new javax.swing.JLabel();
        lblAmountOfContract = new javax.swing.JLabel();
        lblTotalCashFund = new javax.swing.JLabel();
        btnHowToUse = new javax.swing.JLabel();
        btnContact = new javax.swing.JLabel();
        btnAboutUs = new javax.swing.JLabel();
        bgDashboard = new javax.swing.JLabel();
        pnlCardPawnedOff = new javax.swing.JPanel();
        pnlScrollTableContract = new javax.swing.JScrollPane();
        tblContract = new javax.swing.JTable();
        lblContractId = new javax.swing.JLabel();
        btnRedeem = new javax.swing.JButton();
        btnNewContract = new javax.swing.JButton();
        cbContractId = new javax.swing.JComboBox<>();
        cbStatusDisplay = new javax.swing.JComboBox<>();
        btnExport = new javax.swing.JLabel();
        bgPawnedOff = new javax.swing.JLabel();
        pnlCardCustomer = new javax.swing.JPanel();
        btnEditCustomer = new javax.swing.JButton();
        cbSelectCustomerId = new javax.swing.JComboBox<>();
        pnlScrollTableCustomer = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        bgCustomer = new javax.swing.JLabel();
        pnlCardStore = new javax.swing.JPanel();
        btnChangeInfo = new javax.swing.JButton();
        lblInterestCollected = new javax.swing.JLabel();
        lblCashFund = new javax.swing.JLabel();
        lblInvestmentCapital = new javax.swing.JLabel();
        lblRepresentative = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblStoreName = new javax.swing.JLabel();
        bgStore = new javax.swing.JLabel();
        pnlCardEmployee = new javax.swing.JPanel();
        btnChangePassword = new javax.swing.JButton();
        btnNewtEmployee = new javax.swing.JButton();
        btnEditEmployee = new javax.swing.JButton();
        cbSelectEmployee = new javax.swing.JComboBox<>();
        pnlScrollTableCustomer1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        bgEmployee = new javax.swing.JLabel();
        bgCustomer1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pawnshoper");
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setName("home"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSplitPane.setDividerLocation(200);
        pnlSplitPane.setDividerSize(0);
        pnlSplitPane.setLastDividerLocation(200);
        pnlSplitPane.setMaximumSize(new java.awt.Dimension(1200, 700));
        pnlSplitPane.setMinimumSize(new java.awt.Dimension(1200, 700));
        pnlSplitPane.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnlLeft.setMaximumSize(new java.awt.Dimension(200, 700));
        pnlLeft.setMinimumSize(new java.awt.Dimension(200, 700));
        pnlLeft.setPreferredSize(new java.awt.Dimension(200, 700));
        pnlLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployee.png"))); // NOI18N
        btnEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmployee.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEmployeeMousePressed(evt);
            }
        });
        pnlLeft.add(btnEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStore.png"))); // NOI18N
        btnStore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStore.setPreferredSize(new java.awt.Dimension(200, 40));
        btnStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStoreMousePressed(evt);
            }
        });
        pnlLeft.add(btnStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomer.png"))); // NOI18N
        btnCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCustomer.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCustomerMousePressed(evt);
            }
        });
        pnlLeft.add(btnCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawn.png"))); // NOI18N
        btnPawnedOff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPawnedOff.setPreferredSize(new java.awt.Dimension(200, 40));
        btnPawnedOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPawnedOffMousePressed(evt);
            }
        });
        pnlLeft.add(btnPawnedOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboardHover.png"))); // NOI18N
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.setPreferredSize(new java.awt.Dimension(200, 40));
        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDashboardMousePressed(evt);
            }
        });
        pnlLeft.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        backgroundLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/panelLeft.png"))); // NOI18N
        pnlLeft.add(backgroundLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlSplitPane.setLeftComponent(pnlLeft);

        pnlRight.setMaximumSize(new java.awt.Dimension(1000, 700));
        pnlRight.setMinimumSize(new java.awt.Dimension(1000, 700));
        pnlRight.setName(""); // NOI18N
        pnlRight.setPreferredSize(new java.awt.Dimension(1000, 700));
        pnlRight.setLayout(new java.awt.CardLayout());

        pnlCardDashboard.setBackground(new java.awt.Color(153, 153, 255));
        pnlCardDashboard.setMaximumSize(new java.awt.Dimension(1000, 700));
        pnlCardDashboard.setMinimumSize(new java.awt.Dimension(1000, 700));
        pnlCardDashboard.setPreferredSize(new java.awt.Dimension(1000, 700));
        pnlCardDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlScrollTableDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlScrollTableDashboard.setBorder(null);
        pnlScrollTableDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlScrollTableDashboard.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlScrollTableDashboard.setMaximumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableDashboard.setMinimumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableDashboard.setPreferredSize(new java.awt.Dimension(970, 530));

        tblDashboard.setBackground(new java.awt.Color(255, 255, 255));
        tblDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contract ID", "Customer", "Asset", "Loan Amount", "End Date", "Interest to now", "Total Money"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDashboard.setToolTipText("");
        tblDashboard.setFocusable(false);
        tblDashboard.setGridColor(new java.awt.Color(255, 255, 255));
        tblDashboard.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblDashboard.setMaximumSize(new java.awt.Dimension(950, 530));
        tblDashboard.setMinimumSize(new java.awt.Dimension(950, 530));
        tblDashboard.setName(""); // NOI18N
        tblDashboard.setPreferredSize(new java.awt.Dimension(950, 530));
        tblDashboard.setRowHeight(25);
        tblDashboard.setSelectionBackground(new java.awt.Color(119, 91, 200));
        pnlScrollTableDashboard.setViewportView(tblDashboard);
        if (tblDashboard.getColumnModel().getColumnCount() > 0) {
            tblDashboard.getColumnModel().getColumn(0).setResizable(false);
            tblDashboard.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblDashboard.getColumnModel().getColumn(1).setResizable(false);
            tblDashboard.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblDashboard.getColumnModel().getColumn(2).setResizable(false);
            tblDashboard.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblDashboard.getColumnModel().getColumn(3).setResizable(false);
            tblDashboard.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblDashboard.getColumnModel().getColumn(4).setResizable(false);
            tblDashboard.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblDashboard.getColumnModel().getColumn(5).setResizable(false);
            tblDashboard.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblDashboard.getColumnModel().getColumn(6).setResizable(false);
            tblDashboard.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        pnlCardDashboard.add(pnlScrollTableDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 920, 270));

        lblTotalInterestEarn.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalInterestEarn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalInterestEarn.setForeground(new java.awt.Color(30, 30, 30));
        lblTotalInterestEarn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalInterestEarn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTotalInterestEarn.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlCardDashboard.add(lblTotalInterestEarn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 190, 40));

        lblMoneyInvestment.setBackground(new java.awt.Color(255, 255, 255));
        lblMoneyInvestment.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMoneyInvestment.setForeground(new java.awt.Color(30, 30, 30));
        lblMoneyInvestment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMoneyInvestment.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblMoneyInvestment.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlCardDashboard.add(lblMoneyInvestment, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 190, 40));

        lblAmountOfContract.setBackground(new java.awt.Color(255, 255, 255));
        lblAmountOfContract.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAmountOfContract.setForeground(new java.awt.Color(30, 30, 30));
        lblAmountOfContract.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAmountOfContract.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblAmountOfContract.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlCardDashboard.add(lblAmountOfContract, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 180, 40));

        lblTotalCashFund.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCashFund.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalCashFund.setForeground(new java.awt.Color(30, 30, 30));
        lblTotalCashFund.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalCashFund.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTotalCashFund.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlCardDashboard.add(lblTotalCashFund, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, 40));

        btnHowToUse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHowToUse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHowToUseMousePressed(evt);
            }
        });
        pnlCardDashboard.add(btnHowToUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 635, 120, 30));

        btnContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnContactMousePressed(evt);
            }
        });
        pnlCardDashboard.add(btnContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 635, 120, 30));

        btnAboutUs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAboutUs.setName("HomeDashboardFrame"); // NOI18N
        btnAboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAboutUsMousePressed(evt);
            }
        });
        pnlCardDashboard.add(btnAboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 635, 120, 30));

        bgDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/panelRight.png"))); // NOI18N
        bgDashboard.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        pnlCardDashboard.add(bgDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.add(pnlCardDashboard, "pnlCardDashboard");

        pnlCardPawnedOff.setBackground(new java.awt.Color(0, 255, 102));
        pnlCardPawnedOff.setMaximumSize(new java.awt.Dimension(1000, 700));
        pnlCardPawnedOff.setMinimumSize(new java.awt.Dimension(1000, 700));
        pnlCardPawnedOff.setPreferredSize(new java.awt.Dimension(1000, 700));
        pnlCardPawnedOff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlScrollTableContract.setBackground(new java.awt.Color(255, 255, 255));
        pnlScrollTableContract.setBorder(null);
        pnlScrollTableContract.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlScrollTableContract.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlScrollTableContract.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlScrollTableContract.setMaximumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableContract.setMinimumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableContract.setPreferredSize(new java.awt.Dimension(970, 530));

        tblContract.setBackground(new java.awt.Color(255, 255, 255));
        tblContract.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblContract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contract ID", "Customer", "Asset", "Loan Amount", "Start Date", "End Date", "Interest to now", "Total Money"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContract.setToolTipText("");
        tblContract.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblContract.setFocusable(false);
        tblContract.setGridColor(new java.awt.Color(255, 255, 255));
        tblContract.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblContract.setMaximumSize(new java.awt.Dimension(950, 530));
        tblContract.setMinimumSize(new java.awt.Dimension(950, 530));
        tblContract.setName(""); // NOI18N
        tblContract.setPreferredSize(new java.awt.Dimension(950, 530));
        tblContract.setRowHeight(25);
        tblContract.setSelectionBackground(new java.awt.Color(119, 91, 200));
        tblContract.getTableHeader().setResizingAllowed(false);
        tblContract.getTableHeader().setReorderingAllowed(false);
        pnlScrollTableContract.setViewportView(tblContract);
        if (tblContract.getColumnModel().getColumnCount() > 0) {
            tblContract.getColumnModel().getColumn(0).setResizable(false);
            tblContract.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblContract.getColumnModel().getColumn(1).setResizable(false);
            tblContract.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblContract.getColumnModel().getColumn(2).setResizable(false);
            tblContract.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblContract.getColumnModel().getColumn(3).setResizable(false);
            tblContract.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblContract.getColumnModel().getColumn(4).setResizable(false);
            tblContract.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblContract.getColumnModel().getColumn(5).setResizable(false);
            tblContract.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblContract.getColumnModel().getColumn(6).setResizable(false);
            tblContract.getColumnModel().getColumn(6).setPreferredWidth(70);
            tblContract.getColumnModel().getColumn(7).setResizable(false);
            tblContract.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        pnlCardPawnedOff.add(pnlScrollTableContract, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 970, 530));

        lblContractId.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblContractId.setText("Contract ID:");
        pnlCardPawnedOff.add(lblContractId, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

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
        pnlCardPawnedOff.add(btnRedeem, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        btnNewContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewContact.png"))); // NOI18N
        btnNewContract.setBorder(null);
        btnNewContract.setBorderPainted(false);
        btnNewContract.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewContract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewContractMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewContractMouseExited(evt);
            }
        });
        btnNewContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewContractActionPerformed(evt);
            }
        });
        pnlCardPawnedOff.add(btnNewContract, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        cbContractId.setBackground(new java.awt.Color(243, 242, 248));
        cbContractId.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbContractId.setBorder(null);
        cbContractId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContractIdActionPerformed(evt);
            }
        });
        pnlCardPawnedOff.add(cbContractId, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 90, 40));

        cbStatusDisplay.setBackground(new java.awt.Color(243, 242, 248));
        cbStatusDisplay.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbStatusDisplay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pawn", "Paid", "Expire" }));
        cbStatusDisplay.setBorder(null);
        cbStatusDisplay.setMaximumSize(new java.awt.Dimension(78, 32));
        cbStatusDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusDisplayActionPerformed(evt);
            }
        });
        pnlCardPawnedOff.add(cbStatusDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 62, 160, 40));

        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconExportToExcel.png"))); // NOI18N
        btnExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExportMousePressed(evt);
            }
        });
        pnlCardPawnedOff.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        bgPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pnlPawnedOff.png"))); // NOI18N
        bgPawnedOff.setToolTipText("");
        pnlCardPawnedOff.add(bgPawnedOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.add(pnlCardPawnedOff, "pnlCardPawnedOff");

        pnlCardCustomer.setBackground(new java.awt.Color(0, 0, 255));
        pnlCardCustomer.setMaximumSize(new java.awt.Dimension(1030, 700));
        pnlCardCustomer.setMinimumSize(new java.awt.Dimension(1030, 700));
        pnlCardCustomer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditCustomer.png"))); // NOI18N
        btnEditCustomer.setBorder(null);
        btnEditCustomer.setBorderPainted(false);
        btnEditCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditCustomerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditCustomerMouseExited(evt);
            }
        });
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });
        pnlCardCustomer.add(btnEditCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        cbSelectCustomerId.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        pnlCardCustomer.add(cbSelectCustomerId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 40));

        pnlScrollTableCustomer.setBackground(new java.awt.Color(255, 255, 255));
        pnlScrollTableCustomer.setBorder(null);
        pnlScrollTableCustomer.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlScrollTableCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlScrollTableCustomer.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlScrollTableCustomer.setMaximumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableCustomer.setMinimumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableCustomer.setPreferredSize(new java.awt.Dimension(970, 530));

        tblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        tblCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Id", "Customer Name", "Phone Number", "DateRange", "Registered Place", "Address", "Active", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.setToolTipText("");
        tblCustomer.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCustomer.setFocusable(false);
        tblCustomer.setGridColor(new java.awt.Color(255, 255, 255));
        tblCustomer.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblCustomer.setMaximumSize(new java.awt.Dimension(950, 530));
        tblCustomer.setMinimumSize(new java.awt.Dimension(950, 530));
        tblCustomer.setName(""); // NOI18N
        tblCustomer.setPreferredSize(new java.awt.Dimension(950, 530));
        tblCustomer.setRowHeight(25);
        tblCustomer.setSelectionBackground(new java.awt.Color(119, 91, 200));
        tblCustomer.getTableHeader().setResizingAllowed(false);
        tblCustomer.getTableHeader().setReorderingAllowed(false);
        pnlScrollTableCustomer.setViewportView(tblCustomer);
        if (tblCustomer.getColumnModel().getColumnCount() > 0) {
            tblCustomer.getColumnModel().getColumn(0).setResizable(false);
            tblCustomer.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblCustomer.getColumnModel().getColumn(1).setResizable(false);
            tblCustomer.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblCustomer.getColumnModel().getColumn(2).setResizable(false);
            tblCustomer.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblCustomer.getColumnModel().getColumn(3).setResizable(false);
            tblCustomer.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblCustomer.getColumnModel().getColumn(4).setResizable(false);
            tblCustomer.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblCustomer.getColumnModel().getColumn(5).setResizable(false);
            tblCustomer.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblCustomer.getColumnModel().getColumn(6).setResizable(false);
            tblCustomer.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblCustomer.getColumnModel().getColumn(7).setResizable(false);
            tblCustomer.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        pnlCardCustomer.add(pnlScrollTableCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 970, 530));

        bgCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/panelCustomer.png"))); // NOI18N
        pnlCardCustomer.add(bgCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.add(pnlCardCustomer, "pnlCardCustomer");

        pnlCardStore.setBackground(new java.awt.Color(51, 102, 255));
        pnlCardStore.setMaximumSize(new java.awt.Dimension(1000, 700));
        pnlCardStore.setMinimumSize(new java.awt.Dimension(1000, 700));
        pnlCardStore.setPreferredSize(new java.awt.Dimension(1000, 700));
        pnlCardStore.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnChangeInfo.setBackground(new java.awt.Color(255, 255, 255));
        btnChangeInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangeInfo.png"))); // NOI18N
        btnChangeInfo.setMnemonic('C');
        btnChangeInfo.setAlignmentY(0.0F);
        btnChangeInfo.setBorder(null);
        btnChangeInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangeInfo.setIconTextGap(0);
        btnChangeInfo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnChangeInfo.setMaximumSize(new java.awt.Dimension(170, 40));
        btnChangeInfo.setMinimumSize(new java.awt.Dimension(170, 40));
        btnChangeInfo.setPreferredSize(new java.awt.Dimension(170, 40));
        btnChangeInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChangeInfoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChangeInfoMouseExited(evt);
            }
        });
        btnChangeInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeInfoActionPerformed(evt);
            }
        });
        pnlCardStore.add(btnChangeInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 170, -1));

        lblInterestCollected.setBackground(new java.awt.Color(255, 255, 255));
        lblInterestCollected.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInterestCollected.setForeground(new java.awt.Color(30, 30, 30));
        lblInterestCollected.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblInterestCollected, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 420, 40));

        lblCashFund.setBackground(new java.awt.Color(255, 255, 255));
        lblCashFund.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCashFund.setForeground(new java.awt.Color(30, 30, 30));
        lblCashFund.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblCashFund, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 420, 40));

        lblInvestmentCapital.setBackground(new java.awt.Color(255, 255, 255));
        lblInvestmentCapital.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInvestmentCapital.setForeground(new java.awt.Color(30, 30, 30));
        lblInvestmentCapital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblInvestmentCapital, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 410, 40));

        lblRepresentative.setBackground(new java.awt.Color(255, 255, 255));
        lblRepresentative.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRepresentative.setForeground(new java.awt.Color(30, 30, 30));
        lblRepresentative.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblRepresentative, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 410, 40));

        lblAddress.setBackground(new java.awt.Color(255, 255, 255));
        lblAddress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(30, 30, 30));
        lblAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 410, 40));

        lblStoreName.setBackground(new java.awt.Color(255, 255, 255));
        lblStoreName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblStoreName.setForeground(new java.awt.Color(30, 30, 30));
        lblStoreName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlCardStore.add(lblStoreName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 410, 40));

        bgStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pnlStore.png"))); // NOI18N
        pnlCardStore.add(bgStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.add(pnlCardStore, "pnlCardStore");

        pnlCardEmployee.setBackground(new java.awt.Color(0, 204, 153));
        pnlCardEmployee.setMaximumSize(new java.awt.Dimension(1030, 700));
        pnlCardEmployee.setMinimumSize(new java.awt.Dimension(1030, 700));
        pnlCardEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnChangePassword.setBackground(new java.awt.Color(255, 255, 255));
        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangePassword.png"))); // NOI18N
        btnChangePassword.setBorder(null);
        btnChangePassword.setBorderPainted(false);
        btnChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChangePasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChangePasswordMouseExited(evt);
            }
        });
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });
        pnlCardEmployee.add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        btnNewtEmployee.setBackground(new java.awt.Color(255, 255, 255));
        btnNewtEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewEmployee.png"))); // NOI18N
        btnNewtEmployee.setBorder(null);
        btnNewtEmployee.setBorderPainted(false);
        btnNewtEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewtEmployee.setEnabled(false);
        btnNewtEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewtEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewtEmployeeMouseExited(evt);
            }
        });
        btnNewtEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewtEmployeeActionPerformed(evt);
            }
        });
        pnlCardEmployee.add(btnNewtEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        btnEditEmployee.setBackground(new java.awt.Color(255, 255, 255));
        btnEditEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditEmployee.png"))); // NOI18N
        btnEditEmployee.setBorder(null);
        btnEditEmployee.setBorderPainted(false);
        btnEditEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditEmployee.setEnabled(false);
        btnEditEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditEmployeeMouseExited(evt);
            }
        });
        btnEditEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditEmployeeActionPerformed(evt);
            }
        });
        pnlCardEmployee.add(btnEditEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        cbSelectEmployee.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbSelectEmployee.setBorder(null);
        cbSelectEmployee.setEnabled(false);
        pnlCardEmployee.add(cbSelectEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 40));

        pnlScrollTableCustomer1.setBackground(new java.awt.Color(255, 255, 255));
        pnlScrollTableCustomer1.setBorder(null);
        pnlScrollTableCustomer1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlScrollTableCustomer1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlScrollTableCustomer1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlScrollTableCustomer1.setMaximumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableCustomer1.setMinimumSize(new java.awt.Dimension(970, 530));
        pnlScrollTableCustomer1.setPreferredSize(new java.awt.Dimension(970, 530));

        tblEmployee.setBackground(new java.awt.Color(255, 255, 255));
        tblEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emp ID", "Username", "FullName", "Phone Number", "Email", "Address", "Role", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.setToolTipText("");
        tblEmployee.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblEmployee.setFocusable(false);
        tblEmployee.setGridColor(new java.awt.Color(255, 255, 255));
        tblEmployee.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblEmployee.setMaximumSize(new java.awt.Dimension(950, 530));
        tblEmployee.setMinimumSize(new java.awt.Dimension(950, 530));
        tblEmployee.setName(""); // NOI18N
        tblEmployee.setPreferredSize(new java.awt.Dimension(950, 530));
        tblEmployee.setRowHeight(25);
        tblEmployee.setSelectionBackground(new java.awt.Color(119, 91, 200));
        tblEmployee.getTableHeader().setResizingAllowed(false);
        tblEmployee.getTableHeader().setReorderingAllowed(false);
        pnlScrollTableCustomer1.setViewportView(tblEmployee);
        if (tblEmployee.getColumnModel().getColumnCount() > 0) {
            tblEmployee.getColumnModel().getColumn(0).setResizable(false);
            tblEmployee.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblEmployee.getColumnModel().getColumn(1).setResizable(false);
            tblEmployee.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblEmployee.getColumnModel().getColumn(2).setResizable(false);
            tblEmployee.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblEmployee.getColumnModel().getColumn(3).setResizable(false);
            tblEmployee.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblEmployee.getColumnModel().getColumn(4).setResizable(false);
            tblEmployee.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblEmployee.getColumnModel().getColumn(5).setResizable(false);
            tblEmployee.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblEmployee.getColumnModel().getColumn(6).setResizable(false);
            tblEmployee.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblEmployee.getColumnModel().getColumn(7).setResizable(false);
            tblEmployee.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        pnlCardEmployee.add(pnlScrollTableCustomer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 970, 530));

        bgEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/panelEmployee.png"))); // NOI18N
        pnlCardEmployee.add(bgEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bgCustomer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/panelCustomer.png"))); // NOI18N
        pnlCardEmployee.add(bgCustomer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlRight.add(pnlCardEmployee, "pnlCardEmployee");

        pnlSplitPane.setRightComponent(pnlRight);

        getContentPane().add(pnlSplitPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // PAWNED OFF
    private void btnDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashboardMousePressed
        cardLayout.show(pnlRight, "pnlCardDashboard");
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboardHover.png")));
        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawn.png")));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomer.png")));
        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStore.png")));
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployee.png")));
    }//GEN-LAST:event_btnDashboardMousePressed

    private void btnPawnedOffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPawnedOffMousePressed
        cardLayout.show(pnlRight, "pnlCardPawnedOff");
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboard.png")));
        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawnHover.png")));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomer.png")));
        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStore.png")));
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployee.png")));
    }//GEN-LAST:event_btnPawnedOffMousePressed

    private void btnCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustomerMousePressed
        cardLayout.show(pnlRight, "pnlCardCustomer");
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboard.png")));
        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawn.png")));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomerHover.png")));
        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStore.png")));
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployee.png")));
    }//GEN-LAST:event_btnCustomerMousePressed

    private void btnStoreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStoreMousePressed
        cardLayout.show(pnlRight, "pnlCardStore");
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboard.png")));
        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawn.png")));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomer.png")));
        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStoreHover.png")));
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployee.png")));
    }//GEN-LAST:event_btnStoreMousePressed

    private void btnEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmployeeMousePressed
        cardLayout.show(pnlRight, "pnlCardEmployee");
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconDashboard.png")));
        btnPawnedOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconPawn.png")));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconCustomer.png")));
        btnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconStore.png")));
        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEmployeeHover.png")));
    }//GEN-LAST:event_btnEmployeeMousePressed

    private void btnAboutUsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutUsMousePressed
        btnAboutUs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/about.png")));
        btnContact.setIcon(null);
        btnHowToUse.setIcon(null);
        new About(this);

    }//GEN-LAST:event_btnAboutUsMousePressed

    private void btnContactMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContactMousePressed
        btnAboutUs.setIcon(null);
        btnContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/contact.png")));
        btnHowToUse.setIcon(null);
        new Contact(this);
    }//GEN-LAST:event_btnContactMousePressed

    private void btnHowToUseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHowToUseMousePressed
        btnAboutUs.setIcon(null);
        btnContact.setIcon(null);
        btnHowToUse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/howtouse.png")));
        try {
            Desktop.getDesktop().browse(new URL("https://bit.ly/pawnshoper").toURI());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnHowToUseMousePressed

    private void btnExportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseEntered
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconExportToExcelHover.png")));
    }//GEN-LAST:event_btnExportMouseEntered

    private void btnExportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseExited
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconExportToExcel.png")));
    }//GEN-LAST:event_btnExportMouseExited

    private void btnNewContractMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewContractMouseEntered
        btnNewContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewContactHover.png")));
    }//GEN-LAST:event_btnNewContractMouseEntered

    private void btnNewContractMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewContractMouseExited
        btnNewContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewContact.png")));
    }//GEN-LAST:event_btnNewContractMouseExited

    private void btnNewContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewContractActionPerformed
        try {
            NewContract newContract = new NewContract();
            newContract.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNewContractActionPerformed

    private void cbStatusDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusDisplayActionPerformed
        try {
            setStatusDisplay(cbStatusDisplay.getSelectedIndex());
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbStatusDisplayActionPerformed

    private void btnRedeemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRedeemMouseEntered
        btnRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconRedeemHover.png")));
    }//GEN-LAST:event_btnRedeemMouseEntered

    private void btnRedeemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRedeemMouseExited
        btnRedeem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconRedeem.png")));
    }//GEN-LAST:event_btnRedeemMouseExited

    private void btnRedeemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedeemActionPerformed
        contractSelected = Integer.parseInt(cbContractId.getSelectedItem().toString());
        Redeem redeem = new Redeem();
        redeem.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRedeemActionPerformed

    private void btnExportMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMousePressed
        JOptionPane.showMessageDialog(null, "Function is developing...", "Sorry", 1);
    }//GEN-LAST:event_btnExportMousePressed

    private void btnChangeInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeInfoActionPerformed
        JOptionPane.showMessageDialog(null, "Function is developing...", "Sorry", 1);
    }//GEN-LAST:event_btnChangeInfoActionPerformed

    private void btnChangeInfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeInfoMouseEntered
        btnChangeInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangeInfoHover.png")));
    }//GEN-LAST:event_btnChangeInfoMouseEntered

    private void btnChangeInfoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangeInfoMouseExited
        btnChangeInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangeInfo.png")));
    }//GEN-LAST:event_btnChangeInfoMouseExited

    private void btnEditCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCustomerMouseEntered
        btnEditCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditCustomerHover.png")));
    }//GEN-LAST:event_btnEditCustomerMouseEntered

    private void btnEditCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCustomerMouseExited
        btnEditCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditCustomer.png")));
    }//GEN-LAST:event_btnEditCustomerMouseExited

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        customerSelected = Integer.parseInt(cbSelectCustomerId.getSelectedItem().toString());
        EditCustomer editPhoneNumber = new EditCustomer();
        editPhoneNumber.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnNewtEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewtEmployeeMouseEntered
        btnNewtEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewEmployeeHover.png")));
    }//GEN-LAST:event_btnNewtEmployeeMouseEntered

    private void btnNewtEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewtEmployeeMouseExited
        btnNewtEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconNewEmployee.png")));
    }//GEN-LAST:event_btnNewtEmployeeMouseExited

    private void btnNewtEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewtEmployeeActionPerformed

        NewEmployee newEmployee = new NewEmployee();
        newEmployee.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNewtEmployeeActionPerformed

    private void btnEditEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditEmployeeMouseEntered
        btnEditEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditEmployeeHover.png")));
    }//GEN-LAST:event_btnEditEmployeeMouseEntered

    private void btnEditEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditEmployeeMouseExited
        btnEditEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconEditEmployee.png")));
    }//GEN-LAST:event_btnEditEmployeeMouseExited

    private void btnEditEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditEmployeeActionPerformed
        empIdLoadToEditEmp = Integer.parseInt(cbSelectEmployee.getSelectedItem().toString());
        EditEmployee editEmployee = new EditEmployee();
        editEmployee.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditEmployeeActionPerformed

    private void btnChangePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangePasswordMouseEntered
        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangePasswordHover.png")));
    }//GEN-LAST:event_btnChangePasswordMouseEntered

    private void btnChangePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChangePasswordMouseExited
        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconChangePassword.png")));
    }//GEN-LAST:event_btnChangePasswordMouseExited

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        empIdLoadToEditEmp = Integer.parseInt(cbSelectEmployee.getSelectedItem().toString());
        ChangePassword changePassword = new ChangePassword();
        changePassword.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void cbContractIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContractIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbContractIdActionPerformed

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
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new Home().setVisible(true);
//                } catch (Exception ex) {
//                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ContractModel contractModel;
    private CustomerModel customerModel;
    private AssetModel assetModel;
    private StoreModel storeModel;
    private EmployeeModel employeeModel;
    private int statusDisplay = 0;
    public static int empIdLoadToEditEmp = 0;
    public static int contractSelected = 0;
    public static int customerSelected = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLeft;
    private javax.swing.JLabel bgCustomer;
    private javax.swing.JLabel bgCustomer1;
    private javax.swing.JLabel bgDashboard;
    private javax.swing.JLabel bgEmployee;
    private javax.swing.JLabel bgPawnedOff;
    private javax.swing.JLabel bgStore;
    private javax.swing.JLabel btnAboutUs;
    private javax.swing.JButton btnChangeInfo;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JLabel btnContact;
    private javax.swing.JLabel btnCustomer;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnEditEmployee;
    private javax.swing.JLabel btnEmployee;
    private javax.swing.JLabel btnExport;
    private javax.swing.JLabel btnHowToUse;
    private javax.swing.JButton btnNewContract;
    private javax.swing.JButton btnNewtEmployee;
    private javax.swing.JLabel btnPawnedOff;
    private javax.swing.JButton btnRedeem;
    private javax.swing.JLabel btnStore;
    private javax.swing.JComboBox<String> cbContractId;
    private javax.swing.JComboBox<String> cbSelectCustomerId;
    private javax.swing.JComboBox<String> cbSelectEmployee;
    private javax.swing.JComboBox<String> cbStatusDisplay;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAmountOfContract;
    private javax.swing.JLabel lblCashFund;
    private javax.swing.JLabel lblContractId;
    private javax.swing.JLabel lblInterestCollected;
    private javax.swing.JLabel lblInvestmentCapital;
    private javax.swing.JLabel lblMoneyInvestment;
    private javax.swing.JLabel lblRepresentative;
    private javax.swing.JLabel lblStoreName;
    private javax.swing.JLabel lblTotalCashFund;
    private javax.swing.JLabel lblTotalInterestEarn;
    private javax.swing.JPanel pnlCardCustomer;
    private javax.swing.JPanel pnlCardDashboard;
    private javax.swing.JPanel pnlCardEmployee;
    private javax.swing.JPanel pnlCardPawnedOff;
    private javax.swing.JPanel pnlCardStore;
    private javax.swing.JPanel pnlLeft;
    public static javax.swing.JPanel pnlRight;
    private javax.swing.JScrollPane pnlScrollTableContract;
    private javax.swing.JScrollPane pnlScrollTableCustomer;
    private javax.swing.JScrollPane pnlScrollTableCustomer1;
    private javax.swing.JScrollPane pnlScrollTableDashboard;
    private javax.swing.JSplitPane pnlSplitPane;
    private javax.swing.JTable tblContract;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblDashboard;
    private javax.swing.JTable tblEmployee;
    // End of variables declaration//GEN-END:variables

}
