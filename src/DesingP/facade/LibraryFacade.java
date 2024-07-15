package DesingP.facade;

import DesingP.decorator.RatedBook;
import DesingP.factory.ConcreteLibraryFactory;
import DesingP.factory.LibraryFactory;
import DesingP.util.BookStateException;
import model.*;
import DesingP.singleton.SingletonLibrary;

import java.util.List;

/**
 * The {@code LibraryFacade} class provides a simplified interface to the complex library system.
 * It acts as a facade to the underlying {@link Library} and {@link Librarian} classes.
 */
public class LibraryFacade {

    /** The library instance. */
    private final Library library;

    /** The librarian instance. */
    private final Librarian librarian;

    /** The libraryFactory instance. */
    private static final LibraryFactory factory = new ConcreteLibraryFactory();

    /**
     * Constructs a new {@code LibraryFacade} object and initializes the library and librarian.
     * Also, adds sample books to the library.
     */
    public LibraryFacade() {
        this.library = SingletonLibrary.getInstance(); // Initializes the library field with the singleton library instance
        this.librarian = new Librarian(); // Initializes the librarian field
        addSampleBooks(); // Calls the method to add sample books to the library
    }

    /**
     * Adds a book to the library.
     *
     * @param title    The title of the book.
     * @param author   The author of the book.
     * @param year     The year of publication.
     * @param quantity The quantity of the book.
     */
    public void addBook(String title, String author, int year, int quantity) {
        Book book = factory.createBook(title, author, year, quantity); // Creates a new book using the factory
        librarian.addBook(book.getTitle(), book.getAuthor(), book.getYear(), book.getQuantity()); // Adds the book to the library through the librarian
    }

