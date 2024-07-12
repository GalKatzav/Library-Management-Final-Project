package decorator;

import model.Book;

public class RatedBook extends BookDecorator{
    private double rating;

    public RatedBook(Book decoratedBook, double rating) {
        super(decoratedBook);
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

}
