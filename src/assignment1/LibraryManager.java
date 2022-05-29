package assignment1;

import java.util.*;


/**
 * Library Manager class with deals with the relationship between teh authorList and the bookList
 *
 * @author Dawson
 */
public class LibraryManager {

    private List<Author> authorList;
    private List<Book> bookList;
    private BookDatabaseManager bookDatabaseManager;

    /**
     * Library Manager constructor
     * @param databaseManager database manager
     */
    public LibraryManager(BookDatabaseManager databaseManager){
        //todo load the lists using the book database manager
        this.bookDatabaseManager = databaseManager;
        reloadFromDataSource();
    }

    /**
     * reload the data in the data source
     */
    public void reloadFromDataSource() {
        authorList = bookDatabaseManager.getAllAuthors();
        bookList = bookDatabaseManager.getAllBooks();
        List<AuthorISBN> authorISBNList = bookDatabaseManager.getAllISBN();

        for (Author author : authorList) {
            List<String> bookISBNList = new LinkedList<>();
            for (AuthorISBN authorISBN : authorISBNList) {
                if(authorISBN.authorID == author.getAuthorID()){
                    bookISBNList.add(authorISBN.isbn);
                }
            }
            for (Book b : bookList){
                if(bookISBNList.contains(b.getIsbn())){
                    author.getBookList().add(b);
                    b.getAuthorList().add(author);
                }
            }
        }
    }

    /**
     * get the author list
     * @return authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * get book list
     * @return book list
     */
    public List<Book> getBookList() {
        return bookList;
    }

}
