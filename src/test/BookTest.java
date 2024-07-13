package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.Loan;
import model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
    }

    @Test
    public void testBookInitialization() {
        assertEquals("The Catcher in the Rye", book.getTitle());
        assertEquals("J.D. Salinger", book.getAuthor());
        assertEquals(1951, book.getYear());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testSetAvailable() {
        book.lendCopy();
        assertFalse(book.isAvailable());
        book.returnCopy();
        assertTrue(book.isAvailable());
    }

    @Test
    public void testAddLoan() {
        Loan loan = new Loan(book, new Member("John Doe", "123"));
        book.addLoan(loan);
        assertEquals(1, book.getLoanHistory().size());
    }

    @Test
    public void testClone() {
        Book clonedBook = book.clone();
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getYear(), clonedBook.getYear());
    }
}

