package utils;



import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDatabase {
    private final String URL = "jdbc:mysql://localhost:3307/tanit";
    private final String USER = "root";
    private final String PASSWORD = "";

    private static MyDatabase instance;

    private Connection connection;
    private MyDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public static MyDatabase getInstance() {
        if(instance == null)
            instance = new MyDatabase();
        return instance;
    }

    public Connection getConnection() {
        return (Connection) connection;
    }
}


