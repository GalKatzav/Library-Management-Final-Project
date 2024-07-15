package test;

import static org.junit.jupiter.api.Assertions.*;

import DesingP.singleton.SingletonLibrary;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonLibraryTest {

    @BeforeEach
    public void setUp() {
        SingletonLibrary.resetInstance();
    }

    @Test
    public void testSingletonInstance() {
        // בדיקה שהמחלקה מחזירה מופע יחיד
        SingletonLibrary instance1 = SingletonLibrary.getInstance();
        SingletonLibrary instance2 = SingletonLibrary.getInstance();

        assertSame(instance1, instance2, "Singleton instances are not the same");
    }

    @Test
    public void testLibraryFunctionality() {
        // בדיקה שהמופע מתפקד כספרייה רגילה
        SingletonLibrary library = SingletonLibrary.getInstance();
        assertEquals(0, library.getBooks().size(), "Library should be empty initially");

        // הוספת ספר לספרייה ובדיקה שהוא נוסף כראוי
        library.addBook(new Book("Test Book", "Test Author", 2023, 1));
        assertEquals(1, library.getBooks().size(), "Library should contain one book");
    }

    @Test
    public void testClearLibrary() {
        // בדיקה שהמופע של הספרייה מתאפס כראוי בין בדיקות
        SingletonLibrary library = SingletonLibrary.getInstance();
        library.getBooks().clear();
        library.getMembers().clear();

        assertEquals(0, library.getBooks().size(), "Library should be empty after clearing");
        assertEquals(0, library.getMembers().size(), "Library should have no members after clearing");
    }
}
