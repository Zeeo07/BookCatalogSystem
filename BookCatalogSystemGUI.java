import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookCatalogSystemGUI extends JFrame {

    private List<Book> bookList;
    private Catalog catalog;

    public BookCatalogSystemGUI() {
        super("Book Catalog System");

        // Initialize book list and catalog
        bookList = new ArrayList<>();
        catalog = new Catalog();

        // Add established books
        catalog.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        catalog.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        catalog.addBook(new Book("1984", "George Orwell", "Science Fiction"));
    

        // Load catalog data from file
        loadCatalog();

        // Set up GUI components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel buttonPanel = new JPanel(new GridLayout(0,4,10,10)); //Grid layout for the Buttons 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setBackground(new Color(255,182,193));

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        JButton viewButton = new JButton("View Books");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });

        JButton searchAuthorButton = new JButton("Search by Author");
        searchAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByAuthor();
            }
        });

        JButton searchTitleButton = new JButton("Search by Title");
        searchTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByTitle();
            }
        });

        JButton searchGenreButton = new JButton("Search by Genre");
        searchGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByGenre();
            }
        });

        JButton updateButton = new JButton("Update Book Information");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        JButton deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        JButton categorizeButton = new JButton("Categorize by Genre");
        categorizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categorizeByGenre();
            }
        });

        JButton displayDetailsButton = new JButton("Display Book Details");
        displayDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBookDetails();
            }
        });

        JButton saveButton = new JButton("Save Catalog");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCatalog();
            }
        });

        JButton loadButton = new JButton("Load Catalog");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCatalog();
            }
        });

        JButton sortButton = new JButton("Sort Books");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortBooks();
            }
        });

        JButton statisticsButton = new JButton("Generate Statistics");
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateStatistics();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchAuthorButton);
        buttonPanel.add(searchTitleButton);
        buttonPanel.add(searchGenreButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(categorizeButton);
        buttonPanel.add(displayDetailsButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(statisticsButton);
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Set up frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null); // Center the frame on the screen
        add(mainPanel);
        setVisible(true);
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog("Enter book title:");
        String author = JOptionPane.showInputDialog("Enter author:");
        String genre = JOptionPane.showInputDialog("Enter genre:");
        catalog.addBook(new Book(title, author, genre));
        JOptionPane.showMessageDialog(this, "Book added successfully!");
    }
     private void addButtonToPanel(JPanel panel, String text, String iconFileName, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setIcon(new ImageIcon(iconFileName)); // Load icon
        panel.add(button);
    }

    private void viewBooks() {
        StringBuilder sb = new StringBuilder();
        List<Book> books = catalog.getBooks();
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found.", "Book List", JOptionPane.PLAIN_MESSAGE);
        } else {
            for (Book book : books) {
                sb.append(book).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Book List", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void searchByAuthor() {
        String author = JOptionPane.showInputDialog("Enter author's name:");
        List<Book> booksByAuthor = catalog.searchByAuthor(author);
        displaySearchResults(booksByAuthor, "Books by Author: " + author);
    }

    private void searchByTitle() {
        String title = JOptionPane.showInputDialog("Enter book title:");
        List<Book> booksByTitle = catalog.searchByTitle(title);
        displaySearchResults(booksByTitle, "Books by Title: " + title);
    }

    private void searchByGenre() {
        String genre = JOptionPane.showInputDialog("Enter genre:");
        List<Book> booksByGenre = catalog.searchByGenre(genre);
        displaySearchResults(booksByGenre, "Books by Genre: " + genre);
    }

    private void updateBook() {
        String title = JOptionPane.showInputDialog("Enter title of the book to update:");
        String author = JOptionPane.showInputDialog("Enter new author:");
        String genre = JOptionPane.showInputDialog("Enter new genre:");
        catalog.updateBook(title, author, genre);
        JOptionPane.showMessageDialog(this, "Book information updated successfully.");
    }

    private void deleteBook() {
        String title = JOptionPane.showInputDialog("Enter title of the book to delete:");
        catalog.deleteBook(title);
        JOptionPane.showMessageDialog(this, "Book deleted successfully.");
    }

    private void categorizeByGenre() {
        // Displaying books by genre was already handled in the viewBooks method
        JOptionPane.showMessageDialog(this, "Books categorized by genre.");
    }

    private void displayBookDetails() {
        String title = JOptionPane.showInputDialog("Enter title of the book:");
        Book book = catalog.searchByTitle(title).stream().findFirst().orElse(null);
        if (book != null) {
            JOptionPane.showMessageDialog(this, book.toString(), "Book Details", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Book not found.", "Book Details", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void saveCatalog() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("catalog.ser"))) {
            oos.writeObject(catalog);
            JOptionPane.showMessageDialog(this, "Catalog saved successfully.", "Save Catalog", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving catalog: " + e.getMessage(), "Save Catalog Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCatalog() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("catalog.ser"))) {
            catalog = (Catalog) ois.readObject();
            JOptionPane.showMessageDialog(this, "Catalog loaded successfully.", "Load Catalog", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading catalog: " + e.getMessage(), "Load Catalog Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortBooks() {
        Object[] options = {"Sort by Title", "Sort by Author", "Sort by Genre"};
        int choice = JOptionPane.showOptionDialog(this, "Select sorting criteria:", "Sort Books", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0:
                catalog.sortByTitle();
                break;
            case 1:
                catalog.sortByAuthor();
                break;
            case 2:
                catalog.sortByGenre();
                break;
        }
        JOptionPane.showMessageDialog(this, "Books sorted successfully.", "Sort Books", JOptionPane.PLAIN_MESSAGE);
    }

    private void generateStatistics() {
        catalog.generateStatistics();
    }

    private void displaySearchResults(List<Book> books, String title) {
        StringBuilder sb = new StringBuilder();
        if (books.isEmpty()) {
            sb.append("No books found.");
        } else {
            for (Book book : books) {
                sb.append(book).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.toString(), title, JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookCatalogSystemGUI::new);
    }
}
           
