package model;

import java.util.Date;

/**
 * The {@code Loan} class represents a loan of a book to a member in the library.
 * It contains information about the book, the member, the loan date, and the return date.
 */
public class Loan {

    /** The book that is being loaned. */
    private Book book;

    /** The member to whom the book is loaned. */
    private Member member;

    /** The date when the book was loaned. */
    private Date loanDate;

    /** The date when the book was returned. */
    private Date returnDate;

    /**
     * Constructs a new {@code Loan} object with the specified book and member.
     * The loan date is set to the current date.
     *
     * @param book The book that is being loaned.
     * @param member The member to whom the book is loaned.
     */
    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = new Date();
    }

    /**
     * Returns the book that is being loaned.
     *
     * @return The book that is being loaned.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Returns the member to whom the book is loaned.
     *
     * @return The member to whom the book is loaned.
     */
    public Member getMember() {
        return member;
    }

    /**
     * Returns the date when the book was loaned.
     *
     * @return The loan date.
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * Returns the date when the book was returned.
     *
     * @return The return date, or {@code null} if the book has not been returned yet.
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date when the book was returned.
     *
     * @param returnDate The return date to set.
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
