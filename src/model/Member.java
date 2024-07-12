package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String id;
    private List<Loan> loans;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.loans = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public List<Loan> getLoans() {
        return loans;
    }
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            Loan loan = new Loan(book);
            loans.add(loan);
        } else {
            System.out.println("Book is not available");
        }
    }
    public void returnBook(Book book) {
        for (Loan loan : loans) {
            if (loan.getBook().equals(book) && loan.getReturnDate() == null) {
                loan.returnBook();
                break;
            }
        }
    }
}
