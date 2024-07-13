package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.Librarian;
import model.Library;
import DesingP.singleton.SingletonLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibrarianTest {
    private Librarian librarian;
    private Library library;

    @BeforeEach
    public void setUp() {
        library = SingletonLibrary.getInstance();
        librarian = new Librarian();
        library.getBooks().clear();
        library.getMembers().clear();
    }

    @Test
    public void testAddBook() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        assertEquals(1, library.getBooks().size());
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testLibrarianAddBook() {
        Librarian librarian = new Librarian();
        librarian.addBook("Test Title", "Test Author", 2023, 5);
        Library library = SingletonLibrary.getInstance();
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testLibrarianRemoveBook() {
        Librarian librarian = new Librarian();
        librarian.addBook("Test Title", "Test Author", 2023, 5);
        librarian.removeBook("Test Title");
        Library library = SingletonLibrary.getInstance();
        assertEquals(0, library.getBooks().size());
    }
    @Test
    public void testLibrarianLendBook() {
        Librarian librarian = new Librarian();
        librarian.addBook("Test Title", "Test Author", 2023, 5);
        librarian.addMember("John Doe", "123");
        boolean result = librarian.lendBook("Test Title", "123");
        assertTrue(result);
        Library library = SingletonLibrary.getInstance();
        assertEquals(4, library.getBooks().get(0).getQuantity());
    }


    @Test
    public void testRemoveBook() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        librarian.removeBook("The Catcher in the Rye");
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() {
        librarian.addMember("John Doe", "123");
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() {
        librarian.addMember("John Doe", "123");
        librarian.removeMember("123");
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testLendBook() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        librarian.addMember("John Doe", "123");
        librarian.lendBook("The Catcher in the Rye", "123");
        Book book = library.getBooks().get(0);
        assertEquals(4, book.getQuantity());
    }

    @Test
    public void testReturnBook() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        librarian.addMember("John Doe", "123");
        librarian.lendBook("The Catcher in the Rye", "123");
        librarian.returnBook("The Catcher in the Rye", "123");
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testLendBookWhenNoCopiesAvailable() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 1);
        librarian.addMember("John Doe", "123");
        librarian.addMember("Jane Doe", "456");
        librarian.lendBook("The Catcher in the Rye", "123");
        assertFalse(librarian.lendBook("The Catcher in the Rye", "456"));
    }

}
