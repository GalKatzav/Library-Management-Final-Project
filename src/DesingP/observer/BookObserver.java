package DesingP.observer;


/**
 * The {@code BookObserver} class implements the {@link Observer} interface.
 * It represents an observer that gets notified with messages about updates in the observed subject.
 */
public class BookObserver implements Observer {

    /** The name of the observer. */
    private final  String name;

    /**
     * Constructs a new {@code BookObserver} object with the specified name.
     *
     * @param name The name of the observer.
     */
    public BookObserver(String name) {
        this.name = name; // Initializes the name field with the provided value
    }

    /**
     * Receives an update with a message and prints a notification to the console.
     *
     * @param message The message containing the update information.
     */
    @Override
    public void update(String message) {
        // Prints a notification to the console with the observer's name and the received message
        System.out.println("Notification to " + name + ": " + message);
    }
}
