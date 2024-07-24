package model;

import DesingP.decorator.RatedBook;
import DesingP.observer.BookObserver;
import DesingP.singleton.SingletonLibrary;
import DesingP.util.BookStateException;
import DesingP.observer.MemberObserver;

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
        this.library = SingletonLibrary.getInstance(); // Initializes the library field with the singleton instance
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
        Book book = new Book(title, author, year, quantity); // Creates a new Book object
        BookObserver observer = new BookObserver("Library Staff"); // Creates a new BookObserver object
        book.addObserver(observer); // Adds the observer to the book
        library.addBook(book); // Adds the book to the library
    }

    /**
     * Removes a book from the library based on its title.
     *
     * @param title The title of the book to be removed.
     * @throws BookStateException If the book is not found in the library.
     */
    public void removeBook(String title) throws BookStateException {
        Book book = findBookByTitle(title); // Finds the book by its title
        if (book != null) {
            if (book.getBorrowedQuantity() == 0) { // Check if no copies of the book are borrowed
                library.removeBook(book); // Removes the book from the library
            } else {
                throw new BookStateException("Cannot remove the book. There are borrowed copies."); // Throws an exception if the book is borrowed
            }
        } else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
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
            throw new BookStateException("Quantity must be between 0 and 20: " + quantity); // Throws an exception if the quantity is out of range
        }
        Book book = findBookByTitle(title); // Finds the book by its title
        if (book != null) {
            book.setQuantity(quantity); // Updates the quantity of the book
        } else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
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
            throw new BookStateException("ID already taken: " + id); // Throws an exception if the ID is already taken
        }
        Member member = new Member(name, id); // Creates a new Member object
        MemberObserver observer = new MemberObserver(name); // Creates a new MemberObserver object
        member.addObserver(observer); // Adds the observer to the member
        library.addMember(member); // Adds the member to the library
    }

    /**
     * Removes a member from the library based on their ID.
     *
     * @param id The ID of the member to be removed.
     * @throws BookStateException If the member is not found in the library.
     */
    public void removeMember(String id) throws BookStateException {
        Member member = findMemberById(id); // Finds the member by their ID
        if (member != null) {
            if (member.getLoans().isEmpty()) { // Check if the member has any loans
                library.removeMember(member); // Removes the member from the library
            } else {
                throw new BookStateException("Member has borrowed books and cannot be removed."); // Throws an exception if the member has borrowed books
            }
        } else {
            throw new BookStateException("Member not found: " + id); // Throws an exception if the member is not found
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
        Book book = findBookByTitle(title); // Finds the book by its title
        Member member = findMemberById(memberId); // Finds the member by their ID
        if (book != null && member != null && book.isAvailable()) {
            book.lendCopy(); // Lends a copy of the book
            Loan loan = new Loan(book, member); // Creates a new Loan object
            book.addLoan(loan); // Adds the loan to the book
            member.addLoan(loan); // Increments the count of loaned books in the library
            library.incrementLoanedBooks(); // Returns true if the book was successfully lent
            return true;
        } else if (book == null) {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        } else if (member == null) {
            throw new BookStateException("Member not found: " + memberId); // Throws an exception if the member is not found
        } else {
            throw new BookStateException("No available copies of the book: " + title); // Throws an exception if there are no available copies
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
        Book book = findBookByTitle(title); // Finds the book by its title
        Member member = findMemberById(memberId); // Finds the member by their ID
        if (book != null && member != null) {
            book.returnCopy(); // Returns a borrowed copy of the book
            Loan loan = member.findLoanByBook(title); // Finds the loan by the book title
            if (loan != null) {
                member.removeLoan(loan); // Removes the loan from the member
                book.removeLoan(loan); // Removes the loan from the book
                library.decrementLoanedBooks(); // Decrements the count of loaned books in the library
            }
        } else if (book == null) {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
        } else {
            throw new BookStateException("Member not found: " + memberId); // Throws an exception if the member is not found
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
            // Checks if the book's title contains the given title or if it is a RatedBook with an exact title match
            if (book.getTitle().contains(title) || book instanceof RatedBook && (book).getTitle().equals(title)) {
                return book; // Returns the book if found
            }
        }
        return null; // Returns null if the book is not found
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
                return member; // Returns the member if found
            }
        }
        return null; // Returns null if the member is not found
    }

    /**
     * Rates a book in the library based on its title.
     *
     * @param title   The title of the book to be rated.
     * @param rating  The rating to be assigned to the book.
     * @throws BookStateException If the book is not found in the library.
     */
    public void rateBook(String title, double rating) throws BookStateException{
        Book book = findBookByTitle(title); // Finds the book by its title
        if (book != null) {
            if (book instanceof RatedBook) {
                ((RatedBook) book).setRating(rating); // Sets the rating if the book is already a RatedBook
            } else {
                RatedBook ratedBook = new RatedBook(book, rating); // Creates a new RatedBook with the rating
                library.removeBook(book); // Removes the original book from the library
                library.addBook(ratedBook); // Adds the rated book to the library
            }
        }else {
            throw new BookStateException("Book not found: " + title); // Throws an exception if the book is not found
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
        Member member = findMemberById(userId); // Finds the member by their ID
        if (member != null) {
            List<Book> books = new ArrayList<>(); // Creates a new list to hold the borrowed books
            for (Loan loan : member.getLoans()) {
                books.add(loan.getBook()); // Adds each borrowed book to the list
            }
            return books; // Returns the list of borrowed books
        } else {
            throw new BookStateException("Member not found: " + userId); // Throws an exception if the member is not found
        }
    }
}
