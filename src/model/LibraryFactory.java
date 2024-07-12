package model;

public abstract class LibraryFactory {
    public abstract Book createBook(String title, String author, int year);
    public abstract Member createMember(String name, String id);
}
