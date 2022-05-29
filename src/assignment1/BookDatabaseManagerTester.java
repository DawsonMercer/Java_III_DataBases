package assignment1;

import java.util.LinkedList;
import java.util.List;

/**
 * Database tester class that was user to test along the way. not relevant for the assignmetn
 *
 * @author Dawson
 */
public class BookDatabaseManagerTester {
    public static void main(String[] args) {
        System.out.println("Testing the book Database manager");

//        List<Book> bookList = BookDatabaseManager.getAllBooks();
//        bookList.forEach( book -> book.printBookInfo(System.out));


        //insert a book test
        BookDatabaseManager.insertBook(new Book("123456799", "InsertED Book - DawsON Books", 6, "2001"));
        //get book by isbn test and print
        BookDatabaseManager.getBookByISBN("123456799").printBookInfo(System.out);

        BookDatabaseManager.insertAuthor(new Author("Dawson", "Mercer"));

    }
}
