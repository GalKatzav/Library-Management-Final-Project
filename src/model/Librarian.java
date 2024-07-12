package model;

public class Librarian {
    private Library library;

    public Librarian() {
        this.library = SingletonLibrary.getInstance();
    }

    public void addBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        library.addBook(book);
    }

    public void removeBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            library.removeBook(book);
        }
    }

    public void addMember(String name, String id) {
        Member member = new Member(name, id);
        library.addMember(member);
    }

    public void removeMember(String id) {
        Member member = findMemberById(id);
        if (member != null) {
            library.removeMember(member);
        }
    }

    public void lendBook(String title, String memberId) {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.isAvailable()) {
            book.setAvailable(false);
            Loan loan = new Loan(book, member);
            book.addLoan(loan);
            member.addLoan(loan);
        }
    }

    public void returnBook(String title, String memberId) {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null && !book.isAvailable()) {
            book.setAvailable(true);
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(String id) {
        for (Member member : library.getMembers()) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
}
