package assignment1;
import java.util.Scanner;

/**
 * BookApplication class that deals with the user input menu
 *
 * @author Dawson
 */
public class BookApplication {

    /**
     * main method, prompts user to continue to make selections until they wish to quit
     * @param args args
     */
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        BookDatabaseManager bookDatabaseManager = new BookDatabaseManager();
        LibraryManager libraryManager = new LibraryManager(bookDatabaseManager);

        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println();
            System.out.println("Select an options (1-5):");
            System.out.println("1. Print all the books from the database (showing the authors)");
            System.out.println("2. Print all the authors from the database (showing the books)");
            System.out.println("3. Add a book to the database for an existing author");
            System.out.println("4. Add a new author");
            System.out.println("5. Quit");
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    for (Book book : libraryManager.getBookList()) {
                        book.printBookInfo(System.out);
                    }
                    System.out.println();
                    break;
                case "2":
                    for (Author author : libraryManager.getAuthorList()) {
                        author.printAuthorsInfo(System.out);
                    }
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Please enter the books isbn: ");
                    String isbn = input.nextLine();
                    System.out.println("Please enter the title of the book: ");
                    String title = input.nextLine();
                    System.out.println("Please enter the edition of the book: ");
                    int edition = input.nextInt();
                    System.out.println("Please enter the copyright of the book: ");
                    String copyright = input.next();
                    Book newBook = new Book(isbn,title, edition, copyright);
                    System.out.println("How many authors wrote this book?");
                    int num = input.nextInt();
                    for (int i=0; i< num; i++){
                        System.out.println("\nEnter the authorID:");
                        int id = input.nextInt();
                        Author author =new Author(id,"","");
                        newBook.getAuthorList().add(author);
                    }

                    bookDatabaseManager.insertBook(newBook);
                    libraryManager.reloadFromDataSource();
                    break;
                case "4":
                    System.out.println("Please enter the author's first name:");
                    String firstName1 = input.next();
                    System.out.println("Please enter the author's last name");
                    String lastName1 = input.next();
                    //no author id is passed here as it will be dealt with in insertAuthor()
                    Author newAuthor = new Author(firstName1, lastName1);
                    bookDatabaseManager.insertAuthor(newAuthor);
                    libraryManager.reloadFromDataSource();
                    break;
                case "5":
                    System.out.println("Bye!");
                    keepGoing = false;
                    break;


            }
        }
    }


}
