package assignment1;

/**
 * simple class AuthorISBn to store authorID and isbn
 *
 * @author Dawson
 */
public class AuthorISBN {
    public int authorID;
    public String isbn;

    /**
     * constructor for the AuthorISBN class
     * @param authorID
     * @param isbn
     */
    public AuthorISBN(int authorID, String isbn){
        this.authorID = authorID;
        this.isbn = isbn;
    }
}
