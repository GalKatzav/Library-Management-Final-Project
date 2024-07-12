package model;

import java.util.ArrayList;
import java.util.List;

public class Book implements Cloneable{
    private String title;
    private String author;
    private int year;
    private boolean available;
    private List<Observer> observers;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
        this.observers = new ArrayList<>();
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
        notifyObservers();
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported for Book", e);
        }
    }
}
