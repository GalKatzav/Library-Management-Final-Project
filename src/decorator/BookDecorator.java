package decorator;

import model.Book;


public class BookDecorator extends Book{
    protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        super(decoratedBook.getTitle(), decoratedBook.getAuthor(), decoratedBook.getYear());
        this.decoratedBook = decoratedBook;
    }
    @Override
    public String getTitle() {
        return decoratedBook.getTitle();
    }
    @Override
    public String getAuthor() {
        return decoratedBook.getAuthor();
    }
    @Override
    public int getYear() {
        return decoratedBook.getYear();
    }
}
