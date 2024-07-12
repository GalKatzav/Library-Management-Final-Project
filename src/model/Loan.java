package model;

import java.util.Date;

public class Loan {
    private Book book;
    private Date loanDate;
    private Date returnDate;

    public Loan(Book book) {
        this.book = book;
        this.loanDate = new Date();
        this.returnDate = null;
    }
    public Book getBook() {
        return book;
    }
    public Date getLoanDate() {
        return loanDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void returnBook() {
        this.returnDate = new Date();
        book.setAvailable(true);
    }
}
