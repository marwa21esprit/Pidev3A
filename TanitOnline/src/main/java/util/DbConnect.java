package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnect {

    private final String URL = "jdbc:mysql://localhost:3307/tanit";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static DbConnect instance;

    private DbConnect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static DbConnect getInstance() {
        if(instance == null)
            instance = new DbConnect();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
