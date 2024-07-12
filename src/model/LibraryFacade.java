package model;

import java.util.List;

public class LibraryFacade {
    private Library library;
    private Librarian librarian;

    public LibraryFacade() {
        this.library = SingletonLibrary.getInstance();
        this.librarian = new Librarian();
        addSampleBooks(); // הוספת קריאה למתודה להוספת ספרים לדוגמה
    }

    public void addBook(String title, String author, int year) {
        librarian.addBook(title, author, year);
    }

    public void removeBook(String title) {
        librarian.removeBook(title);
    }

    public void addMember(String name, String id) {
        librarian.addMember(name, id);
    }

    public void removeMember(String id) {
        librarian.removeMember(id);
    }

    public void lendBook(String title, String memberId) {
        librarian.lendBook(title, memberId);
    }

    public void returnBook(String title, String memberId) {
        librarian.returnBook(title, memberId);
    }

    public String getLibrarySummary() {
        return library.getLibrarySummary();
    }

    public Book cloneBook(Book book) {
        return book.clone();
    }

    public Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return library.getBooks();
    }

    private void addSampleBooks() {
        addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
        addBook("To Kill a Mockingbird", "Harper Lee", 1960);
        addBook("1984", "George Orwell", 1949);
        addBook("Pride and Prejudice", "Jane Austen", 1813);
        addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925);
    }
}
