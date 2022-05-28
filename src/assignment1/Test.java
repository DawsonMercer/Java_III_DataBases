package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {
    static final String DB_URL = "jdbc:mariadb://localhost:3306";
    //TODO Customize to your setup
    static final String USER = "root";
    static final String PASS = "dawsonmercer";
    public static void main(String[] args) {
        // Open a connection
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
        ) {
            //If this Database already exists it will crash. You can "drop" it in the database view or use a drop query.
            String sql = "use books";
            String sql2 = "SELECT * from titles";
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }





}
