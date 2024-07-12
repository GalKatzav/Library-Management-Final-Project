package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Library {
    private List<Book> books;
    private List<Member> members;

    protected Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
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
    public int countAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    public int countLoanedBooks() {
        return books.size() - countAvailableBooks();
    }
    public int countActiveMembers() {
        return members.size();
    }
    public int countTotalLoans() {
        int count = 0;
        for (Member member : members) {
            count += member.getLoans().size();
        }
        return count;
    }

    public void displayLibrarySummary() {
        System.out.println("Library Summary:");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + countAvailableBooks());
        System.out.println("Loaned Books: " + countLoanedBooks());
        System.out.println("Total Members: " + members.size());
        System.out.println("Total Loans: " + countTotalLoans());
    }
}
