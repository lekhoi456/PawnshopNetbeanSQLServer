package Model;

import Utills.ConnectMSSQL;
import Entity.Store;
import Exception.StoreException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Store model
 *
 * @author KhoiLeQuoc
 */
public class StoreModel {

    private static ArrayList<Store> storeArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    /**
     * constructor
     *
     * @throws Exception
     */
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

    /**
     * load store from DB
     *
     * @throws SQLException
     */
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

    /**
     * get free store id
     *
     * @return
     */
    public int getFreeStoreId() {
        return storeArrayList.size() + 1;
    }

    /**
     * add store to DB
     *
     * @param storeId
     * @param storeName
     * @param sAddress
     * @param representative
     * @param investmentCapital
     * @param cashFund
     * @param interestCollected
     * @throws SQLException
     */
    public void addStore(int storeId, String storeName, String sAddress, String representative, long investmentCapital, long cashFund, long interestCollected) throws SQLException, StoreException {
        try {
            sqlST = "INSERT INTO Store(StoreId, StoreName, SAddress, Representative, InvestmentCapital, CashFund, InterestCollected) VALUES (" + storeId + ", N'" + storeName + "' , N'" + sAddress + "', N'" + representative + "', " + investmentCapital + ", " + cashFund + ", " + interestCollected + ")";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        try {
            storeArrayList.add(new Store(storeId, storeName, sAddress, representative, investmentCapital, cashFund, interestCollected));
        } catch (Exception e) {
            throw new StoreException("Can't add new store");
        }
    }

    /**
     * update store
     *
     * @param storeId
     * @param storeName
     * @param sAddress
     * @param representative
     * @param investmentCapital
     * @param cashFund
     * @param interestCollected
     * @throws SQLException
     */
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

    /**
     * get store array list
     *
     * @return
     */
    public ArrayList<Store> getList() {
        return storeArrayList;
    }
}
