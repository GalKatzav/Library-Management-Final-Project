package DesingP.factory;

import model.Book;
import model.Member;


/**
 * The {@code ConcreteLibraryFactory} class is a concrete implementation of the {@link LibraryFactory} abstract class.
 * It provides methods to create instances of {@link Book} and {@link Member}.
 */
public class ConcreteLibraryFactory extends LibraryFactory {

    /**
     * Creates a new {@link Book} instance with the specified title, author, year, and quantity.
     *
     * @param title    The title of the book.
     * @param author   The author of the book.
     * @param year     The year of publication.
     * @param quantity The quantity of the book.
     * @return A new {@link Book} instance.
     */
    public Book createBook(String title, String author, int year, int quantity) {
        // Creates and returns a new Book instance with the given title, author, year, and quantity
        return new Book(title, author, year, quantity);
    }

    /**
     * Creates a new {@link Member} instance with the specified name and ID.
     *
     * @param name The name of the member.
     * @param id   The ID of the member.
     * @return A new {@link Member} instance.
     */
    public Member createMember(String name, String id) {
        // Creates and returns a new Member instance with the given name and ID
        return new Member(name, id);
    }
}
