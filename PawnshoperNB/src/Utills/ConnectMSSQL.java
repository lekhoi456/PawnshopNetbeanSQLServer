package Utills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connect to SQL Server
 *
 * @author KhoiLeQuoc
 */
public class ConnectMSSQL {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=PAWNSHOP;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static Connection conn = null;

    /**
     * call to getConnection(String dbURL, String userName, String password)
     *
     * @return conn
     */
    public static Connection getConnection() {
        getConnection(DB_URL, USERNAME, PASSWORD);
        return conn;
    }

    /**
     * get connection in DB
     *
     * @param dbURL
     * @param userName
     * @param password
     * @return
     */
    public static Connection getConnection(String dbURL, String userName, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            // System.out.println("Connect to PAWNSHOP DB Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connect to PAWNSHOP DB Failure!");
            e.printStackTrace();
        }
        return conn;
    }
}
