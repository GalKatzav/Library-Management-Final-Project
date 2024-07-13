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
    @Override
    public String getTitle() {
        return decoratedBook.getTitle() + " (Rated: " + rating + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RatedBook ratedBook = (RatedBook) obj;
        return Double.compare(ratedBook.rating, rating) == 0 &&
                decoratedBook.equals(ratedBook.decoratedBook);
    }
}