    /**
     * Removes a book from the library by its title.
     *
     * @param title The title of the book to remove.
     * @throws BookStateException If the book is not found.
     */
    public void removeBook(String title) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        if (book != null) { // If the book is found
            librarian.removeBook(title); // Removes the book from the library
        } else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        }
    }

    /**
     * Adds a member to the library.
     *
     * @param name The name of the member.
     * @param id   The ID of the member.
     * @throws BookStateException If the ID is already taken.
     */
    public void addMember(String name, String id) throws BookStateException {
        if (librarian.findMemberById(id) != null) { // Checks if the member ID is already taken
            throw new BookStateException("ID already taken: " + id); // Throws an exception if the ID is taken
        }
        Member member = factory.createMember(name, id); // Creates a new member using the factory
        librarian.addMember(member.getName(), member.getId()); // Adds the member to the library through the librarian
    }

    /**
     * Removes a member from the library by their ID.
     *
     * @param id The ID of the member to remove.
     * @throws BookStateException If the member is not found.
     */
    public void removeMember(String id) throws BookStateException {
        Member member = librarian.findMemberById(id); // Finds the member by ID using the librarian
        if (member != null) { // If the member is found
            librarian.removeMember(id); // Removes the member from the library
        } else {
            throw new BookStateException("Member not found: " + id); // Throws an exception if the member is not found
        }
    }

    /**
     * Lends a book to a member.
     *
     * @param title    The title of the book to lend.
     * @param memberId The ID of the member borrowing the book.
     * @return {@code true} if the book was successfully lent, {@code false} otherwise.
     * @throws BookStateException If the book or member is not found, or no copies are available.
     */
    public boolean lendBook(String title, String memberId) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        Member member = librarian.findMemberById(memberId); // Finds the member by ID using the librarian
        if (book != null && member != null) { // If both the book and member are found
            return librarian.lendBook(title, memberId); // Lends the book to the member
        } else if (book == null) { // If the book is not found
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        } else {
            throw new BookStateException("Member not found: " + memberId);// Throws an exception if the member is not found
        }
    }

    /**
     * Returns a book from a member.
     *
     * @param title    The title of the book to return.
     * @param memberId The ID of the member returning the book.
     * @throws BookStateException If the book or member is not found, or no copies were borrowed.
     */
    public void returnBook(String title, String memberId) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        Member member = librarian.findMemberById(memberId); // Finds the member by ID using the librarian
        if (book != null && member != null) { // If both the book and member are found
            librarian.returnBook(title, memberId); // Returns the book from the member
        } else if (book == null) { // If the book is not found
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        } else {
            throw new BookStateException("Member not found: " + memberId); // Throws an exception if the member is not found
        }
    }

    /**
     * Gets a summary of the library.
     *
     * @return A summary of the library.
     */
    public String getLibrarySummary() {
        return library.getLibrarySummary(); // Returns the summary of the library
    }

    /**
     * Gets all books in the library.
     *
     * @return A list of all books in the library.
     */
    public List<Book> getAllBooks() {
        return library.getBooks(); // Returns a list of all books in the library
    }

    /**
     * Adds sample books to the library.
     */
    private void addSampleBooks() {
        addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        addBook("To Kill a Mockingbird", "Harper Lee", 1960, 5);
        addBook("1984", "George Orwell", 1949, 5);
        addBook("Pride and Prejudice", "Jane Austen", 1813, 5);
        addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925, 5);
    }

    /**
     * Rates a book.
     *
     * @param title  The title of the book to rate.
     * @param rating The rating of the book.
     * @throws BookStateException If the book is not found.
     */
    public void rateBook(String title, double rating) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        if (book != null) { // If the book is found
            librarian.rateBook(title, rating); // Rates the book
        } else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        }
    }

    /**
     * Gets the rating of a book.
     *
     * @param title The title of the book.
     * @return The rating of the book, or 0.0 if the book is found but not rated, or -1 if the book is not found.
     * @throws BookStateException If the book is not found.
     */
    public double getBookRating(String title) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        if (book != null) { // If the book is found
            if (book instanceof RatedBook) { // Checks if the book is an instance of RatedBook
                return ((RatedBook) book).getRating(); // Returns the rating of the book
            } else {
                return 0.0; // Book found but not rated
            }
        } else {
            throw new BookStateException("Book not found: " + title); // Returns 0.0 if the book is not rated
        }
    }

    /**
     * Gets the list of books loaned by a member.
     *
     * @param userId The ID of the member.
     * @return A list of books loaned by the member.
     * @throws BookStateException If the member is not found.
     */
    public List<Book> getUserLoans(String userId) throws BookStateException{
        Member member = librarian.findMemberById(userId); // Finds the member by ID using the librarian
        if (member != null) { // If the member is found
            return librarian.getUserLoans(userId); // Returns the list of books loaned by the member
        } else {
            throw new BookStateException("Member not found: " + userId); // Throws an exception if the member is not found
        }
    }

    /**
     * Finds a book by its title.
     *
     * @param title The title of the book to find.
     * @return The book with the specified title.
     * @throws BookStateException If the book is not found.
     */
    public Book findBookByTitle(String title) throws BookStateException {
        for (Book book : library.getBooks()) { // Iterates through the list of books in the library
            if (book.getTitle().equals(title)) { // Checks if the title of the book matches the specified title
                return book; // Returns the book if the title matches
            }
        }
        throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
    }

    /**
     * Gets the total number of loans in the library.
     *
     * @return The total number of loans.
     */
    public int getTotalLoans() {
        return library.getTotalLoansCount(); // Returns the total number of loans in the library
    }

    /**
     * Updates the quantity of a book.
     *
     * @param title    The title of the book.
     * @param quantity The new quantity of the book.
     * @throws BookStateException If the quantity is out of range (0-20) or the book is not found.
     */
    public void updateBookQuantity(String title, int quantity) throws BookStateException {
        Book book = librarian.findBookByTitle(title); // Finds the book by title using the librarian
        if (book != null) { // If the book is found
            librarian.updateBookQuantity(title, quantity); // Updates the quantity of the book
        } else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        }
    }
}
