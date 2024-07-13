package test;

import static org.junit.jupiter.api.Assertions.*;

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
        libraryFacade.removeBook("Book Title 1");
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() {
        libraryFacade.addMember("Member 1", "ID1");
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() {
        libraryFacade.addMember("Member 1", "ID1");
        libraryFacade.removeMember("ID1");
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testLendBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
        libraryFacade.addMember("Member 1", "ID1");
        libraryFacade.lendBook("Book Title 1", "ID1");
        Book book = library.getBooks().get(0);
        assertEquals(4, book.getQuantity());
    }

    @Test
    public void testReturnBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001, 5);
        libraryFacade.addMember("Member 1", "ID1");
        libraryFacade.lendBook("Book Title 1", "ID1");
        libraryFacade.returnBook("Book Title 1", "ID1");
        Book book = library.getBooks().get(0);
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testLendBookWhenNoCopiesAvailable() {
        libraryFacade.addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 1);
        libraryFacade.addMember("John Doe", "123");
        libraryFacade.addMember("Jane Doe", "456");
        libraryFacade.lendBook("The Catcher in the Rye", "123");
        assertFalse(libraryFacade.lendBook("The Catcher in the Rye", "456"));
    }

}
