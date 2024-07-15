package DesingP.factory;

import model.Book;
import model.Member;


/**
 * The {@code LibraryFactory} class is an abstract factory for creating {@link Book} and {@link Member} objects.
 * Subclasses of {@code LibraryFactory} will implement the methods to create specific types of books and members.
 */
public abstract class LibraryFactory {

    /**
     * Creates a new {@link Book} object with the specified title, author, publication year, and quantity.
     *
     * @param title    The title of the book.
     * @param author   The author of the book.
     * @param year     The publication year of the book.
     * @param quantity The quantity of the book available.
     * @return A new {@link Book} object with the specified details.
     */
    public abstract Book createBook(String title, String author, int year, int quantity);

    /**
     * Creates a new {@link Member} object with the specified name and ID.
     *
     * @param name The name of the member.
     * @param id   The ID of the member.
     * @return A new {@link Member} object with the specified details.
     */
    public abstract Member createMember(String name, String id);
}
