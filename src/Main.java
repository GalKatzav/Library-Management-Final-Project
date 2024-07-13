import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DesingP.observer.BookObserver;
import DesingP.facade.LibraryFacade;
import DesingP.observer.MemberObserver;
import DesingP.util.BookStateException;
import model.Book;

public class Main extends JFrame {
    private LibraryFacade libraryFacade;

    public Main() {
        libraryFacade = new LibraryFacade();
        setTitle("Digital Library");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // יצירת ממשק משתמש
        JLabel libraryNameLabel = new JLabel("Welcome to the Digital Library", SwingConstants.CENTER);
        libraryNameLabel.setFont(new Font("Serif", Font.BOLD, 24)); // הגדלת גודל הכתב
        JLabel summaryLabel = new JLabel("", SwingConstants.CENTER);
        updateSummaryLabel(summaryLabel);

        JButton menuButton = new JButton("Open Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenu(summaryLabel);
            }
        });

        // הגדרת GridBagLayout למרכז את האלמנטים
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // הוספת מרווחים

        add(libraryNameLabel, gbc);

        gbc.gridy++;
        add(summaryLabel, gbc);

        gbc.gridy++;
        add(menuButton, gbc);
    }

    private void openMenu(JLabel summaryLabel) {
        JFrame menuFrame = new JFrame("Library Menu");
        menuFrame.setSize(400, 550);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // הוספת מרווחים בין הכפתורים

        JButton addBookButton = createMenuButton("Add Book", e -> {
            addBook();
            updateSummaryLabel(summaryLabel);
        });

        JButton addMemberButton = createMenuButton("Add Member", e -> {
            addMember();
            updateSummaryLabel(summaryLabel);
        });

        JButton lendBookButton = createMenuButton("Lend Book", e -> {
            lendBook();
            updateSummaryLabel(summaryLabel);
        });

        JButton returnBookButton = createMenuButton("Return Book", e -> {
            returnBook();
            updateSummaryLabel(summaryLabel);
        });

        JButton removeBookButton = createMenuButton("Remove Book", e -> {
            removeBook();
            updateSummaryLabel(summaryLabel);
        });

        JButton removeMemberButton = createMenuButton("Remove Member", e -> {
            removeMember();
            updateSummaryLabel(summaryLabel);
        });

        JButton summaryButton = createMenuButton("Update Summary", e -> updateSummaryLabel(summaryLabel));

        JButton viewBooksButton = createMenuButton("View All Books", e -> viewAllBooks());

        JButton rateBookButton = createMenuButton("Rate Book", e -> {
            showRateBookDialog();
            updateSummaryLabel(summaryLabel);
        });

        JButton viewRatingButton = createMenuButton("View Book Rating", e -> {
            showViewRatingDialog();
        });

        JButton viewLoansButton = createMenuButton("View User Loans", e -> {
            viewUserLoans();
        });

        addButtonToMenu(menuFrame, addBookButton, gbc);
        addButtonToMenu(menuFrame, addMemberButton, gbc);
        addButtonToMenu(menuFrame, lendBookButton, gbc);
        addButtonToMenu(menuFrame, returnBookButton, gbc);
        addButtonToMenu(menuFrame, removeBookButton, gbc);
        addButtonToMenu(menuFrame, removeMemberButton, gbc);
        addButtonToMenu(menuFrame, summaryButton, gbc);
        addButtonToMenu(menuFrame, viewBooksButton, gbc);
        addButtonToMenu(menuFrame, rateBookButton, gbc);
        addButtonToMenu(menuFrame, viewRatingButton, gbc);
        addButtonToMenu(menuFrame, viewLoansButton, gbc);

        menuFrame.setVisible(true);
    }

    private void viewUserLoans() {
        String memberId = JOptionPane.showInputDialog("Enter the member ID to view loans:");
        if (memberId != null && !memberId.isEmpty()) {
            try {
                java.util.List<Book> loans = libraryFacade.getUserLoans(memberId);
                if (!loans.isEmpty()) {
                    StringBuilder loanList = new StringBuilder();
                    int count = 1;
                    for (Book book : loans) {
                        loanList.append(count++).append(". ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" (").append(book.getYear()).append(")\n");
                    }
                    JTextArea textArea = new JTextArea(loanList.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));
                    JOptionPane.showMessageDialog(null, scrollPane, "User Loans", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No loans found for the member ID \"" + memberId + "\"", "User Loans", JOptionPane.ERROR_MESSAGE);
                }
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private JButton createMenuButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void addButtonToMenu(JFrame menuFrame, JButton button, GridBagConstraints gbc) {
        menuFrame.add(button, gbc);
        gbc.gridy++;
    }

    private void addBook() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField quantityField = new JTextField();
        Object[] message = {
                "Title:", titleField,
                "Author:", authorField,
                "Year:", yearField,
                "Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            libraryFacade.addBook(title, author, year, quantity);
        }
    }

    private void addMember() {
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        Object[] message = {
                "Name:", nameField,
                "ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String id = idField.getText();
            libraryFacade.addMember(name, id);
        }
    }

    private void lendBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to lend:");
        String memberId = JOptionPane.showInputDialog("Enter the member ID:");
        try {
            libraryFacade.lendBook(title, memberId);
            JOptionPane.showMessageDialog(null, "Book lent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to return:");
        String memberId = JOptionPane.showInputDialog("Enter the member ID:");
        try {
            libraryFacade.returnBook(title, memberId);
            JOptionPane.showMessageDialog(null, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to remove:");
        try {
            libraryFacade.removeBook(title);
            JOptionPane.showMessageDialog(null, "Book removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeMember() {
        String id = JOptionPane.showInputDialog("Enter the member ID to remove:");
        try {
            libraryFacade.removeMember(id);
            JOptionPane.showMessageDialog(null, "Member removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAllBooks() {
        java.util.List<Book> books = libraryFacade.getAllBooks();
        StringBuilder bookList = new StringBuilder();
        int count = 1;
        for (Book book : books) {
            bookList.append(count++).append(". ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" (").append(book.getYear()).append(")\n");
        }
        JTextArea textArea = new JTextArea(bookList.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRateBookDialog() {
        JTextField titleField = new JTextField(10);
        JTextField ratingField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(Box.createHorizontalStrut(15)); // מרווח אופקי
        panel.add(new JLabel("Rating (0-10):"));
        panel.add(ratingField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Rate Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            double rating;
            try {
                rating = Double.parseDouble(ratingField.getText());
                if (rating < 0 || rating > 10) {
                    throw new NumberFormatException();
                }
                libraryFacade.rateBook(title, rating);
                JOptionPane.showMessageDialog(null, "Book rated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException | BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Rating", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void showViewRatingDialog() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to view rating:");
        if (title != null && !title.isEmpty()) {
            try {
                double rating = libraryFacade.getBookRating(title);
                if (rating >= 0) {
                    JOptionPane.showMessageDialog(null, "The rating for \"" + title + "\" is: " + rating, "Book Rating", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rating found for the book \"" + title + "\"", "Book Rating", JOptionPane.ERROR_MESSAGE);
                }
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void updateSummaryLabel(JLabel summaryLabel) {
        String summary = libraryFacade.getLibrarySummary().replace("\n", "<br><br>");
        summaryLabel.setText("<html>" + summary + "</html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}