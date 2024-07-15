package DesingP.util;

/**
 * The {@code BookStateException} class represents an exception that is thrown when there is
 * an invalid state or operation related to a book in the library system. This exception
 * extends the {@link Exception} class and provides a message describing the error.
 */
public class BookStateException extends Exception{

    /**
     * Constructs a new {@code BookStateException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public BookStateException(String message) {
        super(message); // Calls the constructor of the superclass Exception with the message
    }
}
