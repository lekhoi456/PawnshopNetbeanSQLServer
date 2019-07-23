package Model;

import Utills.ConnectMSSQL;
import Entity.Customer;
import Exception.CustomerException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Customer Model
 *
 * @author KhoiLeQuoc
 */
public class CustomerModel {

    private static ArrayList<Customer> customerArrayList = new ArrayList<>();
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
    public CustomerModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            customerArrayList = new ArrayList<>();
            pst = null;
            rs = null;
            sqlST = "";
            loadCustomer();
        } catch (Exception e) {
            throw new Exception("Cannot load initialize Customer Model");
        }
    }

    /**
     * load customer from DB
     *
     * @throws SQLException
     */
    private void loadCustomer() throws SQLException {
        sqlST = "SELECT * FROM Customer";
        rs = st.executeQuery(sqlST);
        if (rs.isBeforeFirst()) {
            customerArrayList.clear();
            while (rs.next()) {
                customerArrayList.add(
                        new Customer(rs.getInt("CustomerId"),
                                rs.getString("CustomerName"),
                                rs.getString("PhoneNumber"),
                                rs.getString("SocialId"),
                                rs.getString("DateRange"),
                                rs.getString("RegisteredPlace"),
                                rs.getString("Address"),
                                rs.getInt("IsActive"),
                                rs.getString("CustomerImage")));
            }
        }
    }

    /**
     * get free customer
     *
     * @return
     */
    public int getFreeId() {
        return customerArrayList.size() + 1;
    }

    /**
     * get customer name
     *
     * @param customerId
     * @return
     */
    public String getCustomerName(int customerId) {
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerId == customerArrayList.get(i).getCustomerId()) {
                return customerArrayList.get(i).getCustomerName();
            }
        }
        return null;
    }

    /**
     * add new customer to DB
     *
     * @param customerId
     * @param customerName
     * @param phoneNumber
     * @param socialId
     * @param dateRange
     * @param registeredPlace
     * @param address
     * @param isActive
     * @param customerImage
     * @throws SQLException
     * @throws Exception.CustomerException
     */
    public void addCustomer(int customerId, String customerName, String phoneNumber, String socialId, String dateRange, String registeredPlace, String address, int isActive, String customerImage) throws SQLException, CustomerException {
        try {
            sqlST = "INSERT INTO Customer(CustomerId, CustomerName, PhoneNumber, SocialId, DateRange, RegisteredPlace, Address, IsActive, CustomerImage) VALUES ("
                    + customerId + ", N'" + customerName + "', '" + phoneNumber + "', '" + socialId + "', '" + dateRange + "', N'" + registeredPlace + "', N'" + address + "', " + isActive + " , '" + customerImage + "')";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            customerArrayList.add(new Customer(customerId, customerName, phoneNumber, socialId, dateRange, registeredPlace, address, isActive, customerImage));
        } catch (Exception e) {
            throw new CustomerException("Can't add new customer to Arraylist");
        }
    }

    /**
     * update customer info
     *
     * @param customerId
     * @param phoneNumber
     * @param socialId
     * @param dateRange
     * @param registeredPlace
     * @param address
     * @throws SQLException
     */
    public void updateCustomerInfo(int customerId, String phoneNumber, String socialId, String dateRange, String registeredPlace, String address) throws SQLException {
        try {
            sqlST = "UPDATE Customer SET PhoneNumber = '" + phoneNumber + "', SocialId = '" + socialId + "', DateRange = '" + dateRange + "', RegisteredPlace = '" + registeredPlace + "', Address = N'" + address + "' WHERE CustomerId =" + customerId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update phone and number of customer
     *
     * @param customerId
     * @param phoneNumber
     * @param address
     * @param isActive
     * @param customerImage
     * @throws SQLException
     */
    public void updatePhoneAddress(int customerId, String phoneNumber, String address, int isActive, String customerImage) throws SQLException {
        try {
            sqlST = "UPDATE Customer SET PhoneNumber = '" + phoneNumber + "', Address = N'" + address + "', IsActive = " + isActive + ", CustomerImage = '" + customerImage + "' WHERE CustomerId =" + customerId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deactive account
     *
     * @param customerId
     * @param isActive
     */
    public void deactive(int customerId, int isActive) {
        try {
            sqlST = "UPDATE Customer SET IsActive = 0 WHERE CustomerId = " + customerId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get customer array list
     *
     * @return
     */
    public ArrayList<Customer> getList() {
        return customerArrayList;
    }
}
