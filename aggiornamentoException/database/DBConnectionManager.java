package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "taskdidattici";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "admin";

    private DBConnectionManager() {}

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(driver);

        return DriverManager.getConnection(url+dbName,userName,password);
    }

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }



    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        Statement statement = conn.createStatement();

        return statement.executeQuery(query);
    }

    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        int ret = statement.executeUpdate(query);
        closeConnection(conn);
        return ret;
    }

}
