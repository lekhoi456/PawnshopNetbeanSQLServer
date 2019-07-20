package Model;

import Controller.ConnectMSSQL;
import Entity.Contract;

import java.sql.*;
import java.util.ArrayList;

public class ContractModel {

    private static ArrayList<Contract> ContractArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    public ContractModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            ContractArrayList = new ArrayList<>();
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
            ContractArrayList.clear();
            while (rs.next()) {
                ContractArrayList.add(
                        new Contract(rs.getInt("ContractId"),
                                rs.getInt("CustomerId"),
                                rs.getString("PropertyType"),
                                rs.getString("AssetName"),
                                rs.getLong("TotalLoanAmount"),
                                rs.getLong("InterestRate"),
                                rs.getString("StartDate"),
                                rs.getString("EndDate"),
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

    private int getFreeIndex() {
        for (int i = 1; i < ContractArrayList.size(); i++) {
            if (i != ContractArrayList.get(i - 1).getContractId()) {
                return i;
            }
        }
        return ContractArrayList.size() + 1;
    }

    public void addContract(int contractId, int customerId, String propertyType, String assetName, long totalLoanAmount, long interestRate, String startDate, String endDate, String note, String cashier, int status, String contractImage, long totalMoney, String redeemAtDay, int storeId) throws SQLException {
        try {
            sqlST = "INSERT INTO Contract(ContractId, CustomerId, PropertyType, AssetName, TotalLoanAmount, InterestRate, StartDate, EndDate, Note, Cashier, Status, ContractImage, TotalMoney, RedeemAtDay, StoreId) VALUES ("
                    + +contractId + ", " + customerId + ", N'" + propertyType + "', N'" + assetName + "', " + totalLoanAmount + ", " + interestRate + ", '" + startDate + "', '" + endDate + "', N'" + note + "' , N'" + cashier + "', " + status + ", '" + contractImage + "', " + totalMoney + ", '" + redeemAtDay + "', " + storeId + ")";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            ContractArrayList.add(new Contract(contractId, customerId, propertyType, assetName, totalLoanAmount, interestRate, startDate, endDate, note, cashier, status, contractImage, totalMoney, redeemAtDay, storeId));
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
        for (int i = 0; i < ContractArrayList.size(); i++) {
            if (ContractArrayList.get(i).getStoreId() == keyword) {
                return i;
            }
        }
        return -1;
    }

    public void printListContract() {
        for (Contract contract : ContractArrayList) {
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
        return ContractArrayList;
    }
}
