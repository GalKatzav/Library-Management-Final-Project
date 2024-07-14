package DesingP.decorator;

import DesingP.util.BookStateException;
import model.Book;
import model.Loan;

import java.util.List;

public class BookDecorator extends Book {
    protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        super(decoratedBook.getTitle(), decoratedBook.getAuthor(), decoratedBook.getYear(), decoratedBook.getQuantity());
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

    @Override
    public int getQuantity() {
        return decoratedBook.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        decoratedBook.setQuantity(quantity);
    }

    @Override
    public void lendCopy() throws BookStateException {
        decoratedBook.lendCopy();
    }

    @Override
    public void returnCopy() {
        decoratedBook.returnCopy();
    }

    @Override
    public void addLoan(Loan loan) {
        decoratedBook.addLoan(loan);
    }

    @Override
    public void removeLoan(Loan loan) {
        decoratedBook.removeLoan(loan);
    }

    @Override
    public List<Loan> getLoanHistory() {
        return decoratedBook.getLoanHistory();
    }

    @Override
    public boolean isAvailable() {
        return decoratedBook.isAvailable();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookDecorator that = (BookDecorator) obj;
        return decoratedBook.equals(that.decoratedBook);
    }
}
