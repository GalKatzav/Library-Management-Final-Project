package model;

import DesingP.decorator.RatedBook;
import DesingP.observer.BookObserver;
import DesingP.singleton.SingletonLibrary;
import DesingP.util.BookStateException;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private Library library;

    public Librarian() {
        this.library = SingletonLibrary.getInstance();
    }

    public void addBook(String title, String author, int year, int quantity) {
        Book book = new Book(title, author, year, quantity);
        BookObserver observer = new BookObserver("Library Staff");
        book.addObserver(observer);
        library.addBook(book);
    }


    public void removeBook(String title) throws BookStateException{
        Book book = findBookByTitle(title);
        if (book != null) {
            library.removeBook(book);
        }else {
            throw new BookStateException("Book not found: " + title);
        }
    }

    public void addMember(String name, String id) {
        Member member = new Member(name, id);
        library.addMember(member);
    }

    public void removeMember(String id) throws BookStateException{
        Member member = findMemberById(id);
        if (member != null) {
            library.removeMember(member);
        }else {
            throw new BookStateException("Member not found: " + id);
        }
    }

    public boolean lendBook(String title, String memberId) throws BookStateException {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.isAvailable()) {
            book.lendCopy();
            Loan loan = new Loan(book, member);
            book.addLoan(loan);
            member.addLoan(loan);
            library.incrementLoanedBooks(); // Update loaned books count
            return true;
        } else if (book == null) {
            throw new BookStateException("Book not found: " + title);
        } else if (member == null) {
            throw new BookStateException("Member not found: " + memberId);
        } else {
            throw new BookStateException("No available copies of the book: " + title);
        }
    }



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




    public Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().contains(title) || book instanceof RatedBook && ((RatedBook) book).getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(String id) {
        for (Member member : library.getMembers()) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }
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
