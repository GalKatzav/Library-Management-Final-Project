package model;

import decorator.RatedBook;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private Library library;

    public Librarian() {
        this.library = SingletonLibrary.getInstance();
    }

    public void addBook(String title, String author, int year, int quantity) {
        Book book = new Book(title, author, year, quantity);
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

    public boolean lendBook(String title, String memberId) {
        Book book = findBookByTitle(title);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.isAvailable()) {
            book.lendCopy();
            Loan loan = new Loan(book, member);
            book.addLoan(loan);
            member.addLoan(loan);
            library.incrementLoanedBooks(); // Update loaned books count
            return true;
        }
        return false;
    }


    public void returnBook(String title, String memberId) {
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
        }
    }



    protected Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().contains(title) || book instanceof RatedBook && ((RatedBook) book).getTitle().equals(title)) {
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
    public void rateBook(String title, double rating) {
        Book book = findBookByTitle(title);
        if (book != null) {
            if (book instanceof RatedBook) {
                ((RatedBook) book).setRating(rating);
            } else {
                RatedBook ratedBook = new RatedBook(book, rating);
                library.removeBook(book);
                library.addBook(ratedBook);
            }
        }
    }
    public List<Book> getUserLoans(String userId) {
        Member member = findMemberById(userId);
        if (member != null) {
            return member.getLoans();
        }
        return new ArrayList<>();
    }


}
