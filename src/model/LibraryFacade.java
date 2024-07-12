package model;

public class LibraryFacade {
    private Library library;
    private Librarian librarian;

    public LibraryFacade() {
        this.library = SingletonLibrary.getInstance();
        this.librarian = new Librarian();
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
    public void displayLibrarySummary() {
        library.displayLibrarySummary();
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
}
