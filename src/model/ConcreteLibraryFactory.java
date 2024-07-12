package model;

public class ConcreteLibraryFactory extends LibraryFactory{
    public Book createBook(String title, String author, int year) {
        return new Book(title, author, year);
    }
    public Member createMember(String name, String id) {
        return new Member(name, id);
    }
}
