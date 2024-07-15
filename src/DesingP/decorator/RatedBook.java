package DesingP.decorator;

import model.Book;

/**
 * The {@code RatedBook} class is a decorator that extends the functionality of the {@link Book} class
 * by adding a rating to the book.
 */
public class RatedBook extends BookDecorator{

    /** The rating of the book. */
    private double rating;

    /**
     * Constructs a new {@code RatedBook} object.
     *
     * @param decoratedBook The book instance to be decorated.
     * @param rating The rating of the book.
     */

    public RatedBook(Book decoratedBook, double rating) {
        super(decoratedBook); // Calls the constructor of the superclass BookDecorator
        this.rating = rating; // Initializes the rating field with the provided value
    }

    /**
     * Returns the rating of the book.
     *
     * @return The rating of the book.
     */
    public double getRating() {
        return rating; // Returns the value of the rating field
    }

    /**
     * Sets the rating of the book.
     *
     * @param rating The new rating of the book.
     */
    public void setRating(double rating) {
        this.rating = rating; // Updates the rating field with the provided value
    }

    /**
     * Returns the title of the decorated book along with its rating.
     *
     * @return The title of the book with its rating.
     */
    @Override
    public String getTitle() {
        // Returns the title of the decorated book, appending the rating to it
        return decoratedBook.getTitle() + " (Rated: " + rating + ")";
    }

    /**
     * Compares this rated book with another object for equality.
     * Two rated books are considered equal if their ratings and decorated books are equal.
     *
     * @param obj The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Checks if this object is the same as the compared object
        if (obj == null || getClass() != obj.getClass()) return false; // Checks if the compared object is null or of a different class
        RatedBook ratedBook = (RatedBook) obj; // Casts the compared object to RatedBook
        // Compares the ratings and the decorated books for equality
        return Double.compare(ratedBook.rating, rating) == 0 &&
                decoratedBook.equals(ratedBook.decoratedBook);
    }
}
