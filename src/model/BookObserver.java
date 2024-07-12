package model;

public class BookObserver implements Observer{
    private String name;

    public BookObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Book book) {
        System.out.println("Notification to " + name + ": Book " + book.getTitle() + " is now " + (book.isAvailable() ? "available" : "not available"));
    }
}
