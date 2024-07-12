import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LibraryFacade;
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
        menuFrame.setSize(300, 400);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // הוספת מרווחים בין הכפתורים

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton lendBookButton = new JButton("Lend Book");
        lendBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lendBook();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton removeMemberButton = new JButton("Remove Member");
        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMember();
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton summaryButton = new JButton("Update Summary");
        summaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSummaryLabel(summaryLabel);
            }
        });

        JButton viewBooksButton = new JButton("View All Books");
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllBooks();
            }
        });

        menuFrame.add(addBookButton, gbc);
        gbc.gridy++;
        menuFrame.add(addMemberButton, gbc);
        gbc.gridy++;
        menuFrame.add(lendBookButton, gbc);
        gbc.gridy++;
        menuFrame.add(returnBookButton, gbc);
        gbc.gridy++;
        menuFrame.add(removeBookButton, gbc);
        gbc.gridy++;
        menuFrame.add(removeMemberButton, gbc);
        gbc.gridy++;
        menuFrame.add(summaryButton, gbc);
        gbc.gridy++;
        menuFrame.add(viewBooksButton, gbc);

        menuFrame.setVisible(true);
    }

    private void addBook() {
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        Object[] message = {
                "Title:", titleField,
                "Author:", authorField,
                "Year:", yearField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            libraryFacade.addBook(title, author, year);
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
        libraryFacade.lendBook(title, memberId);
    }

    private void returnBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to return:");
        String memberId = JOptionPane.showInputDialog("Enter the member ID:");
        libraryFacade.returnBook(title, memberId);
    }

    private void removeBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to remove:");
        libraryFacade.removeBook(title);
    }

    private void removeMember() {
        String id = JOptionPane.showInputDialog("Enter the member ID to remove:");
        libraryFacade.removeMember(id);
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
