package controllers;

import models.Book;
import io.javalin.Javalin;

public class BookController {
    public static void configureRoutes(Javalin app) {
        app.get("/books", ctx -> {
        ctx.json(repository.RepositoryBook.getALL());
        });

        //Get book by ID
        app.get("/books/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.RepositoryBook.getBookId(id)
                    .ifPresentOrElse(
                            book -> ctx.json(book),
                            () -> ctx.status(404).result("Book not found")
                    );
        } );

        //Add book
        app.post("/books", ctx -> {
            Book book = ctx.bodyAsClass(Book.class);
            repository.RepositoryBook.addBook(book);
            ctx.status(201).json(book);
        });

        //Update book
        app.put("/books/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Book bookUpdate = ctx.bodyAsClass(Book.class);
            repository.RepositoryBook.updateBook(id, bookUpdate)
                    .ifPresentOrElse(
                            book -> ctx.json(book),
                            () -> ctx.status(404).result("Book not found")
                    );
        });

        //Delete book
        app.delete("/books/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean bookDelete = repository.RepositoryBook.deleteBook(id);
            if(bookDelete) {
                ctx.status(202).result("Deleted book");
            } else {
                ctx.status(404).result("Book no found");
            }
        });
    }
}
