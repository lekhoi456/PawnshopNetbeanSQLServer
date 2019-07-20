package Model;

import Controller.ConnectMSSQL;
import Entity.Contract;

import java.sql.*;
import java.util.ArrayList;

public class ContractModel {

    private static ArrayList<Contract> contractArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    public ContractModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            contractArrayList = new ArrayList<>();
            pst = null;
            rs = null;
            sqlST = "";
            loadContractFromDB();
        } catch (Exception e) {
            throw new Exception("Cannot load initialize Contract Model");
        }
    }

    private void loadContractFromDB() throws SQLException {
        sqlST = "SELECT * FROM Contract";
        rs = st.executeQuery(sqlST);
        if (rs.isBeforeFirst()) {
            contractArrayList.clear();
            while (rs.next()) {
                contractArrayList.add(
                        new Contract(rs.getInt("ContractId"),
                                rs.getInt("CustomerId"),
                                rs.getString("PropertyType"),
                                rs.getString("AssetName"),
                                rs.getLong("TotalLoanAmount"),
                                rs.getLong("InterestRate"),
                                rs.getString("StartDate"),
                                rs.getString("EndDate"),
                                rs.getLong("CreditPeriod"),
                                rs.getString("Note"),
                                rs.getString("Cashier"),
                                rs.getInt("Status"),
                                rs.getString("ContractImage"),
                                rs.getLong("TotalMoney"),
                                rs.getString("RedeemAtDay"),
                                rs.getInt("StoreId")));
            }
        }
    }

    public int getFreeContractId() {
        for (int i = 0; i < contractArrayList.size(); i++) {
            if (i > contractArrayList.get(i).getCustomerId()) {
                return i;
            }
        }
        return contractArrayList.size() + 1;
    }

    public void addContract(int contractId, int customerId, String propertyType, String assetName, long totalLoanAmount, long interestRate, String startDate, String endDate, long creditPeriod, String note, String cashier, int status, String contractImage, long totalMoney, String redeemAtDay, int storeId) throws SQLException {
        try {
            sqlST = "INSERT INTO Contract(ContractId, CustomerId, PropertyType, AssetName, TotalLoanAmount, InterestRate, StartDate, EndDate, CreditPeriod, Note, Cashier, Status, ContractImage, TotalMoney, RedeemAtDay, StoreId) VALUES ("
                    + contractId + ", " + customerId + ", N'" + propertyType + "', N'" + assetName + "', " + totalLoanAmount + ", " + interestRate + ", '" + startDate + "', '" + endDate + "', " + creditPeriod + ", N'" + note + "' , N'" + cashier + "', " + status + ", '" + contractImage + "', " + totalMoney + ", '" + redeemAtDay + "', " + storeId + ")";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            contractArrayList.add(new Contract(contractId, customerId, propertyType, assetName, totalLoanAmount, interestRate, startDate, endDate, creditPeriod, note, cashier, status, contractImage, totalMoney, redeemAtDay, storeId));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void redeemContract(int contractId, int status, long totalMoney, String redeemAtDay) throws SQLException {
        try {
            sqlST = "UPDATE Contract SET ContractId = " + contractId + ", Status = " + status + ", TotalMoney = " + totalMoney + ", RedeemAtDay = '" + redeemAtDay + "' WHERE ContractId=" + contractId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadContractFromDB();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int searchContractId(int keyword) {
        for (int i = 0; i < contractArrayList.size(); i++) {
            if (contractArrayList.get(i).getStoreId() == keyword) {
                return i;
            }
        }
        return -1;
    }

    public void printListContract() {
        for (Contract contract : contractArrayList) {
            System.out.println(contract.getContractId() + " | "
                    + contract.getCustomerId() + " | "
                    + contract.getPropertyType() + " | "
                    + contract.getAssetName() + " | "
                    + contract.getTotalLoanAmount() + " | "
                    + contract.getInterestRate() + " | "
                    + contract.getStartDate() + " | "
                    + contract.getEndDate() + " | "
                    + contract.getNote() + " | "
                    + contract.getCashier() + " | "
                    + contract.getStatus() + " | "
                    + contract.getContractImage() + " | "
                    + contract.getTotalMoney() + " | "
                    + contract.getRedeemAtDay() + " | "
                    + contract.getStoreId());
        }
    }

    public ArrayList<Contract> getList() {
        return contractArrayList;
    }
}
