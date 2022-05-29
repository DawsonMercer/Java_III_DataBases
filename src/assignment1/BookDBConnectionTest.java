package assignment1;
import java.sql.*;

/**
 * Books Database Connection Test - not relevent to assignment 1
 *
 * @author Dawson
 */
public class BookDBConnectionTest {
    /**
     * Get a simple result set from a Database and display it on scree.
     * <b>Note:</b> the database and data was created in advance.
     *
     */
    //Set up the Database Parameters
    static final String DB_URL = "jdbc:mariadb://localhost:3306/books";
    //TODO Customize to your setup
    static final String USER = "root";
    static final String PASS = "dawsonmercer";

    public static void main(String[] args) {
        // Open a connection
        try(
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();
        ){
            String sqlQuery = "SELECT * from titles";          //The Query

            //Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.print("ISBN \t\t\t Title");
            while (resultSet.next()) {
                System.out.printf("\n%s \t\t\t %s",
                        resultSet.getString("isbn"), resultSet.getString("title"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

