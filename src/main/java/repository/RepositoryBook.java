package repository;

import models.Book;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryBook {
    private static final List<Book> books = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    //Get all the books
    public static List<Book> getALL() {
        return  new ArrayList<>(books);
    }

    //Get book by ID
    public static Optional<Book> getBookId(int id) {
        return books.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }

    //Add book
    public static Book addBook(Book book) {
        book.setId(autoId.getAndIncrement());
        books.add(book);
        return book;
    }

    //Update book
    public static Optional<Book> updateBook(int id, Book bookUpdate) {
        Optional<Book> existing = getBookId(id);
        existing.ifPresent(book -> {
            book.setTitle(bookUpdate.getTitle());
            book.setAuthor(bookUpdate.getAuthor());
            book.setEditorial(bookUpdate.getEditorial());
            book.setCategory(bookUpdate.getCategory());
            book.setYearPublication(bookUpdate.getYearPublication());
            book.setAvailable(bookUpdate.isAvailable());
        });
        return existing;
    }

    //Delete book
    public static boolean deleteBook(int id) {
        return books.removeIf(l -> l.getId() == id);
    }


}
