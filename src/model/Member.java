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

    public List<Book> getLoans() {
        List<Book> books = new ArrayList<>();
        for (Loan loan : loans) {
            books.add(loan.getBook());
        }
        return books;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public Loan findLoanByBook(String title) {
        for (Loan loan : loans) {
            if (loan.getBook().getTitle().equals(title)) {
                return loan;
            }
        }
        return null;
    }
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }
}
