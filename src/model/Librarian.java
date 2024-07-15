package model;

import DesingP.decorator.RatedBook;
import DesingP.observer.BookObserver;
import DesingP.singleton.SingletonLibrary;
import DesingP.util.BookStateException;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Librarian} class manages the operations related to books and members in the library.
 * It interacts with the {@code Library} and performs actions such as adding and removing books,
 * updating book quantities, lending and returning books, and managing members.
 */
public class Librarian {

    /** The library instance used by the librarian to perform operations. */
    private final Library library;

    /**
     * Constructs a new {@code Librarian} object and initializes it with the singleton library instance.
     */
    public Librarian() {
        this.library = SingletonLibrary.getInstance();
    }

    /**
     * Adds a new book to the library with the specified title, author, year, and quantity.
     *
     * @param title    The title of the book.
     * @param author   The author of the book.
     * @param year     The publication year of the book.
     * @param quantity The total quantity of the book available in the library.
     */
    public void addBook(String title, String author, int year, int quantity) {
        Book book = new Book(title, author, year, quantity);
        BookObserver observer = new BookObserver("Library Staff");
        book.addObserver(observer);
        library.addBook(book);
    }

    /**
     * Removes a book from the library based on its title.
     *
     * @param title The title of the book to be removed.
     * @throws BookStateException If the book is not found in the library.
     */
    public void removeBook(String title) throws BookStateException{
        Book book = findBookByTitle(title);
        if (book != null) {
            library.removeBook(book);
        }else {
            throw new BookStateException("Book not found: " + title);
        }
    }

    /**
     * Updates the quantity of a book in the library based on its title.
     *
     * @param title    The title of the book.
     * @param quantity The new quantity of the book.
     * @throws BookStateException If the quantity is not between 0 and 20 or if the book is not found.
     */
    public void updateBookQuantity(String title, int quantity) throws BookStateException {
        if (quantity < 0 || quantity > 20) {
            throw new BookStateException("Quantity must be between 0 and 20: " + quantity);
        }
        Book book = findBookByTitle(title);
        if (book != null) {
            book.setQuantity(quantity);
        } else {
            throw new BookStateException("Book not found: " + title);
        }
    }

    /**
     * Adds a new member to the library with the specified name and ID.
     *
     * @param name The name of the member.
     * @param id   The ID of the member.
     * @throws BookStateException If the ID is already taken by another member.
     */
    public void addMember(String name, String id) throws BookStateException {
        if (findMemberById(id) != null) {
            throw new BookStateException("ID already taken: " + id);
        }
        Member member = new Member(name, id);
        library.addMember(member);
    }

    /**
     * Removes a member from the library based on their ID.
     *
     * @param id The ID of the member to be removed.
     * @throws BookStateException If the member is not found in the library.
     */
    public void removeMember(String id) throws BookStateException{
        Member member = findMemberById(id);
        if (member != null) {
            library.removeMember(member);
        }else {
            throw new BookStateException("Member not found: " + id);
        }
    }

    /**
     * Lends a book to a member based on the book's title and the member's ID.
     *
     * @param title    The title of the book to be lent.
     * @param memberId The ID of the member borrowing the book.
     * @return {@code true} if the book was successfully lent, {@code false} otherwise.
     * @throws BookStateException If the book or member is not found or if there are no available copies.
     */
    public boolean lendBook(String title, String memberId) throws BookStateException {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.isAvailable()) {
            book.lendCopy();
            Loan loan = new Loan(book, member);
            book.addLoan(loan);
            member.addLoan(loan);
            library.incrementLoanedBooks();
            return true;
        } else if (book == null) {
            throw new BookStateException("Book not found: " + title);
        } else if (member == null) {
            throw new BookStateException("Member not found: " + memberId);
        } else {
            throw new BookStateException("No available copies of the book: " + title);
        }
    }

    /**
     * Returns a borrowed book based on the book's title and the member's ID.
     *
     * @param title    The title of the book to be returned.
     * @param memberId The ID of the member returning the book.
     * @throws BookStateException If the book or member is not found or if there are no borrowed copies to return.
     */
    public void returnBook(String title, String memberId) throws BookStateException {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null) {
            book.returnCopy();
            Loan loan = member.findLoanByBook(title);
            if (loan != null) {
                member.removeLoan(loan);
                book.removeLoan(loan);
                library.decrementLoanedBooks(); // Update loaned books count
            }
        } else if (book == null) {
            throw new BookStateException("Book not found: " + title);
        } else {
            throw new BookStateException("Member not found: " + memberId);
        }
    }

    /**
     * Finds a book in the library based on its title.
     *
     * @param title The title of the book to be found.
     * @return The {@code Book} object if found, {@code null} otherwise.
     */
    public Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().contains(title) || book instanceof RatedBook && (book).getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Finds a member in the library based on their ID.
     *
     * @param id The ID of the member to be found.
     * @return The {@code Member} object if found, {@code null} otherwise.
     */
    public Member findMemberById(String id) {
        for (Member member : library.getMembers()) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    /**
     * Rates a book in the library based on its title.
     *
     * @param title   The title of the book to be rated.
     * @param rating  The rating to be assigned to the book.
     * @throws BookStateException If the book is not found in the library.
     */
    public void rateBook(String title, double rating) throws BookStateException{
        Book book = findBookByTitle(title);
        if (book != null) {
            if (book instanceof RatedBook) {
                ((RatedBook) book).setRating(rating);
            } else {
                RatedBook ratedBook = new RatedBook(book, rating);
                library.removeBook(book);
                library.addBook(ratedBook);
            }
        }else {
            throw new BookStateException("Book not found: " + title);
        }
    }

    /**
     * Retrieves the list of books currently borrowed by a member based on their ID.
     *
     * @param userId The ID of the member.
     * @return A list of {@code Book} objects currently borrowed by the member.
     * @throws BookStateException If the member is not found in the library.
     */
    public List<Book> getUserLoans(String userId) throws BookStateException{
        Member member = findMemberById(userId);
        if (member != null) {
            List<Book> books = new ArrayList<>();
            for (Loan loan : member.getLoans()) {
                books.add(loan.getBook());
            }
            return books;
        } else {
            throw new BookStateException("Member not found: " + userId);
        }
    }
}
