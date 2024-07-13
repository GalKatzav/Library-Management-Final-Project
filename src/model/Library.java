package model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private int loanedBooksCount;
    private int totalLoansCount;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loanedBooksCount = 0;
        this.totalLoansCount = 0;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }


    public String getLibrarySummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Total Books: ").append(countTotalBooks()).append("\n");
        summary.append("Available Books: ").append(countAvailableBooks()).append("\n");
        summary.append("Loaned Books: ").append(loanedBooksCount).append("\n");
        summary.append("Total Members: ").append(members.size()).append("\n");
        summary.append("Total Loans: ").append(getTotalLoansCount()).append("\n");
        return summary.toString();
    }

    private int countTotalBooks() {
        int totalBooks = 0;
        for (Book book : books) {
            totalBooks += book.getQuantity();
        }
        return totalBooks;
    }

    private int countAvailableBooks() {
        int availableBooks = 0;
        for (Book book : books) {
            availableBooks += book.getQuantity();
        }
        return availableBooks;
    }

    public void incrementLoanedBooks() {
        loanedBooksCount++;
        totalLoansCount++;
    }

    public void decrementLoanedBooks() {
        loanedBooksCount--;
    }

    public int getTotalLoansCount() {
        return totalLoansCount;
    }
}
