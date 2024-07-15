package DesingP.singleton;

import model.Library;

/**
 * The {@code SingletonLibrary} class is a singleton implementation of the {@link Library} class.
 * This ensures that only one instance of the library exists throughout the application.
 * It provides a global point of access to the single instance of the library.
 */
public class SingletonLibrary extends Library {

    /** The single instance of the {@code SingletonLibrary}. */
    private static SingletonLibrary instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the {@code SingletonLibrary} instance.
     */
    private SingletonLibrary() {
        super(); // Calls the constructor of the superclass Library
    }

    /**
     * Returns the single instance of the {@code SingletonLibrary}.
     * If the instance does not exist, it creates a new one.
     *
     * @return The single instance of the {@code SingletonLibrary}.
     */
    public static SingletonLibrary getInstance() {
        // Checks if the instance is null and creates a new one if necessary
        if (instance == null) {
            instance = new SingletonLibrary(); // Initializes the instance
        }
        return instance; // Returns the single instance of SingletonLibrary
    }

    /**
     * Resets the single instance of the {@code SingletonLibrary}.
     * This method is mainly used for testing purposes.
     */
    public static void resetInstance() {
        instance = null; // Sets the instance to null, effectively resetting the singleton
    }
}
