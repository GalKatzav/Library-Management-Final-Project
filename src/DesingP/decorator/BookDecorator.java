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
        super(decoratedBook.getTitle(), decoratedBook.getAuthor(), decoratedBook.getYear(), decoratedBook.getQuantity());
        this.decoratedBook = decoratedBook;
    }

    /**
     * Returns the title of the decorated book.
     *
     * @return The title of the book.
     */
    @Override
    public String getTitle() {
        return decoratedBook.getTitle();
    }

    /**
     * Returns the author of the decorated book.
     *
     * @return The author of the book.
     */
    @Override
    public String getAuthor() {
        return decoratedBook.getAuthor();
    }

    /**
     * Returns the year of publication of the decorated book.
     *
     * @return The year of publication.
     */
    @Override
    public int getYear() {
        return decoratedBook.getYear();
    }

    /**
     * Returns the quantity of the decorated book.
     *
     * @return The quantity of the book.
     */
    @Override
    public int getQuantity() {
        return decoratedBook.getQuantity();
    }

    /**
     * Sets the quantity of the decorated book.
     *
     * @param quantity The new quantity of the book.
     */
    @Override
    public void setQuantity(int quantity) {
        decoratedBook.setQuantity(quantity);
    }

    /**
     * Lends a copy of the decorated book.
     *
     * @throws BookStateException If no available copies are left.
     */
    @Override
    public void lendCopy() throws BookStateException {
        decoratedBook.lendCopy();
    }

    /**
     * Returns a copy of the decorated book.
     *
     * @throws BookStateException If no borrowed copies are available to return.
     */
    @Override
    public void returnCopy() throws BookStateException {
        decoratedBook.returnCopy();
    }

    /**
     * Adds a loan to the loan history of the decorated book.
     *
     * @param loan The loan to add.
     */
    @Override
    public void addLoan(Loan loan) {
        decoratedBook.addLoan(loan);
    }

    /**
     * Removes a loan from the loan history of the decorated book.
     *
     * @param loan The loan to remove.
     */
    @Override
    public void removeLoan(Loan loan) {
        decoratedBook.removeLoan(loan);
    }

    /**
     * Returns the loan history of the decorated book.
     *
     * @return The loan history of the book.
     */
    @Override
    public List<Loan> getLoanHistory() {
        return decoratedBook.getLoanHistory();
    }

    /**
     * Checks if the decorated book is available for lending.
     *
     * @return {@code true} if the book is available, {@code false} otherwise.
     */
    @Override
    public boolean isAvailable() {
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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookDecorator that = (BookDecorator) obj;
        return decoratedBook.equals(that.decoratedBook);
    }
}
