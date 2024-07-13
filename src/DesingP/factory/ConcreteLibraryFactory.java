package DesingP.factory;

import model.Book;
import model.Member;

public class ConcreteLibraryFactory extends LibraryFactory {
    public Book createBook(String title, String author, int year, int quantity) {
        return new Book(title, author, year, quantity);
    }
    public Member createMember(String name, String id) {
        return new Member(name, id);
    }
}
