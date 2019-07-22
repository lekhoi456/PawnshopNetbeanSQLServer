package Model;

import Controller.ConnectMSSQL;
import Entity.Store;

import java.sql.*;
import java.util.ArrayList;

public class StoreModel {

    private static ArrayList<Store> storeArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    public StoreModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            storeArrayList = new ArrayList<>();
            pst = null;
            rs = null;
            sqlST = "";
            loadStore();
        } catch (Exception e) {
            throw new Exception("Cannot load initialize Store Model");
        }
    }

    private void loadStore() throws SQLException {
        sqlST = "SELECT * FROM Store";
        rs = st.executeQuery(sqlST);
        if (rs.isBeforeFirst()) {
            storeArrayList.clear();
            while (rs.next()) {
                storeArrayList.add(
                        new Store(rs.getInt("StoreId"),
                                rs.getString("StoreName"),
                                rs.getString("SAddress"),
                                rs.getString("Representative"),
                                rs.getLong("InvestmentCapital"),
                                rs.getLong("CashFund"),
                                rs.getLong("InterestCollected")));
            }
        }
    }

    public int getFreeId() {
        for (int i = 0; i < storeArrayList.size(); i++) {
            if (i > storeArrayList.get(i).getStoreId()) {
                return i;
            }
        }
        return storeArrayList.size() + 1;
    }

    public void addStore(int storeId, String storeName, String sAddress, String representative, long investmentCapital, long cashFund, long interestCollected) throws SQLException {
        try {
            sqlST = "INSERT INTO Store(StoreId, StoreName, SAddress, Representative, InvestmentCapital, CashFund, InterestCollected) VALUES (" + storeId + ", N'" + storeName + "' , N'" + sAddress + "', N'" + representative + "', " + investmentCapital + ", " + cashFund + ", " + interestCollected + ")";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            storeArrayList.add(new Store(storeId, storeName, sAddress, representative, investmentCapital, cashFund, interestCollected));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateStore(int storeId, String storeName, String sAddress, String representative, long investmentCapital, long cashFund, long interestCollected) throws SQLException {
        try {
            sqlST = "UPDATE Store SET StoreName = N'" + storeName + "', SAddress = N'" + sAddress + "', Representative = N'" + representative + "', InvestmentCapital = " + investmentCapital + ", CashFund = " + cashFund + ", InterestCollected = " + interestCollected + " WHERE StoreId = " + storeId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadStore();
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<Store> getList() {
        return storeArrayList;
    }
}
