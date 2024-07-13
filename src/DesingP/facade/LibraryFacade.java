package DesingP.facade;

import DesingP.decorator.RatedBook;
import DesingP.util.BookStateException;
import model.*;
import DesingP.singleton.SingletonLibrary;

import java.util.List;

public class LibraryFacade {
    private Library library;
    private Librarian librarian;

    public LibraryFacade() {
        this.library = SingletonLibrary.getInstance();
        this.librarian = new Librarian();
        addSampleBooks();
    }

    public void addBook(String title, String author, int year, int quantity) {
        librarian.addBook(title, author, year, quantity);
    }

    public void removeBook(String title) throws BookStateException {
        librarian.removeBook(title);
    }

    public void addMember(String name, String id) {
        librarian.addMember(name, id);
    }

    public void removeMember(String id) throws BookStateException {
        librarian.removeMember(id);
    }

    public boolean lendBook(String title, String memberId) throws BookStateException {
        return librarian.lendBook(title, memberId);
    }

    public void returnBook(String title, String memberId) throws BookStateException {
        librarian.returnBook(title, memberId);
    }

    public String getLibrarySummary() {
        return library.getLibrarySummary();
    }


    public List<Book> getAllBooks() {
        return library.getBooks();
    }

    private void addSampleBooks() {
        addBook("The Catcher in the Rye", "J.D. Salinger", 1951, 5);
        addBook("To Kill a Mockingbird", "Harper Lee", 1960, 5);
        addBook("1984", "George Orwell", 1949, 5);
        addBook("Pride and Prejudice", "Jane Austen", 1813, 5);
        addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925, 5);
    }

    public void rateBook(String title, double rating) throws BookStateException {
        librarian.rateBook(title, rating);
    }

    public double getBookRating(String title) throws BookStateException {
        Book book = librarian.findBookByTitle(title);
        if (book instanceof RatedBook) {
            return ((RatedBook) book).getRating();
        } else if (book != null) {
            return 0.0; // Book found but not rated
        }
        return -1; // Book not found
    }

    public List<Book> getUserLoans(String userId) throws BookStateException{
        return librarian.getUserLoans(userId);
    }
}
