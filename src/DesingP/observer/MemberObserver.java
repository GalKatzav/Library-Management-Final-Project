package DesingP.observer;

public class MemberObserver implements Observer {
    private String name;

    public MemberObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + name + ": " + message);
    }
}
