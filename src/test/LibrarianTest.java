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
        try {
            librarian.removeBook("Test Title");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
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
            assertEquals(4, library.getBooks().get(0).getQuantity());
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveBook() {
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        try {
            librarian.removeBook("The Catcher in the Rye");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() {
        try {
            librarian.addMember("Member 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() {
        try {
            librarian.addMember("Member 1", "ID1");
            librarian.removeMember("ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testLendBook() {
        try {
            librarian.addBook("Book Title 1", "Author 1", 2001, 5);
            librarian.addMember("John Doe", "123");
            librarian.lendBook("Book Title 1", "123");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        Book book = library.getBooks().get(0);
        assertEquals(4, book.getQuantity());
    }

    @Test
    public void testReturnBook() {
        try {
            librarian.addBook("Book Title 1", "Author 1", 2001, 5);
            librarian.addMember("John Doe", "123");
            librarian.lendBook("Book Title 1", "123");
            librarian.returnBook("Book Title 1", "123");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
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
