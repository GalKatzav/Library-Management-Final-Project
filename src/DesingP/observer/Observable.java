package DesingP.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Observable} class is an abstract class that provides the basic functionality
 * for managing and notifying observers. It is designed to be extended by other classes
 * that need to notify observers about changes in their state.
 */
public abstract class Observable {

    /** A list of observers that are monitoring this observable object. */
    private List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers by sending them a message.
     *
     * @param message The message to be sent to all observers.
     */
    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
