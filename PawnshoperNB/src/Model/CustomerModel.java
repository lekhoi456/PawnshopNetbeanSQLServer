package Model;

import Controller.ConnectMSSQL;
import Entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerModel {

    private static ArrayList<Customer> customerArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

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

    public int getFreeId() {
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (i > customerArrayList.get(i).getCustomerId()) {
                return i;
            }
        }
        return customerArrayList.size() + 1;
    }

    public String getCustomerName(int customerId) {
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerId == customerArrayList.get(i).getCustomerId()) {
                return customerArrayList.get(i).getCustomerName();
            }
        }
        return null;
    }

    public void addCustomer(int customerId, String customerName, String phoneNumber, String socialId, String dateRange, String registeredPlace, String address, int isActive, String customerImage) throws SQLException {
        try {
            sqlST = "INSERT INTO Customer(CustomerId, CustomerName, PhoneNumber, SocialId, DateRange, RegisteredPlace, Address, IsActive, CustomerImage) VALUES ("
                    + customerId + ", N'" + customerName + "', '" + phoneNumber + "', '" + socialId + "', '" + dateRange + "', N'" + registeredPlace + "', N'" + address + "', " + isActive + " , '" + customerImage + "')";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            customerArrayList.add(new Customer(customerId, customerName, phoneNumber, socialId, dateRange, registeredPlace, address, isActive, customerImage));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public int searchCustomerId(int keyword) {
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerArrayList.get(i).getCustomerId() == keyword) {
                return i;
            }
        }
        return -1;
    }

    public void printListCustomer() {
        for (Customer customer : customerArrayList) {
            System.out.println(customer.getCustomerId() + "|"
                    + customer.getCustomerName() + " | "
                    + customer.getPhoneNumber() + " | "
                    + customer.getSocialId() + " | "
                    + customer.getDateRange() + " | "
                    + customer.getRegisteredPlace() + " | "
                    + customer.getAddress() + " | "
                    + customer.getIsActive() + " | "
                    + customer.getCustomerImage());
        }
    }

    public ArrayList<Customer> getList() {
        return customerArrayList;
    }
}
