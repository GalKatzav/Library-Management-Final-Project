import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DesingP.facade.LibraryFacade;
import DesingP.util.BookStateException;
import model.Book;


/**
 * The {@code Main} class represents the main application window for the Digital Library system.
 * It provides a graphical user interface (GUI) for interacting with the library system, including
 * adding and removing books, updating book quantities, lending and returning books, managing members,
 * and viewing library information.
 */
public class Main extends JFrame {

    /** The facade for interacting with the library system. */
    private final LibraryFacade libraryFacade;

    /**
     * Constructs a new {@code Main} object and initializes the main application window.
     */
    public Main() {
        libraryFacade = new LibraryFacade(); // Initializes the LibraryFacade
        setTitle("Digital Library"); // Sets the title of the window
        setSize(600, 400); // Sets the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default close operation
        setLocationRelativeTo(null); // Centers the window

        JLabel libraryNameLabel = new JLabel("Welcome to the Digital Library", SwingConstants.CENTER); // Creates a label for the library name
        libraryNameLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Sets the font of the label
        JLabel summaryLabel = new JLabel("", SwingConstants.CENTER); // Creates a label for the summary
        updateSummaryLabel(summaryLabel); // Updates the summary label

        JButton menuButton = new JButton("Open Menu"); // Creates a button to open the menu
        menuButton.addActionListener(new ActionListener() { // Adds an action listener to the button
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenu(summaryLabel); // Opens the menu when the button is clicked
            }
        });

        setLayout(new GridBagLayout()); // Sets the layout of the main window to GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // Creates GridBagConstraints for layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Sets padding

        add(libraryNameLabel, gbc); // Adds the library name label to the window

        gbc.gridy++;
        add(summaryLabel, gbc); // Adds the summary label to the window

