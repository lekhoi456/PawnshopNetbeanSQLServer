package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMSSQL {

    private static String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=PAWNSHOP;";
    private static String USERNAME = "sa";
    private static String PASSWORD = "123456";
    private static Connection conn = null;

    public static Connection getConnection() {
        getConnection(DB_URL, USERNAME, PASSWORD);
        return conn;
    }

    public static Connection getConnection(String dbURL, String userName, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            // System.out.println("Connect to PAWNSHOP DB Successfully!");
        } catch (Exception e) {
            System.out.println("Connect to PAWNSHOP DB Failure!");
            e.printStackTrace();
        }
        return conn;
    }
}
