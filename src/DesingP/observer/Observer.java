package DesingP.observer;

/**
 * The {@code Observer} interface should be implemented by any class that wants to receive
 * updates from an {@link Observable} object. It defines a single method, {@code update},
 * which is called when the observable object wants to notify its observers of a change.
 */
public interface Observer {

    /**
     * Called when the observable object wants to notify the observer of a change.
     *
     * @param message The message containing information about the update.
     */
    void update(String message);
}
