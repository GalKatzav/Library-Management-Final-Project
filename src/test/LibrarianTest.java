package test;

import static org.junit.jupiter.api.Assertions.*;

import DesingP.util.BookStateException;
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
    public void testAddBook() throws BookStateException {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        assertEquals(1, library.getBooks().size());
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testLibrarianAddBook() throws BookStateException {
        Librarian librarian = new Librarian();
        librarian.addBook("Test Title", "Test Author", 2023, 5);
        Library library = SingletonLibrary.getInstance();
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testLibrarianRemoveBook() throws BookStateException {
        Librarian librarian = new Librarian();
        librarian.addBook("Test Title", "Test Author", 2023, 5);
        librarian.removeBook("Test Title");
        Library library = SingletonLibrary.getInstance();
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testLibrarianLendBook() {
        Librarian librarian = new Librarian();
        try {
            librarian.addBook("Test Title", "Test Author", 2023, 5);
            librarian.addMember("John Doe", "123");
            boolean result = librarian.lendBook("Test Title", "123");
            assertTrue(result);
            Library library = SingletonLibrary.getInstance();
            Book book = library.getBooks().get(0);
            assertEquals(1, book.getBorrowedQuantity());
            assertEquals(4, book.getAvailableQuantity());
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
    }


    @Test
    public void testRemoveBook() throws BookStateException {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        librarian.removeBook("The Catcher in the Rye");
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() throws BookStateException {
        librarian.addMember("Member 1", "ID1");
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() throws BookStateException {
        librarian.addMember("Member 1", "ID1");
        librarian.removeMember("ID1");
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testLendBook() throws BookStateException {
        librarian.addBook("Book Title 1", "Author 1", 2001, 5);
        librarian.addMember("John Doe", "123");
        librarian.lendBook("Book Title 1", "123");
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
        assertEquals(1, book.getBorrowedQuantity());

    }

    @Test
    public void testReturnBook() throws BookStateException {
        librarian.addBook("Book Title 1", "Author 1", 2001, 5);
        librarian.addMember("John Doe", "123");
        librarian.lendBook("Book Title 1", "123");
        librarian.returnBook("Book Title 1", "123");
        Book book = library.getBooks().get(0);
        assertEquals(0, book.getBorrowedQuantity());
    }

    @Test
    public void testLendBookWhenNoCopiesAvailable() {
        try {
            librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 1);
            librarian.addMember("John Doe", "123");
            librarian.addMember("Jane Doe", "456");

            librarian.lendBook("The Catcher in the Rye", "123");
            boolean result = librarian.lendBook("The Catcher in the Rye", "456");
            fail("Expected BookStateException not thrown");
        } catch (BookStateException e) {
            assertEquals("No available copies of the book: The Catcher in the Rye", e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateMember() {
        try {
            librarian.addMember("Member 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertThrows(BookStateException.class, () -> {
            librarian.addMember("Member 2", "ID1");
        });
    }
}
