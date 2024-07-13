package model;

import DesingP.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class Book extends Observable implements Cloneable {
    private String title;
    private String author;
    private int year;
    private int quantity;
    private List<Loan> loanHistory;

    public Book(String title, String author, int year, int quantity) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.loanHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void lendCopy() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void returnCopy() {
        quantity++;
    }

    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }

    public void removeLoan(Loan loan) {
        loanHistory.remove(loan);
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void setAvailable(boolean available) {
        if (available) {
            quantity = Math.max(quantity, 1);
        } else {
            quantity = 0;
        }
    }
}