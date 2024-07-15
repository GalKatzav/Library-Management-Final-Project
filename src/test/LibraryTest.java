package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Library;
import model.Book;
import model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Book Title", "Author", 2000, 5);
        library.addBook(book);
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testRemoveBook() {
        Book book = new Book("Book Title", "Author", 2000, 5);
        library.addBook(book);
        library.removeBook(book);
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testAddMember() {
        Member member = new Member("Member Name", "ID1");
        library.addMember(member);
        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void testRemoveMember() {
        Member member = new Member("Member Name", "ID1");
        library.addMember(member);
        library.removeMember(member);
        assertEquals(0, library.getMembers().size());
    }

    @Test
    public void testIncrementLoanedBooks() {
        library.incrementLoanedBooks();
        assertEquals(1, library.getLoanedBooksCount());
        assertEquals(1, library.getTotalLoansCount());
    }

    @Test
    public void testDecrementLoanedBooks() {
        library.incrementLoanedBooks();
        library.decrementLoanedBooks();
        assertEquals(0, library.getLoanedBooksCount());
        assertEquals(1, library.getTotalLoansCount()); // סך כל ההשאלות נשאר יציב
    }

    @Test
    public void testCountTotalBooks() {
        Book book1 = new Book("Book Title 1", "Author 1", 2000, 5);
        Book book2 = new Book("Book Title 2", "Author 2", 2005, 3);
        library.addBook(book1);
        library.addBook(book2);
        assertEquals(8, library.countTotalBooks());
    }

    @Test
    public void testCountAvailableBooks() {
        Book book1 = new Book("Book Title 1", "Author 1", 2000, 5);
        Book book2 = new Book("Book Title 2", "Author 2", 2005, 3);
        library.addBook(book1);
        library.addBook(book2);
        assertEquals(8, library.countAvailableBooks());
    }

    @Test
    public void testGetLibrarySummary() {
        Book book = new Book("Book Title", "Author", 2000, 5);
        Member member = new Member("Member Name", "ID1");
        library.addBook(book);
        library.addMember(member);
        library.incrementLoanedBooks();
        String expectedSummary = "Total Books: 5\n" +
                "Available Books: 5\n" +
                "Loaned Books: 1\n" +
                "Total Members: 1\n" +
                "Total Loans: 1\n";
        assertEquals(expectedSummary, library.getLibrarySummary());
    }
}
