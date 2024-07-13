package model;

import java.util.ArrayList;
import java.util.List;

public class Book implements Cloneable{
    private String title;
    private String author;
    private int year;
    private boolean available;
    private int quantity;
    private List<Loan> loanHistory;
    private int borrowedQuantity;

    public Book(String title, String author, int year, int quantity) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
        this.quantity = quantity;
        this.borrowedQuantity = 0;
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

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

}
