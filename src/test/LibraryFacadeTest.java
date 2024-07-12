package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

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
        libraryFacade.addBook("Book Title 1", "Author 1", 2001);
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testRemoveBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001);
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
        libraryFacade.addBook("Book Title 1", "Author 1", 2001);
        libraryFacade.addMember("Member 1", "ID1");
        libraryFacade.lendBook("Book Title 1", "ID1");
        Book book = library.getBooks().get(0);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        libraryFacade.addBook("Book Title 1", "Author 1", 2001);
        libraryFacade.addMember("Member 1", "ID1");
        libraryFacade.lendBook("Book Title 1", "ID1");
        libraryFacade.returnBook("Book Title 1", "ID1");
        Book book = library.getBooks().get(0);
        assertTrue(book.isAvailable());
    }
}


