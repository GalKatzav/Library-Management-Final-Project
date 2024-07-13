package DesingP.singleton;

import model.Library;

public class SingletonLibrary extends Library {
    private static SingletonLibrary instance;

    private SingletonLibrary() {
        super();
    }
    public static SingletonLibrary getInstance() {
        if (instance == null) {
            instance = new SingletonLibrary();
        }
        return instance;
    }
}
