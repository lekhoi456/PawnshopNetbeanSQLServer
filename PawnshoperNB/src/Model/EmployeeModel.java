package Model;

import Utills.ConnectMSSQL;
import Utills.HashPW;
import Entity.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeModel {

    private static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private static Connection conn;
    private static Statement st;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static String sqlST;

    public EmployeeModel() throws Exception {
        try {
            conn = ConnectMSSQL.getConnection();
            st = conn.createStatement();
            employeeArrayList = new ArrayList<>();
            pst = null;
            rs = null;
            sqlST = "";
            loadEmployee();
        } catch (Exception e) {
            throw new Exception("Cannot load initialize Employee Model");
        }
    }

    public void loadEmployee() throws SQLException {
        sqlST = "SELECT * FROM Employee";
        rs = st.executeQuery(sqlST);
        if (rs.isBeforeFirst()) {
            employeeArrayList.clear();
            while (rs.next()) {
                employeeArrayList.add(
                        new Employee(rs.getInt("EmpId"),
                                rs.getString("Username"),
                                rs.getString("EPassword"),
                                rs.getString("FullName"),
                                rs.getString("PhoneNumber"),
                                rs.getString("Email"),
                                rs.getString("EAddress"),
                                rs.getString("ERole"),
                                rs.getInt("IsActive"),
                                rs.getInt("StoreId")));
            }
        }
    }

    public int getFreeId() {
        return employeeArrayList.size() + 1;
    }

    public void addEmployee(int empId, String username, String ePassword, String fullName, String phoneNumber, String email, String eAddress, String eRole, int isActive, int storeId) throws SQLException {
        try {
            sqlST = "INSERT INTO Employee(EmpId, Username, EPassword, FullName, PhoneNumber, Email, EAddress, ERole, IsActive, StoreId) VALUES ("
                    + empId + ", '" + username + "', '" + ePassword + "', N'" + fullName + "', '" + phoneNumber + "', '" + email + "', N'" + eAddress + "', '" + eRole + "', " + isActive + ", " + storeId + ")";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            employeeArrayList.add(new Employee(empId, username, ePassword, fullName, phoneNumber, email, eAddress, eRole, isActive, storeId));
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateEmployeeInfo(int empId, String phoneNumber, String email, String address, String role, int isActive) throws SQLException {
        try {
            sqlST = "UPDATE Employee SET PhoneNumber= '" + phoneNumber + "', Email= '" + email + "', EAddress= N'" + address + "', ERole = '" + role + "', IsActive = " + isActive + " WHERE EmpId=" + empId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadEmployee();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void changePassword(String username, String password) throws SQLException {
        password = HashPW.encode(password);
        try {
            sqlST = "UPDATE Employee SET EPassword = '" + password + "' WHERE Username='" + username + "'";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadEmployee();
        } catch (SQLException e) {
            throw e;
        }
    }

    protected void changeRole(String username, String role) throws SQLException {
        try {
            sqlST = "UPDATE Employee SET ERole = '" + role + "' WHERE Username = '" + username + "'";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadEmployee();
        } catch (SQLException e) {
            throw e;
        }
    }

    protected void changeStatus(String username, boolean isActive) throws SQLException {
        try {
            sqlST = "UPDATE Employee SET IsActive = '" + isActive + "' WHERE Username = '" + username + "'";
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            loadEmployee();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteEmployee(int empId) throws SQLException {
        try {
            sqlST = "DELETE FROM Employee WHERE EmpId=" + empId;
            pst = conn.prepareStatement(sqlST);
            pst.executeUpdate();
            employeeArrayList.remove(searchEmployeeId(empId));
        } catch (SQLException e) {
            throw e;
        }
    }

    public int searchEmployeeId(int keyword) {
        for (int i = 0; i < employeeArrayList.size(); i++) {
            if (employeeArrayList.get(i).getStoreId() == keyword) {
                return i;
            }
        }
        return -1;
    }

    public void printListEmployee() {
        for (Employee employee : employeeArrayList) {
            System.out.println(employee.getEmpId() + "|"
                    + employee.getUsername() + "|"
                    + employee.getFullName() + "|"
                    + employee.getPhoneNumber() + "|"
                    + employee.getEmail() + "|"
                    + employee.geteRole() + "|"
                    + employee.getIsActive() + "|"
                    + employee.getStoreId());
        }
    }

    public ArrayList<Employee> getList() {
        return employeeArrayList;
    }
}
