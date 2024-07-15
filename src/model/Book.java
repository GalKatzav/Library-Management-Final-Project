package model;

import DesingP.observer.Observable;
import DesingP.util.BookStateException;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Book} class represents a book in a library system.
 * It contains information about the book's title, author, publication year, quantity,
 * borrowed quantity, and loan history.
 * This class also provides methods for lending and returning copies of the book.
 */
public class Book extends Observable implements Cloneable {
    /** The title of the book. */
    private final String title;

    /** The author of the book. */
    private final String author;

    /** The publication year of the book. */
    private final int year;

    /** The total quantity of the book available in the library. */
    private int quantity;

    /** The quantity of the book that has been borrowed. */
    private int borrowedQuantity;

    /** The history of loans for this book. */
    private final List<Loan> loanHistory;


    /**
     * Constructs a new {@code Book} object with the specified title, author, year, and quantity.
     *
     * @param title     The title of the book.
     * @param author    The author of the book.
     * @param year      The publication year of the book.
     * @param quantity  The total quantity of the book available in the library.
     */
    public Book(String title, String author, int year, int quantity) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.borrowedQuantity = 0;
        this.loanHistory = new ArrayList<>();
    }

    /**
     * Returns the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the total quantity of the book available in the library.
     *
     * @return The total quantity of the book.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the total quantity of the book available in the library.
     *
     * @param quantity The new quantity of the book.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the quantity of the book that has been borrowed.
     *
     * @return The borrowed quantity of the book.
     */
    public int getBorrowedQuantity() {
        return borrowedQuantity;
    }

    /**
     * Returns the quantity of the book that is currently available for borrowing.
     *
     * @return The available quantity of the book.
     */
    public int getAvailableQuantity() {
        return quantity - borrowedQuantity;
    }

    /**
     * Lends a copy of the book, if available.
     *
     * @throws BookStateException If there are no available copies to lend.
     */
    public void lendCopy() throws BookStateException {
        // Checks if there are available copies to lend
        if (borrowedQuantity < quantity) {
            borrowedQuantity++; // Increases the borrowed quantity by 1
        } else {
            throw new BookStateException("No available copies of the book: " + title); // Throws an exception if no copies are available
        }
    }

    /**
     * Returns a borrowed copy of the book.
     *
     * @throws BookStateException If there are no borrowed copies to return.
     */
    public void returnCopy() throws BookStateException {
        // Checks if there are borrowed copies to return
        if (borrowedQuantity > 0) {
            borrowedQuantity--; // Decreases the borrowed quantity by 1
        } else {
            throw new BookStateException("No borrowed copies to return for the book: " + title); // Throws an exception if no copies are borrowed
        }
    }

    /**
     * Adds a loan to the loan history of the book.
     *
     * @param loan The loan to add.
     */
    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }

    /**
     * Removes a loan from the loan history of the book.
     *
     * @param loan The loan to remove.
     */
    public void removeLoan(Loan loan) {
        loanHistory.remove(loan);
    }

    /**
     * Returns the loan history of the book.
     *
     * @return The loan history of the book.
     */
    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    /**
     * Clones the book by creating a new instance with the same title, author, year,
     * quantity, borrowed quantity, and loan history.
     *
     * @return A cloned instance of the book.
     */
    @Override
    public Book clone() {
        try {
            // Attempts to clone the book
            return (Book) super.clone(); // Calls the clone method of the superclass
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    /**
     * Returns whether the book is available for borrowing.
     *
     * @return {@code true} if the book is available for borrowing, {@code false} otherwise.
     */
    public boolean isAvailable() {
        return getAvailableQuantity() > 0;
    }

    /**
     * Sets the availability status of the book.
     *
     * @param available {@code true} if the book should be available, {@code false} otherwise.
     */
    public void setAvailable(boolean available) {
        // Sets the quantity based on the availability status
        if (available) {
            quantity = Math.max(quantity, 1); // Ensures at least one copy is available
        } else {
            quantity = 0; // Sets the quantity to 0 if not available
        }
    }
}