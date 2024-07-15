package model;

import DesingP.observer.Observable;
import DesingP.observer.Observer;

import java.util.ArrayList;
import java.util.List;


/**
 * The {@code Member} class represents a member of the library.
 * It contains information about the member's name, ID, and loans.
 * The class also supports observer pattern to notify observers of changes.
 */
public class Member extends Observable {

    /** The name of the member. */
    private final String name;

    /** The ID of the member. */
    private final String id;

    /** The list of loans that the member has. */
    private final List<Loan> loans;

    /** The list of observers observing this member. */
    private final List<Observer> observers;

    /**
     * Constructs a new {@code Member} object with the specified name and ID.
     *
     * @param name The name of the member.
     * @param id The ID of the member.
     */
    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.loans = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Returns the name of the member.
     *
     * @return The name of the member.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the member.
     *
     * @return The ID of the member.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the list of loans that the member has.
     *
     * @return The list of loans.
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * Adds a loan to the member's list of loans.
     *
     * @param loan The loan to add.
     */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * Removes a loan from the member's list of loans.
     *
     * @param loan The loan to remove.
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /**
     * Finds a loan by the book's title.
     *
     * @param title The title of the book.
     * @return The loan corresponding to the book title, or {@code null} if not found.
     */
    public Loan findLoanByBook(String title) {
        for (Loan loan : loans) {
            if (loan.getBook().getTitle().equals(title)) {
                return loan;
            }
        }
        return null;
    }

    /**
     * Adds an observer to the member.
     *
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the member.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers with a given message.
     *
     * @param message The message to send to observers.
     */
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}