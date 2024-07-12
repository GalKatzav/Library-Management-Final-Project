package model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean available;
    private List<Loan> loanHistory;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
        this.loanHistory = new ArrayList<>();
    }

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
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }

    public Book clone() {
        return new Book(this.title, this.author, this.year);
    }
}
