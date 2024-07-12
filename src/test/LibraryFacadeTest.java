package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.LibraryFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryFacadeTest {

    private LibraryFacade libraryFacade;

    @BeforeEach
    public void setUp() {
        libraryFacade = new LibraryFacade();
    }

    @Test
    public void testAddBook() {
        libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
        assertEquals(6, libraryFacade.getAllBooks().size()); // יש כבר 5 ספרים לדוגמה
    }

    @Test
    public void testRemoveBook() {
        libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
        libraryFacade.removeBook("The Catcher in the Rye");
        assertEquals(5, libraryFacade.getAllBooks().size());
    }

    @Test
    public void testAddMember() {
        libraryFacade.addMember("John Doe", "123");
        assertEquals(1, libraryFacade.getAllBooks().size());
    }

    @Test
    public void testRemoveMember() {
        libraryFacade.addMember("John Doe", "123");
        libraryFacade.removeMember("123");
        assertEquals(0, libraryFacade.getAllBooks().size());
    }

    @Test
    public void testLendBook() {
        libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
        libraryFacade.addMember("John Doe", "123");
        libraryFacade.lendBook("The Catcher in the Rye", "123");
        Book book = libraryFacade.getAllBooks().get(0);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
        libraryFacade.addMember("John Doe", "123");
        libraryFacade.lendBook("The Catcher in the Rye", "123");
        libraryFacade.returnBook("The Catcher in the Rye", "123");
        Book book = libraryFacade.getAllBooks().get(0);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testGetLibrarySummary() {
        String summary = libraryFacade.getLibrarySummary();
        assertTrue(summary.contains("Total Books: 5"));
    }
}

