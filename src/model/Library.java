package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Library} class represents a library containing a collection of books and members.
 * It provides methods to manage books and members, track loaned books, and summarize library information.
 */
public class Library {

    /** A list of books in the library. */
    private final List<Book> books;

    /** A list of members in the library. */
    private final List<Member> members;

    /** The count of books currently loaned out. */
    private int loanedBooksCount;

    /** The total count of loans made by the library. */
    private int totalLoansCount;

    /**
     * Constructs a new {@code Library} object with empty lists of books and members,
     * and initializes loan counts to zero.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loanedBooksCount = 0;
        this.totalLoansCount = 0;
    }

    /**
     * Returns the list of books in the library.
     *
     * @return The list of books.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Returns the list of members in the library.
     *
     * @return The list of members.
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * Adds a book to the library's collection.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the library's collection.
     *
     * @param book The book to be removed.
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Adds a member to the library.
     *
     * @param member The member to be added.
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Removes a member from the library.
     *
     * @param member The member to be removed.
     */
    public void removeMember(Member member) {
        members.remove(member);
    }

    /**
     * Returns a summary of the library's information, including total books, available books,
     * loaned books, total members, and total loans.
     *
     * @return A summary of the library's information.
     */
    public String getLibrarySummary() {
        StringBuilder summary = new StringBuilder(); // Creates a new StringBuilder to build the summary
        summary.append("Total Books: ").append(countTotalBooks()).append("\n");
        summary.append("Available Books: ").append(countAvailableBooks()).append("\n");
        summary.append("Loaned Books: ").append(loanedBooksCount).append("\n");
        summary.append("Total Members: ").append(members.size()).append("\n");
        summary.append("Total Loans: ").append(totalLoansCount).append("\n");
        return summary.toString(); // Returns the summary as a string
    }

    /**
     * Counts the total number of books in the library.
     *
     * @return The total number of books.
     */
    public int countTotalBooks() {
        int totalBooks = 0; // Initializes the total books count to 0
        for (Book book : books) {
            totalBooks += book.getQuantity(); // Adds the quantity of each book to the total books count
        }
        return totalBooks; // Returns the total books count
    }

    /**
     * Returns the count of books currently loaned out.
     *
     * @return The count of loaned books.
     */
    public int getLoanedBooksCount() {
        return loanedBooksCount;
    }

    /**
     * Counts the total number of available books in the library.
     *
     * @return The total number of available books.
     */
    public int countAvailableBooks() {
        int availableBooks = 0; // Initializes the available books count to 0
        for (Book book : books) {
            availableBooks += book.getAvailableQuantity(); // Adds the available quantity of each book to the available books count
        }
        return availableBooks; // Returns the available books count
    }

    /**
     * Increments the count of loaned books and total loans by one.
     */
    public void incrementLoanedBooks() {
        loanedBooksCount++; // Increments the loaned books count by 1
        totalLoansCount++; // Increments the total loans count by 1
    }

    /**
     * Decrements the count of loaned books by one.
     */
    public void decrementLoanedBooks() {
        loanedBooksCount--;
    }

    /**
     * Returns the total count of loans made by the library.
     *
     * @return The total count of loans.
     */
    public int getTotalLoansCount() {
        return totalLoansCount;
    }
}
