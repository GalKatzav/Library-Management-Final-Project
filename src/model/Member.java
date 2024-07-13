package model;

import DesingP.observer.Observable;
import DesingP.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Member extends Observable {
    private String name;
    private String id;
    private List<Loan> loans;
    private List<Observer> observers;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.loans = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    public Loan findLoanByBook(String title) {
        for (Loan loan : loans) {
            if (loan.getBook().getTitle().equals(title)) {
                return loan;
            }
        }
        return null;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}