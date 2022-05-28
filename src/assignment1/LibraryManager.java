package assignment1;

import java.util.*;


public class LibraryManager {

    private List<Author> authorList;
    private List<Book> bookList;

    private BookDatabaseManager bookDatabaseManager;

    public LibraryManager(BookDatabaseManager databaseManager){
        //todo load the lists using the book database manager
        this.bookDatabaseManager = databaseManager;
        reloadFromDataSource();
    }

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


    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    //todo add a book to the list and to the underlying database(and its author)
    //todo add an author to the list and to the underlying database(and its book)
}