        gbc.gridy++;
        add(menuButton, gbc); // Adds the menu button to the window
    }

    /**
     * Opens the library menu in a new window.
     *
     * @param summaryLabel The label to update the library summary.
     */
    private void openMenu(JLabel summaryLabel) {
        JFrame menuFrame = new JFrame("Library Menu"); // Creates a new frame for the menu
        menuFrame.setSize(400, 650); // Sets the size of the menu frame
        menuFrame.setLocationRelativeTo(null); // Centers the menu frame
        menuFrame.setLayout(new GridBagLayout()); // Sets the layout of the menu frame to GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // Creates GridBagConstraints for layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Sets padding

        JButton addBookButton = createMenuButton("Add Book", e -> {
            addBook();
            updateSummaryLabel(summaryLabel); // Updates the summary label after adding a book
        });

        JButton addMemberButton = createMenuButton("Add Member", e -> {
            addMember();
            updateSummaryLabel(summaryLabel); // Updates the summary label after adding a member
        });

        JButton lendBookButton = createMenuButton("Lend Book", e -> {
            lendBook();
            updateSummaryLabel(summaryLabel); // Updates the summary label after lending a book
        });

        JButton returnBookButton = createMenuButton("Return Book", e -> {
            returnBook();
            updateSummaryLabel(summaryLabel); // Updates the summary label after returning a book
        });

        JButton removeBookButton = createMenuButton("Remove Book", e -> {
            removeBook();
            updateSummaryLabel(summaryLabel); // Updates the summary label after removing a book
        });

        JButton removeMemberButton = createMenuButton("Remove Member", e -> {
            removeMember();
            updateSummaryLabel(summaryLabel); // Updates the summary label after removing a member
        });

        JButton summaryButton = createMenuButton("Update Summary", e -> updateSummaryLabel(summaryLabel)); // Updates the summary label when the button is clicked

        JButton viewBooksButton = createMenuButton("View All Books", e -> viewAllBooks()); // Views all books when the button is clicked

        JButton rateBookButton = createMenuButton("Rate Book", e -> {
            showRateBookDialog();
            updateSummaryLabel(summaryLabel); // Updates the summary label after rating a book
        });

        JButton viewRatingButton = createMenuButton("View Book Rating", e -> {
            showViewRatingDialog(); // Views the rating of a book when the button is clicked
        });

        JButton viewLoansButton = createMenuButton("View User Loans", e -> {
            viewUserLoans(); // Views the loans of a user when the button is clicked
        });

        JButton viewBookDetailsButton = createMenuButton("View Book Details", e -> {
            viewBookDetails(); // Views the details of a book when the button is clicked
        });

        JButton updateBookQuantityButton = createMenuButton("Update Book Quantity", e -> {
            updateBookQuantity();
            updateSummaryLabel(summaryLabel); // Updates the summary label after updating the quantity of a book
        });

        // Adds the buttons to the menu
        addButtonToMenu(menuFrame, addBookButton, gbc);
        addButtonToMenu(menuFrame, addMemberButton, gbc);
        addButtonToMenu(menuFrame, lendBookButton, gbc);
        addButtonToMenu(menuFrame, returnBookButton, gbc);
        addButtonToMenu(menuFrame, removeBookButton, gbc);
        addButtonToMenu(menuFrame, removeMemberButton, gbc);
        addButtonToMenu(menuFrame, updateBookQuantityButton, gbc);
        addButtonToMenu(menuFrame, viewBooksButton, gbc);
        addButtonToMenu(menuFrame, viewBookDetailsButton, gbc);
        addButtonToMenu(menuFrame, rateBookButton, gbc);
        addButtonToMenu(menuFrame, viewRatingButton, gbc);
        addButtonToMenu(menuFrame, viewLoansButton, gbc);
        addButtonToMenu(menuFrame, summaryButton, gbc);


        menuFrame.setVisible(true); // Makes the menu frame visible
    }

    /**
     * Updates the quantity of a book in the library.
     */
    private void updateBookQuantity() {
        JTextField titleField = new JTextField(10); // Creates a text field for the book title
        JTextField quantityField = new JTextField(10); // Creates a text field for the book quantity

        JPanel panel = new JPanel(); // Creates a panel to hold the input fields
        panel.add(new JLabel("Title:")); // Adds a label for the title
        panel.add(titleField); // Adds the title text field to the panel
        panel.add(Box.createHorizontalStrut(15)); // Adds horizontal spacing
        panel.add(new JLabel("New Quantity (0-20):")); // Adds a label for the quantity
        panel.add(quantityField); // Adds the quantity text field to the panel

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Book Quantity", JOptionPane.OK_CANCEL_OPTION); // Shows a confirmation dialog with the panel
        if (result == JOptionPane.OK_OPTION) { // If OK is clicked
            String title = titleField.getText(); // Gets the title from the text field
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText()); // Parses the quantity from the text field
                if (quantity < 0 || quantity > 20) {
                    throw new NumberFormatException(); // Throws an exception if the quantity is out of range
                }
                libraryFacade.updateBookQuantity(title, quantity); // Updates the book quantity using the facade
                JOptionPane.showMessageDialog(null, "Book quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
            } catch (NumberFormatException | BookStateException e) {
                // Shows an error message
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity between 0 and 20 and ensure the book exists.", "Invalid Quantity or Book Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Displays the details of a book in the library.
     */
    private void viewBookDetails() {
        // Shows an input dialog to get the book title
        String title = JOptionPane.showInputDialog("Enter the title of the book to view details:");
        if (title != null && !title.isEmpty()) { // If the title is not null and not empty
            try {
                Book book = libraryFacade.findBookByTitle(title); // Finds the book using the facade
                if (book != null) { // If the book is found
                    int totalCopies = book.getQuantity(); // Gets the total copies of the book
                    int borrowedCopies = book.getBorrowedQuantity(); // Gets the borrowed copies of the book
                    int availableCopies = book.getAvailableQuantity(); // Gets the available copies of the book

                    StringBuilder bookDetails = new StringBuilder(); // Creates a StringBuilder to build the book details
                    bookDetails.append("Title: ").append(book.getTitle()).append("\n");
                    bookDetails.append("Total Copies: ").append(totalCopies).append("\n");
                    bookDetails.append("Borrowed Copies: ").append(borrowedCopies).append("\n");
                    bookDetails.append("Available Copies: ").append(availableCopies).append("\n");

                    JTextArea textArea = new JTextArea(bookDetails.toString()); // Creates a text area to display the book details
                    textArea.setEditable(false); // Makes the text area non-editable
                    JScrollPane scrollPane = new JScrollPane(textArea); // Creates a scroll pane for the text area
                    scrollPane.setPreferredSize(new Dimension(400, 300)); // Sets the preferred size of the scroll pane
                    JOptionPane.showMessageDialog(null, scrollPane, "Book Details", JOptionPane.INFORMATION_MESSAGE); // Shows a message dialog with the book details
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found: " + title, "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if the book is not found
                }
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
            }
        }
    }

    /**
     * Displays the list of books currently borrowed by a member.
     */
    private void viewUserLoans() {
        String memberId = JOptionPane.showInputDialog("Enter the member ID to view loans:"); // Shows an input dialog to get the member ID
        if (memberId != null && !memberId.isEmpty()) { // If the member ID is not null and not empty
            try {
                java.util.List<Book> loans = libraryFacade.getUserLoans(memberId); // Gets the loans of the member using the facade
                if (!loans.isEmpty()) { // If the member has loans
                    StringBuilder loanList = new StringBuilder(); // Creates a StringBuilder to build the loan list
                    int count = 1;
                    for (Book book : loans) { // For each loan
                        // Appends the loan details
                        loanList.append(count++).append(". ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" (").append(book.getYear()).append(")\n");
                    }
                    JTextArea textArea = new JTextArea(loanList.toString()); // Creates a text area to display the loan list
                    textArea.setEditable(false); // Makes the text area non-editable
                    JScrollPane scrollPane = new JScrollPane(textArea); // Creates a scroll pane for the text area
                    scrollPane.setPreferredSize(new Dimension(400, 300)); // Sets the preferred size of the scroll pane
                    JOptionPane.showMessageDialog(null, scrollPane, "User Loans", JOptionPane.INFORMATION_MESSAGE); // Shows a message dialog with the loan list
                } else {
                    // Shows an error message if no loans are found
                    JOptionPane.showMessageDialog(null, "No loans found for the member ID \"" + memberId + "\"", "User Loans", JOptionPane.ERROR_MESSAGE);
                }
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
            }
        }
    }

    /**
     * Creates a new button for the library menu.
     *
     * @param text The text to display on the button.
     * @param actionListener The action listener for the button.
     * @return The created button.
     */
    private JButton createMenuButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text); // Creates a new button with the specified text
        button.addActionListener(actionListener); // Adds the action listener to the button
        return button; // Returns the created button
    }

    /**
     * Adds a button to the library menu frame.
     *
     * @param menuFrame The frame of the library menu.
     * @param button The button to add.
     * @param gbc The GridBagConstraints for layout.
     */
    private void addButtonToMenu(JFrame menuFrame, JButton button, GridBagConstraints gbc) {
        menuFrame.add(button, gbc); // Adds the button to the menu frame
        gbc.gridy++; // Increments the grid y position
    }

    /**
     * Adds a new book to the library.
     */
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

        // Shows a confirmation dialog with the input fields
        int option = JOptionPane.showConfirmDialog(null, message, "Add New Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) { // If OK is clicked
            String title = titleField.getText(); // Gets the title from the text field
            String author = authorField.getText(); // Gets the author from the text field
            int year = Integer.parseInt(yearField.getText()); // Parses the year from the text field
            int quantity = Integer.parseInt(quantityField.getText()); // Parses the quantity from the text field
            libraryFacade.addBook(title, author, year, quantity); // Adds the book using the facade
        }
    }

    /**
     * Adds a new member to the library.
     */
    private void addMember() {
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        Object[] message = {
                "Name:", nameField,
                "ID:", idField
        };

        // Shows a confirmation dialog with the input fields
        int option = JOptionPane.showConfirmDialog(null, message, "Add New Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) { // If OK is clicked
            String name = nameField.getText(); // Gets the name from the text field
            String id = idField.getText(); // Gets the ID from the text field
            try {
                libraryFacade.addMember(name, id); // Adds the member using the facade
                // Shows a success message
                JOptionPane.showMessageDialog(null, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
            }
        }
    }

    /**
     * Lends a book to a member.
     */
    private void lendBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to lend:"); // Shows an input dialog to get the book title
        String memberId = JOptionPane.showInputDialog("Enter the member ID:"); // Shows an input dialog to get the member ID
        try {
            libraryFacade.lendBook(title, memberId); // Lends the book using the facade
            JOptionPane.showMessageDialog(null, "Book lent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
        }
    }

    /**
     * Returns a borrowed book to the library.
     */
    private void returnBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to return:"); // Shows an input dialog to get the book title
        String memberId = JOptionPane.showInputDialog("Enter the member ID:"); // Shows an input dialog to get the member ID
        try {
            libraryFacade.returnBook(title, memberId); // Returns the book using the facade
            JOptionPane.showMessageDialog(null, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
        }
    }

    /**
     * Removes a book from the library.
     */
    private void removeBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to remove:"); // Shows an input dialog to get the book title
        try {
            libraryFacade.removeBook(title); // Removes the book using the facade
            JOptionPane.showMessageDialog(null, "Book removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
        }
    }

    /**
     * Removes a member from the library.
     */
    private void removeMember() {
        String id = JOptionPane.showInputDialog("Enter the member ID to remove:"); // Shows an input dialog to get the member ID
        try {
            libraryFacade.removeMember(id); // Removes the member using the facade
            JOptionPane.showMessageDialog(null, "Member removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
        } catch (BookStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
        }
    }

    /**
     * Displays a list of all books in the library.
     */
    private void viewAllBooks() {
        java.util.List<Book> books = libraryFacade.getAllBooks(); // Gets all books using the facade
        StringBuilder bookList = new StringBuilder(); // Creates a StringBuilder to build the book list
        int count = 1;
        for (Book book : books) { // For each book
            // Appends the book details
            bookList.append(count++).append(". ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" (").append(book.getYear()).append(")\n");
        }
        JTextArea textArea = new JTextArea(bookList.toString()); // Creates a text area to display the book list
        textArea.setEditable(false); // Makes the text area non-editable
        JScrollPane scrollPane = new JScrollPane(textArea); // Creates a scroll pane for the text area
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Sets the preferred size of the scroll pane
        JOptionPane.showMessageDialog(null, scrollPane, "All Books", JOptionPane.INFORMATION_MESSAGE); // Shows a message dialog with the book list
    }

    /**
     * Displays a dialog to rate a book.
     */
    private void showRateBookDialog() {
        JTextField titleField = new JTextField(10); // Creates a text field for the book title
        JTextField ratingField = new JTextField(10); // Creates a text field for the book rating

        JPanel panel = new JPanel(); // Creates a panel to hold the input fields
        panel.add(new JLabel("Title:")); // Adds a label for the title
        panel.add(titleField); // Adds the title text field to the panel
        panel.add(Box.createHorizontalStrut(15)); // Adds horizontal spacing
        panel.add(new JLabel("Rating (0-10):")); // Adds a label for the rating
        panel.add(ratingField); // Adds the rating text field to the panel

        // Shows a confirmation dialog with the panel
        int result = JOptionPane.showConfirmDialog(null, panel, "Rate Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) { // If OK is clicked
            String title = titleField.getText(); // Gets the title from the text field
            double rating;
            try {
                rating = Double.parseDouble(ratingField.getText()); // Parses the rating from the text field
                if (rating < 0 || rating > 10) {
                    throw new NumberFormatException(); // Throws an exception if the rating is out of range
                }
                libraryFacade.rateBook(title, rating); // Rates the book using the facade
                JOptionPane.showMessageDialog(null, "Book rated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // Shows a success message
            } catch (NumberFormatException | BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Rating", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
            }
        }
    }

    /**
     * Displays the rating of a book.
     */
    private void showViewRatingDialog() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to view rating:"); // Shows an input dialog to get the book title
        if (title != null && !title.isEmpty()) { // If the title is not null and not empty
            try {
                double rating = libraryFacade.getBookRating(title); // Gets the rating of the book using the facade
                if (rating >= 0) { // If the rating is valid
                    // Shows the rating
                    JOptionPane.showMessageDialog(null, "The rating for \"" + title + "\" is: " + rating, "Book Rating", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Shows an error message if no rating is found
                    JOptionPane.showMessageDialog(null, "No rating found for the book \"" + title + "\"", "Book Rating", JOptionPane.ERROR_MESSAGE);
                }
            } catch (BookStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Shows an error message if an exception occurs
            }
        }
    }

    /**
     * Updates the summary label with the current summary of the library.
     *
     * @param summaryLabel The label to update with the library summary.
     */
    private void updateSummaryLabel(JLabel summaryLabel) {
        // Gets the library summary and replaces newlines with HTML line breaks
        String summary = libraryFacade.getLibrarySummary().replace("\n", "<br><br>");
        summaryLabel.setText("<html>" + summary + "</html>"); // Sets the summary label text
    }

    /**
     * The main method to run the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true); // Creates and shows the main window
            }
        });
    }
}