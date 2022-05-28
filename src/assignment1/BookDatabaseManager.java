package assignment1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * this class will manage all engagments with the books database
 *
 * @author Dawson
 */
public class BookDatabaseManager {

    public static LibraryManager getLibraryManager(){
        //todo build a library up correctly with all authors and books and their relationships
        //todo add get all books and get all authors into LibraryManager class?? and loop through for each 51 mins mark may 20
        //fixme maybe
        return new LibraryManager(new BookDatabaseManager());
    }

//    public BookDatabaseManager() {
//        String test = BooksDatabaseSQL.BOOK_TABLE_NAME;
//
//    }

    /**
     * retrieve Book from the database using ISBN
     * @param isbn isbn
     * @return
     */
    public static Book getBookByISBN(String isbn){

        try(
                Connection connection = getConnection();
        ){
            String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME +
                    " where "+ BooksDatabaseSQL.BOOK_COL_NAME_ISBN +" = ?";          //The Query
            PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,isbn);
            System.out.println(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            //todo: check to make sure its one row?


//            //Execute the query and get the result set
            while(resultSet.next()){
                    return new Book(
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                            resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                    );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
    /**
     * retrireve all of the books from the database into the linkedlist
     * note: this method is dangerous if the database is large. But for this example it isnt.
     * @return list of Books
     */
    public static List<Book> getAllBooks(){
        LinkedList bookList = new LinkedList();

        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ){
            String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME;          //The Query
            //Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                bookList.add(
                        new Book(
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                            resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;

    }

    /**
     * retrive all of the authors from the database into the linkedlist
     * @return list of authors
     */
    public static List<Author> getAllAuthors(){
        LinkedList authorList = new LinkedList<>();
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from " + AuthorDatabaseSQL.AUTHORS_TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                authorList.add(
                        new Author(
                                resultSet.getInt(AuthorDatabaseSQL.AUTHOR_COL_NAME_ID),
                                resultSet.getString(AuthorDatabaseSQL.AUTHOR_COL_NAME_FIRSTNAME),
                                resultSet.getString(AuthorDatabaseSQL.AUTHOR_COL_NAME_LASTNAME)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorList;

    }

    /**
     * retrive all of the AuthorISBNs from the authorisbn database
     * @return authorISBN list
     */
    public List<AuthorISBN> getAllISBN(){
        List<AuthorISBN> authorISBNList = new LinkedList<>();
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from " + AuthorISBNDatabaseSQL.AUTHORSISBN_TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                authorISBNList.add(
                        new AuthorISBN(
                                resultSet.getInt(AuthorISBNDatabaseSQL.AUTHOR_COL_NAME_AUTHORID),
                                resultSet.getString(AuthorISBNDatabaseSQL.AUTHOR_COL_NAME_ISBN)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorISBNList;



    }


    /**
     * insert a Book into the database. Will return false if there is an issue
     * @param book book to insert
     * @return true if insert works, flase if otherise
     */
    public static boolean insertBook(Book book){
        try(Connection connection = getConnection();){
            String sqlQuery = "INSERT INTO " + BooksDatabaseSQL.BOOK_TABLE_NAME + " VALUES (?,?,?,?)";

            PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
            //these values are the books attributes
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());

//            System.out.println(preparedStatement.toString());

            preparedStatement.executeQuery();
            //todo how do we know it works?
            //todo what about authors

            //todo: check to make sure its one row?

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * insert a Author into the database. Will return false if there is an issue
     * @param author book to insert
     * @return true if insert works, false if otherwise
     *
     */
    public static boolean insertAuthor(Author author){
        try(
                Connection connection = getConnection();
        ){
            String sqlQuery = "INSERT INTO " + AuthorDatabaseSQL.AUTHORS_TABLE_NAME +
                    " VALUES (default, ?, ?)";
            PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
            //these values are the books attributes
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());

            preparedStatement.executeQuery();
            //todo how do we know it works?
            //todo what about authors

            //todo: check to make sure its one row?


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    /**
     * Get a connection to the Books Database - details in the inner class Books Database SQL
     * @return connection
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(BooksDatabaseSQL.DB_URL,
                BooksDatabaseSQL.USER,
                BooksDatabaseSQL.PASS);

    }

    /**
     * simple inner class to abstract all the specific SQL information
     */
    private class BooksDatabaseSQL{

        //login information
        public static final String DB_URL = "jdbc:mariadb://localhost:3306/books";
        public static final String USER = "root";
        public static final String PASS = "dawsonmercer";

        //book table information
        public static final String BOOK_TABLE_NAME = "titles";
        public static final String BOOK_COL_NAME_ISBN = "isbn";
        public static final String BOOK_COL_NAME_TITLE = "title";
        public static final String BOOK_COL_NAME_EDITION_NUMBER = "editionNumber";
        public static final String BOOK_COL_NAME_COPYRIGHT = "copyright";


    }

    private class AuthorDatabaseSQL{
        public static final String AUTHORS_TABLE_NAME = "authors";
        public static final String AUTHOR_COL_NAME_ID= "authorID";
        public static final String AUTHOR_COL_NAME_FIRSTNAME= "firstName";
        public static final String AUTHOR_COL_NAME_LASTNAME= "lastName";

    }

    private class AuthorISBNDatabaseSQL{
        public static final String AUTHORSISBN_TABLE_NAME= "authorisbn";
        public static final String AUTHOR_COL_NAME_AUTHORID= "authorID";
        public static final String AUTHOR_COL_NAME_ISBN= "isbn";

    }


}
