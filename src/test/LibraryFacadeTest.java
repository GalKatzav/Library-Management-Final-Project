package test;

import static org.junit.jupiter.api.Assertions.*;

import DesingP.util.BookStateException;
import model.Book;
import DesingP.facade.LibraryFacade;
import model.Library;
import DesingP.singleton.SingletonLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryFacadeTest {
    private LibraryFacade libraryFacade;
    private Library library;

    @BeforeEach
    public void setUp() {
        library = SingletonLibrary.getInstance();
        libraryFacade = new LibraryFacade();
        library.getBooks().clear();
        library.getMembers().clear();
    }

    @Test
    public void testAddBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
        assertEquals(1, library.getBooks().size());
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testRemoveBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
        try {
            libraryFacade.removeBook("Book Title 1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() {
        try {
            libraryFacade.addMember("Member 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() {
        try {
            libraryFacade.addMember("Member 1", "ID1");
            libraryFacade.removeMember("ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testLendBook() {
        try {
            libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
            libraryFacade.addMember("Member 1", "ID1");
            libraryFacade.lendBook("Book Title 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        Book book = library.getBooks().get(0);
        assertEquals(1, book.getBorrowedQuantity());
        assertEquals(4, book.getAvailableQuantity());
    }


    @Test
    public void testReturnBook() {
        try {
            libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
            libraryFacade.addMember("Member 1", "ID1");
            libraryFacade.lendBook("Book Title 1", "ID1");
            libraryFacade.returnBook("Book Title 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testLendBookWhenNoCopiesAvailable() {
        try {
            libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 1);
            libraryFacade.addMember("John Doe", "123");
            libraryFacade.addMember("Jane Doe", "456");

            libraryFacade.lendBook("The Catcher in the Rye", "123");
            boolean result = libraryFacade.lendBook("The Catcher in the Rye", "456");
            fail("Expected BookStateException not thrown");
        } catch (BookStateException e) {
            assertEquals("No available copies of the book: The Catcher in the Rye", e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateMember() {
        try {
            libraryFacade.addMember("Member 1", "ID1");
        } catch (BookStateException e) {
            fail("BookStateException thrown: " + e.getMessage());
        }
        assertThrows(BookStateException.class, () -> {
            libraryFacade.addMember("Member 2", "ID1");
        });
    }


}
