package DesingP.observer;

import model.Book;

public class BookObserver implements Observer {
    private String name;

    public BookObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + name + ": " + message);
    }
}
