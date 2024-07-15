package DesingP.decorator;

import DesingP.util.BookStateException;
import model.Book;
import model.Loan;

import java.util.List;

/**
 * The {@code BookDecorator} class is a base decorator class for enhancing the {@link Book} class.
 * It forwards all method calls to the decorated {@link Book} instance.
 */
public class BookDecorator extends Book {

    /** The decorated book instance. */
    protected Book decoratedBook;

    /**
     * Constructs a new {@code BookDecorator} object.
     *
     * @param decoratedBook The book instance to be decorated.
     */
    public BookDecorator(Book decoratedBook) {
        // Calls the constructor of the superclass Book with the details of the decorated book
        super(decoratedBook.getTitle(), decoratedBook.getAuthor(), decoratedBook.getYear(), decoratedBook.getQuantity());
        // Stores the decorated book instance in the decoratedBook field
        this.decoratedBook = decoratedBook;
    }

    /**
     * Returns the title of the decorated book.
     *
     * @return The title of the book.
     */
    @Override
    public String getTitle() {
        // Forwards the getTitle call to the decorated book instance
        return decoratedBook.getTitle();
    }

    /**
     * Returns the author of the decorated book.
     *
     * @return The author of the book.
     */
    @Override
    public String getAuthor() {
        // Forwards the getAuthor call to the decorated book instance
        return decoratedBook.getAuthor();
    }

    /**
     * Returns the year of publication of the decorated book.
     *
     * @return The year of publication.
     */
    @Override
    public int getYear() {
        // Forwards the getYear call to the decorated book instance
        return decoratedBook.getYear();
    }

    /**
     * Returns the quantity of the decorated book.
     *
     * @return The quantity of the book.
     */
    @Override
    public int getQuantity() {
        // Forwards the getQuantity call to the decorated book instance
        return decoratedBook.getQuantity();
    }

    /**
     * Sets the quantity of the decorated book.
     *
     * @param quantity The new quantity of the book.
     */
    @Override
    public void setQuantity(int quantity) {
        // Forwards the setQuantity call to the decorated book instance
        decoratedBook.setQuantity(quantity);
    }

    /**
     * Lends a copy of the decorated book.
     *
     * @throws BookStateException If no available copies are left.
     */
    @Override
    public void lendCopy() throws BookStateException {
        // Forwards the lendCopy call to the decorated book instance
        try {
            decoratedBook.lendCopy();
        } catch (BookStateException e) {
            // Throws the exception if the decorated book's lendCopy method throws it
            throw new BookStateException("Error lending book copy: " + e.getMessage());
        }
    }

    /**
     * Returns a copy of the decorated book.
     *
     * @throws BookStateException If no borrowed copies are available to return.
     */
    @Override
    public void returnCopy() throws BookStateException {
        // Forwards the returnCopy call to the decorated book instance
        try {
            decoratedBook.returnCopy();
        } catch (BookStateException e) {
            // Throws the exception if the decorated book's returnCopy method throws it
            throw new BookStateException("Error returning book copy: " + e.getMessage());
        }
    }

    /**
     * Adds a loan to the loan history of the decorated book.
     *
     * @param loan The loan to add.
     */
    @Override
    public void addLoan(Loan loan) {
        // Forwards the addLoan call to the decorated book instance
        decoratedBook.addLoan(loan);
    }

    /**
     * Removes a loan from the loan history of the decorated book.
     *
     * @param loan The loan to remove.
     */
    @Override
    public void removeLoan(Loan loan) {
        // Forwards the removeLoan call to the decorated book instance
        decoratedBook.removeLoan(loan);
    }

    /**
     * Returns the loan history of the decorated book.
     *
     * @return The loan history of the book.
     */
    @Override
    public List<Loan> getLoanHistory() {
        // Forwards the getLoanHistory call to the decorated book instance
        return decoratedBook.getLoanHistory();
    }

    /**
     * Checks if the decorated book is available for lending.
     *
     * @return {@code true} if the book is available, {@code false} otherwise.
     */
    @Override
    public boolean isAvailable() {
        // Forwards the isAvailable call to the decorated book instance
        return decoratedBook.isAvailable();
    }

    /**
     * Compares this book decorator with another object for equality.
     * Two book decorators are considered equal if their decorated books are equal.
     *
     * @param obj The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // Checks if this instance and the obj are the same
        if (this == obj) return true;
        // Checks if the obj is null or if they are not of the same class
        if (obj == null || getClass() != obj.getClass()) return false;
        // Casts obj to BookDecorator and compares the decorated books for equality
        BookDecorator that = (BookDecorator) obj;
        return decoratedBook.equals(that.decoratedBook);
    }
}
