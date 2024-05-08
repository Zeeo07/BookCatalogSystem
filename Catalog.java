import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Book> books;

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByGenre(String genre) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }
        return result;
    }

    public void updateBook(String title, String author, String genre) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAuthor(author);
                book.setGenre(genre);
                break;
            }
        }
    }

    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public void sortByTitle() {
        books.sort((book1, book2) -> book1.getTitle().compareToIgnoreCase(book2.getTitle()));
    }

    public void sortByAuthor() {
        books.sort((book1, book2) -> book1.getAuthor().compareToIgnoreCase(book2.getAuthor()));
    }

    public void sortByGenre() {
        books.sort((book1, book2) -> book1.getGenre().compareToIgnoreCase(book2.getGenre()));
    }

    public void generateStatistics() {
        int totalBooks = books.size();
        // Add more statistics as needed
        System.out.println("Total number of books: " + totalBooks);
    }
}
