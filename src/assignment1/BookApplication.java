package assignment1;

import java.util.Scanner;


/**
 * Part 4: Create a Simple Application
 * 1.	Create a Java class called “BookApplication” with a main method that prompts the user to select any of the following options:
 *      1.	Print all the books from the database (showing the authors)
 *      2.	Print all the authors from the database (showing the books)
 *      3.	Add a book to the database for an existing author
 *      4.	Add a new author
 *      5.	Quit
 *
 * 2.	Note that the user should be able to continue making choices until they quit.
 */

public class BookApplication {

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        BookDatabaseManager bookDatabaseManager = new BookDatabaseManager();
        LibraryManager libraryManager = new LibraryManager(bookDatabaseManager);



        boolean keepUsing = true;
        while (keepUsing) {
            System.out.println();
            System.out.println("Please choose a number for the associated options:");
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
                    //fixme help, it skips past copyright does not allowed it to be entered
                    System.out.println("Please enter the copyright of the book: ");
                    String copyright = input.nextLine();
                    Book newBook = new Book(isbn,title, edition, copyright);
                    bookDatabaseManager.insertBook(newBook);
                    //db.AddAuthorToBook(isbn, authorid);
                    libraryManager.reloadFromDataSource();
                    break;
                case "4":
                    System.out.println("Please enter the author's first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter the author's last name");
                    String lastName = input.nextLine();
                    //no author id is passed here as it will be dealt with in insertAuthor()
                    Author newAuthor = new Author(firstName, lastName);
                    bookDatabaseManager.insertAuthor(newAuthor);
                    libraryManager.reloadFromDataSource();
                    break;
                case "5":
                    System.out.println("Thank you for using the database. Goodbye");
                    keepUsing = false;
                    break;


            }
        }
    }


}
