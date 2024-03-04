package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    final String URL ="jdbc:mysql://localhost:3306/pijava";
    final String USER ="root";
    final String PWD ="";

    private static MyDB instance;
    private Connection cnx;

    public  Connection getCnx()
    {
        return cnx;
    }
    public MyDB() {
        try {
            cnx = DriverManager.getConnection(URL,USER,PWD);
        } catch (SQLException e) {
            System.out.println((e.getMessage()));
        }
    }

    public static MyDB getInstance(){
        if (instance== null){
            instance = new MyDB();
        }return  instance;
    }
}
