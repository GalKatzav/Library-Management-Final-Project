package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Book;
import model.Loan;
import model.Member;
import DesingP.util.BookStateException;
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
        Book book = new Book("Test Title", "Test Author", 2023, 1);
        book.setAvailable(false);
        assertFalse(book.isAvailable());
        book.setAvailable(true);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testAddLoan() {
        Book book = new Book("Test Title", "Test Author", 2023, 5);
        Loan loan = new Loan(book, new Member("John Doe", "123"));
        book.addLoan(loan);
        assertEquals(1, book.getLoanHistory().size());
    }

    @Test
    public void testRemoveLoan() {
        Book book = new Book("Test Title", "Test Author", 2023, 5);
        Loan loan = new Loan(book, new Member("John Doe", "123"));
        book.addLoan(loan);
        book.removeLoan(loan);
        assertEquals(0, book.getLoanHistory().size());
    }


    @Test
    public void testClone() {
        Book clonedBook = book.clone();
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getYear(), clonedBook.getYear());
    }
    @Test
    public void testLendCopy() throws BookStateException {
        Book book = new Book("Test Title", "Test Author", 2023, 5);
        book.lendCopy();
        assertEquals(4, book.getAvailableQuantity()); // שינוי לשימוש ב-getAvailableQuantity
    }

    @Test
    public void testReturnCopy() throws BookStateException {
        Book book = new Book("Test Title", "Test Author", 2023, 5);
        book.lendCopy();
        book.returnCopy();
        assertEquals(5, book.getAvailableQuantity()); // שינוי לשימוש ב-getAvailableQuantity
    }

}

