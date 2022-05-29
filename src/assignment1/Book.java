package assignment1;

import java.io.PrintStream;
import java.util.List;
import java.util.LinkedList;

/**
 * simple class to stoire all book information. the informaiton will not change
 *
 * @author Dawson
 */
public class Book {
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright ;
    //todo getAllAuthorsForABook and then do the opposite for authors, get all books under the author
    //todo BookLibrary DONT load all of your books into a list and for a author i want to grab all the isbn adn then create a bunch of book objects
    //todo what you should do it use the list
    //todo explanation at 43 mins
    private List<Author> authorList = new LinkedList<>();


    /**
     *
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
//        this.authorList = new LinkedList<>();
    }


    /**
     * Create a book with a all the authors
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright, List<Author> authorList) {
        this(isbn, title, editionNumber, copyright);
        this.authorList = authorList;
    }

    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * get the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * get the edition number
     * @return edition nummber
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * get the copright
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * get the list of Authors
     * @return list of Authors
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * set the list of Authors
     * @param authorList list of Authors
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * print out the book informaiton
     * @param printStream
     */
    public void printBookInfo(PrintStream printStream){
        printStream.printf("\nISBN: %s \t\t Title: %-80s \t Edition #: %d \t\t Copyright: %s",
                this.getIsbn(), this.getTitle(), this.getEditionNumber(), this.getCopyright());
        authorList.stream().forEach(author -> {
            System.out.printf("\nFirst Name: %s \t\t Last Name: %s", author.getFirstName(), author.getLastName());
        });
        //todo add the authors - traverse the list
    }

}
