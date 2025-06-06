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

        ///1. configurare il drivermanger
        Class.forName(driver); ///com.mysql.cj.jdbc.Driver

        ///2. Crea la connessione tramite il driver manager e la restituisce
        return DriverManager.getConnection(url+dbName,userName,password);
    }

    public static void closeConnection(Connection c) throws SQLException {
        ///Chiudo la conessione
        c.close();
    }



    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {
        ///.1 creo la connessione
        Connection conn = getConnection();

        ///2. creo lo statement
        Statement statement = conn.createStatement();

        ///Eseguo la query fornita in input e ritorna il ResultSet
        return statement.executeQuery(query);
    }

    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        int ret = statement.executeUpdate(query);
        conn.close();
        return ret;
    }

    public static Integer updateQueryReturnGeneratedKey(String query) throws ClassNotFoundException, SQLException {
        Integer ret = null;

        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            ret = rs.getInt(1);
        }

        conn.close();

        return ret;
    }
}
