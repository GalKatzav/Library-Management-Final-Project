import model.LibraryFacade;
import model.Book;
public class Main {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacade();

        // הוספת ספרים
        libraryFacade.addBook("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        libraryFacade.addBook("To Kill a Mockingbird", "Harper Lee", 1960);

        // הוספת קוראים
        libraryFacade.addMember("John Doe", "123");
        libraryFacade.addMember("Jane Smith", "456");

        // השאלת ספרים
        libraryFacade.lendBook("The Great Gatsby", "123");

        // החזרת ספרים
        libraryFacade.returnBook("The Great Gatsby", "123");

        // הצגת סיכום הספרייה
        libraryFacade.displayLibrarySummary();

        // שכפול ספר
        Book originalBook = libraryFacade.findBookByTitle("The Great Gatsby");
        if (originalBook != null) {
            Book clonedBook = libraryFacade.cloneBook(originalBook);
            libraryFacade.addBook(clonedBook.getTitle() + " (Copy)", clonedBook.getAuthor(), clonedBook.getYear());
        }

        // הצגת סיכום הספרייה לאחר השכפול
        libraryFacade.displayLibrarySummary();
    }
}
