package assignment1;

import java.util.*;
import java.io.PrintStream;

/**
 * simple class to store all of the Author information
 *
 * @author Dawson
 */
public class Author {
    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    /**
     * create an Author from the database with authorID, first name, last name
     * @param firstName first name
     * @param lastName last name
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }

    /**
     * create an author with first name and last name
     * @param firstName first name
     * @param lastName last name
     */
    public Author(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAuthorID() {
        return authorID;
    }

    /**
     * get Author's first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get Authors last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void printAuthorsInfo(PrintStream printStream){
        printStream.printf("\n\nAuthor ID: %d \t\t First Name: %-10s \t\t Last Name: %-10s",
                this.getAuthorID(),this.getFirstName(),this.getLastName());
        bookList.stream().forEach(book -> {
            System.out.printf("\nISBN: %-12s Title: %-60s Edition: %-5s", book.getIsbn(), book.getTitle(),
                    book.getEditionNumber(),book.getCopyright());
        });

    }
}
